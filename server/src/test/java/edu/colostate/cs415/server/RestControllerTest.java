package edu.colostate.cs415.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.ProjectDTO;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.ProjectSize;
import edu.colostate.cs415.model.ProjectStatus;

public class RestControllerTest {
    private static DBConnector dbConnector;
    private static Company company;
    private static RestController restController;
    Gson gson = new Gson();
    private static final int PORT = 8080;

    @BeforeClass
    public static void init(){
        resetSpark();

        dbConnector = mock(DBConnector.class);
        company = new Company("Company");
        when(dbConnector.loadCompanyData()).thenAnswer((i) -> new DBConnector().loadCompanyData());

        restController = new RestController(PORT, dbConnector);
        restController.start();
        spark.Spark.awaitInitialization();
    }

    @Before
    public void resetState() {
        setControllerCompany(new DBConnector().loadCompanyData());
    }

    @AfterClass
    public static void tearDownServer() {
        try {
            if (restController != null) {
                restController.stop();
            }
        } catch (Exception ignored) {
        }
        resetSpark();
    }

    private static void resetSpark() {
        try {
            spark.Spark.stop();
        } catch (Exception ignored) {
        }
        try {
            spark.Spark.awaitStop();
        } catch (Exception ignored) {
        }
    }

    private static String url(String path) {
        return "http://localhost:" + PORT + path;
    }

    private static void setControllerCompany(Company c) {
        try {
            Field f = RestController.class.getDeclaredField("company");
            f.setAccessible(true);
            f.set(restController, c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void assertRequestFails(Request req) throws IOException {
        boolean failed = false;
        try {
            req.execute().returnContent().asString();
        } catch (Exception e) {
            failed = true;
        }
        assertTrue(failed);
    }

    @Test
    public void testGetQualifications() throws IOException {
        QualificationDTO[] qualifications = gson.fromJson(
            Request.get(url("/api/qualifications")).execute().returnContent().asString(),
    QualificationDTO[].class);

        assertTrue(qualifications.length == 12);
    }

    @Test
    public void testGetQualification() throws IOException {
        QualificationDTO qualification = gson.fromJson(
            Request.get(url("/api/qualifications/Java")).execute().returnContent().asString(),
            QualificationDTO.class);

        assertEquals(qualification.getDescription(), "Java");
    }

    @Test
    public void testGetQualification_nonExistant() throws IOException {
        assertRequestFails(Request.get(url("/api/qualifications/NonExistentQualification")));
    }

    @Test
    public void testPostQualification() throws IOException {
        QualificationDTO qualificationDTO = new QualificationDTO("C", new String[]{});
        String qualification2String = gson.toJson(qualificationDTO);
        String response = Request.post((url("/api/qualifications/C")).replace(" ", "%20"))
                    .bodyString(qualification2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        
        QualificationDTO qualification = gson.fromJson(
                Request.get(url("/api/qualifications/C")).execute().returnContent().asString(),
                QualificationDTO.class);

        assertEquals(qualification.getDescription(), "C");
    }

    @Test
    public void testPostQualification_descriptionMissMatch() throws IOException {
        QualificationDTO qualificationDTO = new QualificationDTO("C++", new String[]{});
        String qualification2String = gson.toJson(qualificationDTO);
        assertRequestFails(Request.post(url("/api/qualifications/C").replace(" ", "%20"))
                                  .bodyString(qualification2String, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void testPostQualification_qualificationCountCheck() throws IOException {
        QualificationDTO qualificationDTO = new QualificationDTO("C++", new String[]{});

        Request.post(url("/api/qualifications/C%2B%2B"))
            .bodyString(gson.toJson(qualificationDTO), ContentType.APPLICATION_JSON)
            .execute();
        QualificationDTO[] qualifications = gson.fromJson(
            Request.get(url("/api/qualifications")).execute().returnContent().asString(),
            QualificationDTO[].class);

        assertEquals(13, qualifications.length);
    }

    @Test
    public void testGetWorkers() throws IOException {
        WorkerDTO[] workers = gson.fromJson(
            Request.get(url("/api/workers")).execute().returnContent().asString(),
            WorkerDTO[].class);
        
        assertTrue(workers.length == 12);
    }

    @Test
    public void testGetWorkerName() throws IOException {
        WorkerDTO worker = gson.fromJson(
            Request.get(url("/api/workers/Nick%20Hubbard")).execute().returnContent().asString(),
            WorkerDTO.class);
        assertEquals(worker.getName(), "Nick Hubbard");
    }

    @Test
    public void testGetWorkerName_nonexistantName() throws IOException {
        assertRequestFails(Request.get(url("/api/workers/dlkafhad")));
    }

    @Test
    public void testPostWorkerName() throws IOException {
        String[] qualifications = new String[]{"Java"};
        WorkerDTO workerDTO = new WorkerDTO("Bob Bobbington", 1000, 0, new String[]{}, qualifications);
        String worker2String = gson.toJson(workerDTO);
        String response = Request.post((url("/api/workers/Bob Bobbington")).replace(" ", "%20"))
                                 .bodyString(worker2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        
        WorkerDTO worker = gson.fromJson(
                Request.get(url("/api/workers/Bob%20Bobbington")).execute().returnContent().asString(),
                WorkerDTO.class);

        assertEquals(worker.getName(), "Bob Bobbington");
    }

    @Test
    public void testPostWorker_invalidQualification() throws IOException {
        String[] qualifications = new String[]{"Outside Qualification"};
        WorkerDTO workerDTO = new WorkerDTO("Bob Bobbington", 1000, 0, new String[]{}, qualifications);
        String worker2String = gson.toJson(workerDTO);
        assertRequestFails(Request.post(url("/api/workers/Bob Bobbington").replace(" ", "%20"))
                                  .bodyString(worker2String, ContentType.APPLICATION_JSON));
    }

    @Test
    public void testPostWorker_wrongName() throws IOException {
        String[] qualifications = new String[]{"Java"};
        WorkerDTO workerDTO = new WorkerDTO("George", 10000, 0, new String[]{}, qualifications);
        String worker2String = gson.toJson(workerDTO);
        assertRequestFails(Request.post(url("/api/workers/Bob Bobbington").replace(" ", "%20"))
                                  .bodyString(worker2String, ContentType.APPLICATION_JSON));
    }

    @Test
    public void testPostWorker_workerCountCheck() throws IOException {
        String[] qualifications = new String[]{"Java"};
        
        WorkerDTO workerDTO = new WorkerDTO("Bob Bobbington", 1000, 0, new String[]{}, qualifications);
        Request.post(url("/api/workers/Bob%20Bobbington"))
            .bodyString(gson.toJson(workerDTO), ContentType.APPLICATION_JSON)
            .execute();
        WorkerDTO[] workers = gson.fromJson(
            Request.get(url("/api/workers")).execute().returnContent().asString(),
            WorkerDTO[].class);

        assertEquals(13, workers.length);
    }

    @Test
    public void testGetProjects() throws IOException {
        ProjectDTO[] projects = gson.fromJson(
            Request.get(url("/api/projects")).execute().returnContent().asString(),
            ProjectDTO[].class);
        
        assertTrue(projects.length == 12);
    }

 @Test
    public void testGetProjectName() throws IOException {
        ProjectDTO project = gson.fromJson(
            Request.get(url("/api/projects/Financial%20Banking%20System")).execute().returnContent().asString(),
            ProjectDTO.class);
        assertEquals(project.getName(), "Financial Banking System");
    }

    @Test
    public void testGetProjectName_nonexistantName() throws IOException {
        assertRequestFails(Request.get(url("/api/projects/dlkafhad")));
    }

    @Test
    public void testPostCreateProject() throws IOException {
        String[] qualifications = new String[]{"Java"};
        String[] workers = new String[]{"Bob"};
        ProjectDTO projectDTO = new ProjectDTO("Test Project", ProjectSize.MEDIUM, ProjectStatus.ACTIVE, workers, qualifications, qualifications);
        String project2String = gson.toJson(projectDTO);
        String response = Request.post((url("/api/projects/Test Project")).replace(" ", "%20"))
                    .bodyString(project2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        
        assertEquals("OK", response);

        ProjectDTO savedProject = gson.fromJson(
            Request.get(url("/api/projects/Test%20Project")).execute().returnContent().asString(),
            ProjectDTO.class);
        assertEquals("Test Project", savedProject.getName());
        assertEquals(ProjectStatus.PLANNED, savedProject.getStatus());
        assertEquals(0, savedProject.getWorkers().length);
    }

    @Test
    public void testPostProject_emptyQualifications() throws IOException {
        String[] qualifications = new String[]{};
        String[] workers = new String[]{"Bob"};
        ProjectDTO projectDTO = new ProjectDTO("Test Project", ProjectSize.MEDIUM, ProjectStatus.ACTIVE, workers, qualifications, qualifications);

        String project2String = gson.toJson(projectDTO);
        assertRequestFails(Request.post(url("/api/projects/Test Project").replace(" ", "%20"))
            .bodyString(project2String, ContentType.APPLICATION_JSON));
    }

    @Test
    public void testPostProject_invalidQualification() throws IOException {
        String[] qualifications = new String[]{"Outside Qualification"};
        String[] workers = new String[]{"Bob"};
        ProjectDTO projectDTO = new ProjectDTO("Test Project", ProjectSize.MEDIUM, ProjectStatus.ACTIVE, workers, qualifications, qualifications);

        String project2String = gson.toJson(projectDTO);
        assertRequestFails(Request.post(url("/api/projects/Test Project").replace(" ", "%20"))
            .bodyString(project2String, ContentType.APPLICATION_JSON));
    }

    @Test
    public void testPostProject_wrongName() throws IOException {
        String[] qualifications = new String[]{"Java"};
        String[] workers = new String[]{"Bob"};
        ProjectDTO projectDTO = new ProjectDTO("Test", ProjectSize.MEDIUM, ProjectStatus.ACTIVE, workers, qualifications, qualifications);

        String project2String = gson.toJson(projectDTO);
        assertRequestFails(Request.post(url("/api/projects/Test Project").replace(" ", "%20"))
            .bodyString(project2String, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_valid_assign() throws IOException{
        WorkerDTO w1 = gson.fromJson(
            Request.get(url("/api/workers/Erika%20Johnston")).execute().returnContent().asString(),
            WorkerDTO.class);

        String worker = "Erika Johnston";
        String project = "Android Task Monitoring";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        String returnValue = Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON)
                                    .execute().returnContent().asString();

        assertEquals(returnValue, "OK");

        WorkerDTO w2 = gson.fromJson(
            Request.get(url("/api/workers/Erika%20Johnston")).execute().returnContent().asString(),
            WorkerDTO.class);

        assertEquals(w2.getWorkload(), w1.getWorkload() + 1);
        //implemented this before the Project queries were implemented, so just checks that the project workload was added
    }

    @Test
    public void test_missingProject_assign() throws IOException{
        String worker = "Erika Johnston";
        String jsonString = String.format("{\"worker\":\"%s\"}",worker);
        assertRequestFails(Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_missingWorker_assign() throws IOException{
        String project = "Android Task Monitoring";
        String jsonString = String.format("{\"project\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void test_emptyWorkerName_assign() throws IOException{
        String worker = "";
        String project = "Android Task Monitoring";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_emptyProjectName_assign() throws IOException{
        String worker = "Erika Johnston";
        String project = "";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void test_workerNotInCompany_assign() throws IOException{
        String worker = "HIJKLMNOP";
        String project = "Android Task Monitoring";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_projectNotInCompany_assign() throws IOException{
        String worker = "Erika Johnston";
        String project = "ABCDEFG";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/assign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_valid_unassign() throws IOException{
        WorkerDTO w1 = gson.fromJson(
            Request.get(url("/api/workers/Omar%20Williamson")).execute().returnContent().asString(),
            WorkerDTO.class);

        String worker = "Omar Williamson";
        String project = "Credit Card Fraud Detection";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        String returnValue = Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON)
                                    .execute().returnContent().asString();

        assertEquals(returnValue, "OK");

        WorkerDTO w2 = gson.fromJson(
            Request.get(url("/api/workers/Omar%20Williamson")).execute().returnContent().asString(),
            WorkerDTO.class);

        assertEquals(w2.getWorkload(), w1.getWorkload() - 3);
        //implemented this before the Project queries were implemented, so just checks that the project workload was added
    }

    @Test 
    public void test_missingProject_unassign() throws IOException{
        String worker = "Omar Williamson";
        String jsonString = String.format("{\"worker\":\"%s\"}",worker);
        assertRequestFails(Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_missingWorker_unassign() throws IOException{
        String project = "Credit Card Fraud Detection";
        String jsonString = String.format("{\"project\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void test_emptyWorkerName_unassign() throws IOException{
        String worker = "";
        String project = "Credit Card Fraud Detection";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}", worker, project);
        assertRequestFails(Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_emptyProjectName_unassign() throws IOException{
        String worker = "Omar Williamson";
        String project = "";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void test_workerNotInCompany_unassign() throws IOException{
        String worker = "HIJKLMNOP";
        String project = "Credit Card Fraud Detection";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_projectNotInCompany_unassign() throws IOException{
        String worker = "Omar Williamson";
        String project = "ABCDEFG";
        String jsonString = String.format("{\"worker\":\"%s\",\"project\":\"%s\"}",worker, project);
        assertRequestFails(Request.put(url("/api/unassign"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void test_valid_start() throws IOException{
        String project = "Face Detector";
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        String returnValue = Request.put(url("/api/start"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON)
                                    .execute().returnContent().asString();

        assertEquals(returnValue, "OK");


        ProjectDTO p = gson.fromJson(
            Request.get(url("/api/projects/Face%20Detector")).execute().returnContent().asString(),
            ProjectDTO.class);
        
        assertEquals(p.getStatus(), ProjectStatus.ACTIVE);
    }

    @Test
    public void test_nullProject_start() throws IOException{
        String project = null;
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/start"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    public void test_emptyProject_start() throws IOException{
        String project = "";
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/start"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_projectNotInCompany_start() throws IOException{
        String project = "ABCDEFG";
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/start"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test 
    public void test_valid_finish() throws IOException{
        String project = "Smart Chatbot";
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        String returnValue = Request.put(url("/api/finish"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON)
                                    .execute().returnContent().asString();

        assertEquals(returnValue, "OK");


        ProjectDTO p = gson.fromJson(
            Request.get(url("/api/projects/Smart%20Chatbot")).execute().returnContent().asString(),
            ProjectDTO.class);
        
        assertEquals(ProjectStatus.FINISHED, p.getStatus());
    }

    @Test
    public void test_nullProject_finish() throws IOException{
        String project = null;
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/finish"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    public void test_emptyProject_finish() throws IOException{
        String project = "";
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/finish"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void test_projectNotInCompany_finish() throws IOException{
        String project = "ABCDEFG";
        String jsonString = String.format("{\"name\":\"%s\"}", project);
        assertRequestFails(Request.put(url("/api/finish"))
                                    .addHeader("Content-Type", "application/json")
                                    .bodyString(jsonString, ContentType.APPLICATION_JSON));
    }

    @Test
    public void testPostWorker_updatesQualificationWorkersList() throws IOException {
        String[] qualifications = new String[] { "Java" };
        WorkerDTO workerDTO = new WorkerDTO("Alice A", 1000, 0, new String[] {}, qualifications);

        // POST new worker
        Request.post(url("/api/workers/Alice%20A"))
            .bodyString(gson.toJson(workerDTO), ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();

        // GET qualification and confirm it now lists the new worker
        QualificationDTO javaDto = gson.fromJson(
            Request.get(url("/api/qualifications/Java"))
                .execute()
                .returnContent()
                .asString(),
            QualificationDTO.class
        );

        assertTrue(Arrays.asList(javaDto.getWorkers()).contains("Alice A"));
    }

    @Test
    public void testPostWorker_multipleQualifications_updatesAllQualificationWorkersLists() throws IOException {
        String[] qualifications = new String[] { "Java", "Python" };
        WorkerDTO workerDTO = new WorkerDTO("Multi Qual", 1000, 0, new String[] {}, qualifications);

        Request.post(url("/api/workers/Multi%20Qual"))
            .bodyString(gson.toJson(workerDTO), ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();

        QualificationDTO javaDto = gson.fromJson(
            Request.get(url("/api/qualifications/Java"))
                .execute()
                .returnContent()
                .asString(),
            QualificationDTO.class
        );

        QualificationDTO pythonDto = gson.fromJson(
            Request.get(url("/api/qualifications/Python"))
                .execute()
                .returnContent()
                .asString(),
            QualificationDTO.class
        );

        assertTrue(Arrays.asList(javaDto.getWorkers()).contains("Multi Qual"));
        assertTrue(Arrays.asList(pythonDto.getWorkers()).contains("Multi Qual"));
    }

    @Test
    public void testPostProject_projectListCountIncreases() throws IOException {
        ProjectDTO[] before = gson.fromJson(
            Request.get(url("/api/projects")).execute().returnContent().asString(),
            ProjectDTO[].class
        );
        assertEquals(12, before.length);

        String[] qualifications = new String[] { "Java" };
        ProjectDTO projectDTO = new ProjectDTO(
            "Count Project",
            ProjectSize.SMALL,
            ProjectStatus.ACTIVE,     // controller/model should still store PLANNED initially
            new String[] {},
            qualifications,
            new String[] {}
        );

        Request.post(url("/api/projects/Count%20Project"))
            .bodyString(gson.toJson(projectDTO), ContentType.APPLICATION_JSON)
            .execute()
            .returnContent()
            .asString();

        ProjectDTO[] after = gson.fromJson(
            Request.get(url("/api/projects")).execute().returnContent().asString(),
            ProjectDTO[].class
        );

        assertEquals(13, after.length);
        assertTrue(Arrays.stream(after).anyMatch(p -> "Count Project".equals(p.getName())));
    }
    
    @Test
    public void test() {
        assert (true);
    }
}
