package com.automation.cucumber.testng;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.cucumber.testng.constants.Browser;
import com.automation.cucumber.testng.constants.ConfigData;
/**
 * 
 * 
 * @author Ranganath Chenna
 *
 */
public class StartBrowserInstance {

	public static WebDriver getWebDriverInstance() throws CommonException{
		
		WebDriver driver = null;
		
		ConfigData configData = ConfigReader.getConfigData();
		
		if(Boolean.parseBoolean(configData.getRunLocal())) {
			
			
			if(configData.getBrowserName().equalsIgnoreCase(Browser.FIREFOX.getName())) {
				
				if(    Objects.isNull(configData.getFirefoxDriverInterfacePath()) || 
						configData.getChromeDriverInterfacePath().trim().length() == 0) {
					throw new CommonException("Invalid Firefox/Gecko Driver Path Passed... Please valid Path of Firefox Executable");
				}
				
				System.setProperty("webdriver.gecko.driver",configData.getFirefoxDriverInterfacePath());driver = new FirefoxDriver();
			}
			else if(configData.getBrowserName().equalsIgnoreCase(Browser.CHROME.getName())){
				
				if(    Objects.isNull(configData.getChromeDriverInterfacePath()) || 
						configData.getChromeDriverInterfacePath().trim().length() == 0) {
					throw new CommonException("Invalid Chrome Driver Path Passed... Please valid Path of ChromeExecutable");
				}
				
				System.setProperty("webdriver.chrome.driver",configData.getChromeDriverInterfacePath());
				driver = new ChromeDriver();
			}
			else {
				throw new CommonException("Invalid Browser Selected :: Please Pass Any one Of ::: "+Browser.FIREFOX.getName()+"/"+Browser.CHROME.getName());
			}
			
		}
		else {
						
			DesiredCapabilities capabilities; 
			
			if(configData.getBrowserName().equalsIgnoreCase(Browser.FIREFOX.getName())) {
				capabilities = DesiredCapabilities.firefox();
			}
			else if(configData.getBrowserName().equalsIgnoreCase(Browser.CHROME.getName())){
				capabilities = DesiredCapabilities.chrome();
			}
			else {
				throw new CommonException("Invalid Browser Selected :: Please Pass Any one Of ::: "+Browser.FIREFOX.getName()+"/"+Browser.CHROME.getName());
			}
			
			String remoteGridURL =  "http://"+configData.getGridHubHost()+":"+configData.getGridHubPort()+"/wd/hub";
			URL url;
			try {
				url = new URL(remoteGridURL);
			} catch (MalformedURLException e) {
				throw new CommonException("Exception while constrcting the Grid URL", e.getCause());
			}
			
			capabilities.setCapability("video", "True"); // NOTE: "True" is a case sensitive string, not boolean.
			
			driver = new RemoteWebDriver(url,capabilities);	
		}
		
		driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	
    	return driver;
	}
}
