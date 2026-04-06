package edu.colostate.cs415.server;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.QualificationDTO;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Company;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

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
    public void test() {
        assert (true);
    }
}
