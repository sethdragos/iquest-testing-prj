package catalin;

import java.io.IOException;

import utils.SimpleHttpClient;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;

public class VerifyRequestTest {
	
    private static final String URL = "http://www.ft.com";
    private static SimpleHttpClient httpClient;

	@BeforeTest
    public void open() {
		
        httpClient = new SimpleHttpClient();
	}
	
	@AfterTest
    public void close() {
        httpClient.closeConnection();
    }
	
	@Test
    public void mainTest() {
		
	System.out.println("Connecting to: " + URL);
		
        try {
        	httpClient.executeGet(URL);
        } catch (IOException ioe) {
            System.err.println("Cannot connect to " + URL + ". " + ioe);
        } finally {
            close();
        }
		
    Integer expectedStatus = 200;   
 
    Assert.assertEquals(httpClient.getResponseStatusCode(), expectedStatus, "HTTP response NOT OK");
    System.out.println("HTTP status OK");
    
    }	
}