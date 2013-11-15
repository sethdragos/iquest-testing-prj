package catalin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Created with Eclipse, using BrowserMob Proxy
 * User: Vallach
 * Date: 03/11/2013
 * HAR Viewer: http://www.softwareishard.com/har/viewer/
 */

public class GetHTTParchive {
	
	private WebDriver driver;
	private ProxyServer server;
	private Proxy proxy;
	private Har har;
	private String strFilePath;
	private String baseURL = ("http://www.ft.com/home/uk");
	
@BeforeTest
public void createDriver() {
	
	server = new ProxyServer(4444);
    
    try {
		server.start();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	try {
		proxy = server.seleniumProxy();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, proxy);
    
    driver = new FirefoxDriver(capabilities);
}

@AfterTest
public void tidyUp() throws Exception {
	server.stop();
	driver.quit();
}

@Test (testName = "GetHARfile")
public void httpRequestCheck() throws IOException {
  
	server.newHar("FT");
	driver.get(baseURL);
	har = server.getHar();

	System.out.println("HAR = " + har);
	
	strFilePath = "Selenium_test.har";
	FileOutputStream fos = new FileOutputStream(strFilePath);
	har.writeTo(fos);
}
}