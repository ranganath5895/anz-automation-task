package com.automation.task.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.task.common.constants.Browser;
import com.automation.task.common.constants.ConfigData;
import com.automation.task.common.helpers.ConfigReader;
import com.automation.task.common.reports.ReportLogger;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author Ranganath Chenna
 *
 */
@Slf4j
class StartBrowserInstance {

	static WebDriver getWebDriverInstance() throws CommonException{
		
		WebDriver driver = null;
		
		ConfigData configData = ConfigReader.getConfigData();
		
		logger.info("Config Data :: {}"+configData.toString());
    	
		if(Boolean.parseBoolean(configData.getRunLocal())) {
			
			logger.info("Execution is happening in Local Environment...");
			
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
			
			logger.info("Execution is happening in Grid Environment...");
			
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
			
			ReportLogger.log("Session Id ::: " + ((RemoteWebDriver) driver).getSessionId());
    		
		}
		
		driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	
    	return driver;
	}
}
