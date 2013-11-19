package george;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.SimpleHttpClient;

import java.io.IOException;


public class StatusCodeTest {
    private static final String URL = "http://www.ft.com";
    private static SimpleHttpClient httpClient;

    @BeforeTest
    private static void init() {
        httpClient = new SimpleHttpClient();

    }

    @AfterTest
    private static void tearDown() {
        httpClient.closeConnection();
    }

    @Test(testName = "PapucuLuJorje")
    public void mainTest() {

        try {
            httpClient.executeGet(URL);

        } catch (IOException ioe) {
            System.err.println("Cannot connect to " + URL + ". " + ioe);
        }
        Integer expectedStatus = 200;
        System.out.println("This is not OK");
        Assert.assertEquals(httpClient.getResponseStatusCode(), expectedStatus, "HTTP response NOT OK");

    }
}
