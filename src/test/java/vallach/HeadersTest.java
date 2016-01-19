package vallach;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HeadersTest {
	
	public static void main(String[] args) throws ClientProtocolException, IOException{
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://www.ft.com");
		
//		System.out.println("request:-------------------");
//		System.out.println(httpget.getRequestLine());
		
		Header headers[] = httpget.getAllHeaders();
		
//		for(Header h:headers){
//			System.out.println(h.getName() + ": " + h.getValue());
//		}
		
		HttpResponse response = httpclient.execute(httpget);
		
//		HttpEntity entity = response.getEntity();
		
//		System.out.println("response:-------------------");
//		System.out.println(response.getStatusLine());
		
		headers = response.getAllHeaders();
		
		for(Header h:headers){
			if (h.getValue().equals("t") || h.getValue().equals("Test")) {
					System.out.println("This is the Test Environment");
			}
		}
		
//		String content = EntityUtils.toString(entity);
//		System.out.println("content:---------------------");
//		System.out.println(content);
	}
}