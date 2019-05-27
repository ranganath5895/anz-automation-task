package com.automation.cucumber.testng;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HooksTest {

	private WebDriver webDriver;

	@Before
	public void setUpThingsForTest() throws CommonException {
		ConfigReader.readAndSetConfigData();
		webDriver = StartBrowserInstance.getWebDriverInstance();
		WebBrowser.setWebDriver(webDriver);
	}
	
	
	@After
	public void closingTheThingsForTest() {
	
		if(Objects.nonNull(webDriver)) {
			webDriver.quit();
		}
	}
}

