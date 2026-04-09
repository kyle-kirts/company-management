package edu.colostate.cs415.server;

import static spark.Spark.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.AssignmentDTO;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Project;
import edu.colostate.cs415.model.Qualification;
import edu.colostate.cs415.model.Worker;
import spark.Request;
import spark.Response;
import spark.Spark;

public class RestController {

	private static Logger log = Logger.getLogger(RestController.class.getName());
	private static String OK = "OK";
	private static String KO = "KO";

	private DBConnector dbConnector;
	private Company company;
	private Gson gson;

	public RestController(int port, DBConnector dbConnector) {
		port(port);
		this.dbConnector = dbConnector;
		gson = new Gson();
	}

	public void start() {
		// Load data from DB
		company = dbConnector.loadCompanyData();

		// Redirect
		redirect.get("/", "/helloworld");

		// Logging
		after("/*", (req, res) -> logRequest(req, res));
		exception(Exception.class, (exc, req, res) -> handleException(exc, res));

		// Hello World
		get("/helloworld", (req, res) -> helloWorld());

		// API
		path("/api", () -> {
			// Enable CORS
			options("/*", (req, res) -> optionsCORS(req, res));
			after("/*", (req, res) -> enableCORS(res));

			// Qualifications
			path("/qualifications", () -> {
				get("", (req, res) -> getQualifications(), gson::toJson);
				get("/:description", (req, res) -> getQualification(req.params("description")),
						gson::toJson);
				post("/:description", (req, res) -> createQualification(req));
			});

			path("/workers", () -> {
				get("", (req, res) -> getWorkers(), gson::toJson);
				get("/:name", (req, res) -> getWorker(req.params("name")),
						gson::toJson);
				post("/:name", (req, res) -> createWorker(req));
			});

			put("/assign",  (req, res) -> assign(req));

			put("/unassign", (req, res) -> unassign(req));

			put("/start", (req, res) -> start(req));

			put("/finish", (req, res) -> finish(req));

			path("/projects", () -> {
				get("", (req, res) -> getProjects(), gson::toJson);
				get("/:name", (req, res) -> getProject(req.params("name")),
						gson::toJson);
				post("/:name", (req, res) -> createProject(req));
			});
		});
	}

	public void stop() {
		Spark.stop();
	}

	private String helloWorld() {
		return "Hello World!";
	}

	private String assign(Request request){
		AssignmentDTO adto = gson.fromJson(request.body(), AssignmentDTO.class);
		String workerName = adto.getWorker();
		String projectName = adto.getProject();

		if(workerName == null || projectName == null) throw new RuntimeException("Worker or Project is null.");

		Worker worker = company.getEmployedWorkers().stream()
													.filter(w -> w.getName().equals(workerName))
													.findFirst()
													.orElseThrow(() -> new RuntimeException("Worker not at this company."));
		Project project = company.getProjects().stream()
												.filter(p -> p.getName().equals(projectName))
												.findFirst()
												.orElseThrow(() -> new RuntimeException("Project not at this company."));

		company.assign(worker, project);

		return OK;
	}

	private String unassign(Request request){
		AssignmentDTO adto = gson.fromJson(request.body(), AssignmentDTO.class);
		String workerName = adto.getWorker();
		String projectName = adto.getProject();

		if(workerName == null || projectName == null) throw new RuntimeException("Worker or Project is null.");

		Worker worker = company.getEmployedWorkers().stream()
													.filter(w -> w.getName().equals(workerName))
													.findFirst()
													.orElseThrow(() -> new RuntimeException("Worker not at this company."));
		Project project = company.getProjects().stream()
												.filter(p -> p.getName().equals(projectName))
												.findFirst()
												.orElseThrow(() -> new RuntimeException("Project not at this company."));

		company.unassign(worker, project);

		return OK;
	}

	private String start(Request request){
		ProjectDTO proj = gson.fromJson(request.body(), ProjectDTO.class);
		String projectName = proj.getName();

		if(projectName == null) throw new RuntimeException("Project is null");

		Project project = company.getProjects().stream()
												.filter(p -> p.getName().equals(projectName))
												.findFirst()
												.orElseThrow(() -> new RuntimeException("Project not at this company."));
		company.start(project);

		return OK;
	}

	private String finish(Request request){
		ProjectDTO proj = gson.fromJson(request.body(), ProjectDTO.class);
		String projectName = proj.getName();

		if(projectName == null) throw new RuntimeException("Project is null");

		Project project = company.getProjects().stream()
												.filter(p -> p.getName().equals(projectName))
												.findFirst()
												.orElseThrow(() -> new RuntimeException("Project not at this company."));
		company.finish(project);

		return OK;

	}

	private QualificationDTO[] getQualifications() {
		Set<Qualification> qualifications = company.getQualifications();
		QualificationDTO[] qualificationsDTO = qualifications.stream()
															 .map(Qualification::toDTO)
															 .toArray(QualificationDTO[]::new);
		return qualificationsDTO;
	}

	private QualificationDTO getQualification(String description) {
		Set<Qualification> qualifications = company.getQualifications();
		QualificationDTO  qualificationDTO = qualifications.stream()
														   .filter(q -> q.equals(new Qualification(description)))
														   .map(Qualification::toDTO)
														   .findFirst()
														   .orElseThrow(() -> new RuntimeException("Qualification not found."));
		return qualificationDTO;
	}

	private String createQualification(Request request) {
		QualificationDTO assignmentDTO = gson.fromJson(request.body(), QualificationDTO.class);
		if (request.params("description").equals(assignmentDTO.getDescription())) {
			company.createQualification(assignmentDTO.getDescription());
		} else
			throw new RuntimeException("Qualification descriptions do not match.");
		return OK;
	}

	private WorkerDTO[] getWorkers() {
		Set<Worker> workerSet = company.getEmployedWorkers();
		WorkerDTO[] workers = workerSet.stream()
									   .map(Worker::toDTO)
									   .toArray(WorkerDTO[]::new);

		return workers;
	}

	private WorkerDTO getWorker(String name) {
		Set<Worker> workerSet = company.getEmployedWorkers();
		WorkerDTO worker = workerSet.stream()
									.filter(w -> w.getName().equals(name))
									.map(Worker::toDTO)
									.findFirst()
									.orElseThrow(() -> new RuntimeException("Worker not found."));
		return worker;
	}

	private String createWorker(Request request) {
		WorkerDTO workerDTO = gson.fromJson(request.body(), WorkerDTO.class);

		if (!request.params("name").equals(workerDTO.getName())) {
			throw new RuntimeException("Worker names do not match.");
		}

		// Resolve to the company’s Qualification instances (no new Qualification objects)
		Set<Qualification> qualifications = resolveQualifications(workerDTO.getQualifications());

		Worker created = company.createWorker(workerDTO.getName(), qualifications, workerDTO.getSalary());
		if (created == null) {
			throw new RuntimeException("Failed to create worker.");
		}

		return OK;
	}

	private ProjectDTO[] getProjects() {
		Set<Project> projectSet = company.getProjects();
		ProjectDTO[] projects = projectSet.stream()
									   .map(Project::toDTO)
									   .toArray(ProjectDTO[]::new);

		return projects;
	}

	private ProjectDTO getProject(String projectName) {
		Set<Project> projectSet = company.getProjects();
		ProjectDTO project = projectSet.stream()
									.filter(w -> w.getName().equals(projectName))
									.map(Project::toDTO)
									.findFirst()
									.orElseThrow(() -> new RuntimeException("Project not found."));
		return project;
	}

	private String createProject(Request request) {
		ProjectDTO projectDTO = gson.fromJson(request.body(), ProjectDTO.class);

		if (!request.params("name").equals(projectDTO.getName())) {
			throw new RuntimeException("Project names do not match.");
		}

		// Resolve to the company’s Qualification instances (no new Qualification objects)
		Set<Qualification> qualifications = resolveQualifications(projectDTO.getQualifications());

		Project createdProject = company.createProject(projectDTO.getName(), qualifications, projectDTO.getSize());
		if (createdProject == null) {
			throw new RuntimeException("Failed to create project.");
		}

		return OK;
	}

	//Helper to ensure that only canonical instance of Qualifications is used 
	private Qualification resolveQualification(String description) {
    if (description == null || description.trim().isEmpty()) {
        throw new RuntimeException("Qualification description is null or empty.");
    }

    Qualification probe = new Qualification(description);

    return company.getQualifications().stream()
            .filter(q -> q.equals(probe))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Qualifications not found in company."));
	}

	private Set<Qualification> resolveQualifications(String[] descriptions) {
		if (descriptions == null || descriptions.length == 0) {
			throw new RuntimeException("Qualifications cannot be null or empty.");
		}

		return Arrays.stream(descriptions)
				.map(this::resolveQualification)
				.collect(Collectors.toSet());
	}


	// Logs every request received
	private void logRequest(Request request, Response response) {
		log.info(request.requestMethod() + " " + request.pathInfo() + "\nREQUEST:\n" + request.body() + "\nRESPONSE:\n"
				+ response.body());
	}

	// Exception handling
	private void handleException(Exception exception, Response response) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace();
		exception.printStackTrace(pw);
		log.severe(sw.toString());
		response.body(KO);
		response.status(500);
	}

	// Enable CORS
	private void enableCORS(Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	}

	// Enable CORS
	private String optionsCORS(Request request, Response response) {
		String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
		if (accessControlRequestHeaders != null)
			response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);

		String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
		if (accessControlRequestMethod != null)
			response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
		return OK;
	}
}