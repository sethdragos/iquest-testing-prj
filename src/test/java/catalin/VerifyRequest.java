package catalin;

import java.io.IOException;

import utils.SimpleHttpClient;
import examples.FirstExample;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;

public class VerifyRequest {
	
    private static final String URL = "http://www.ft.com";
    private static SimpleHttpClient httpClient;
	
	@BeforeTest
    public void init() {
        httpClient = new SimpleHttpClient();
	}
	
	@AfterTest
    public void tearDown() {
        httpClient.closeConnection();
    }
	
	@Test
    public void main() {
		
        try {
            FirstExample.executeGetRequest(URL);
        } catch (IOException ioe) {
            System.err.println("Cannot connect to " + URL + ". " + ioe);
        } finally {
            tearDown();
        }     
		
    Integer status=httpClient.getResponseStatusCode();   
    
    System.out.println(status);
    
    if (status==200) {
    	System.out.println("HTTP response = 200"); 
    } else {
    	System.out.println("HTTP response NOT OK");
    }
    
    Assert.assertEquals(httpClient.getResponseStatusCode(), "200");
    
    }	
}