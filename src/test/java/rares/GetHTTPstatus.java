package rares;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.SimpleHttpClient;

import java.io.IOException;

public class GetHTTPstatus {
    private static final String URL = "http://www.ft.com";          //definesc constanta URL ; final - inseamna ca nu se modifica
    private static SimpleHttpClient httpClient;
    private static  Integer EXPECTED_STATUS = 200;                  //creez expected status care e o valoare finala aka 200

    @BeforeTest
    private void init() {
        httpClient = new SimpleHttpClient();
    }


    @Test(testName = "rastat")
    public void mainTest() {

        try {
            httpClient.executeGet(URL);

        } catch (IOException ioe) {
            System.err.println("Cannot connect to " + URL + ioe);
        }

        Assert.assertEquals(httpClient.getResponseStatusCode(), EXPECTED_STATUS, "HTTP response NOT OK");
        System.out.println("Status code is  OK");

    }

    @AfterTest
    private void tearDown() {
        httpClient.closeConnection();
    }
}
