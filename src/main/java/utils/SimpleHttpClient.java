package utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author : dragos.serghie
 *         <p/>
 *         A simple Apache HttpClient to execute a HTTP GET request against
 *         a target URL.
 */

public class SimpleHttpClient {

    private DefaultHttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse response;
    private HttpEntity entity;

    public SimpleHttpClient() {
        httpClient = new DefaultHttpClient();
    }

    public void executeGet(String url) throws IOException {
        httpGet = new HttpGet(url);
        response = httpClient.execute(httpGet);
    }

    public Integer getResponseStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public String getResponseBody() throws IOException {

        entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);
        if (entity != null) {
            EntityUtils.consume(entity);
        }
        return responseBody;
    }

    public void closeConnection() {
        httpClient.getConnectionManager().shutdown();
    }
}
