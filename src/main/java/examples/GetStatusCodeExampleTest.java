package examples;

import utils.SimpleHttpClient;

import java.io.IOException;

public class GetStatusCodeExampleTest {

    private static final String URL = "http://www.ft.com";
    private static SimpleHttpClient httpClient;

    public static final void main(String[] args) {

        init();

        try {
            executeGetRequest(URL);
        } catch (IOException ioe) {
            System.err.println("Cannot connect to " + URL + ". " + ioe);
        } finally {
            tearDown();
        }
    }

    private static void init() {
        httpClient = new SimpleHttpClient();
    }

    private static void tearDown() {
        httpClient.closeConnection();
    }

    public static void executeGetRequest(String url) throws IOException {
        System.out.println("Executing request HTTP GET request for URL: " + url);
        httpClient.executeGet(url);
        System.out.println("Response status code is: " + httpClient.getResponseStatusCode());
        System.out.println("Response body is:  " + httpClient.getResponseBody());
    }
}
