package edu.colostate.cs415.server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.dto.WorkerDTO;
import edu.colostate.cs415.model.Company;
import edu.colostate.cs415.model.Worker;
import edu.colostate.cs415.model.Qualification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

public class RestControllerTest {
    private static DBConnector dbConnector;
    private static Company company;
    private static RestController restController;
    Gson gson = new Gson();
    private static final int PORT = 4567;

    @BeforeClass
    public static void init(){
        resetSpark();

        dbConnector = mock(DBConnector.class);
        company = new Company("Company");
        when(dbConnector.loadCompanyData()).thenAnswer((i) -> company);

        restController = new RestController(PORT, dbConnector);
        restController.start();
        spark.Spark.awaitInitialization();

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
            Request.get("http://localhost:4567/api/workers").execute().returnContent().asString(),
            WorkerDTO[].class);
        
    }

    @Test
    public void testGetWorkerName() throws IOException {
        String name = "Bob";
        Set<Qualification> qs = new HashSet<>();
        Worker worker2 = company.createWorker(name, qs, 1000);
        WorkerDTO worker = gson.fromJson(
            Request.get("http://localhost:4567/api/workers/" + name).execute().returnContent().asString(),
            WorkerDTO.class);
        assertEquals(worker.getName(), name);
    }

    @Test
    public void testPostWorkerName() throws IOException {
        String name = "Bob";
        Set<Qualification> qs = new HashSet<>();
        Worker worker2 = new Worker(name, qs, 1000);
        String worker2String = gson.toJson(worker2.toDTO());

        String response = Request.post(("http://localhost:4567/api/workers/" + name).replace(" ", "%20"))
                    .bodyString(worker2String, ContentType.APPLICATION_JSON).execute().returnContent().asString();
        
        WorkerDTO worker = gson.fromJson(
                Request.get("http://localhost:4567/api/workers/" + name).execute().returnContent().asString(),
                WorkerDTO.class);
        
        assertEquals(worker.getName(), name);
    }

    @Test
    public void test() {
        assert (true);
    }
}
