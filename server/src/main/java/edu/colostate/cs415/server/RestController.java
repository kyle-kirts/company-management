package edu.colostate.cs415.server;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.redirect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
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
		WorkerDTO assignmentDTO = gson.fromJson(request.body(), WorkerDTO.class);
		if (request.params("name").equals(assignmentDTO.getName())) {
			Stream<String> stream = Arrays.stream(assignmentDTO.getQualifications());
			Set<Qualification> qualifications = stream.map(Qualification::new)
													  .collect(Collectors.toSet());

			if (!company.getQualifications().containsAll(qualifications)) {
				throw new RuntimeException("Qualifications not found in company.");
			}

			company.createWorker(assignmentDTO.getName(), qualifications, assignmentDTO.getSalary());
		} else
			throw new RuntimeException("Worker names do not match.");
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
		if (request.params("name").equals(projectDTO.getName())) {
			if (projectDTO.getQualifications() == null || projectDTO.getQualifications().length == 0) {
				throw new RuntimeException("Project qualifications cannot be null or empty.");
			}

			Stream<String> stream = Arrays.stream(projectDTO.getQualifications());
			Set<Qualification> qualifications = stream.map(Qualification::new)
													  .collect(Collectors.toSet());

			if (!company.getQualifications().containsAll(qualifications)) {
				throw new RuntimeException("Qualifications not found in company.");
			}

			Project createdProject = company.createProject(projectDTO.getName(), qualifications, projectDTO.getSize());
			if (createdProject == null) {
				throw new RuntimeException("Failed to create project.");
			}
		} else {
			throw new RuntimeException("Project names do not match.");
		}
		return OK;
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