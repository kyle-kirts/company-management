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

import com.google.gson.Gson;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Company;

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
    public void test() {
        assert (true);
    }
}
