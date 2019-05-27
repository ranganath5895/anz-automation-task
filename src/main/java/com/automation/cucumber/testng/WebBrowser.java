package com.automation.cucumber.testng;

import org.openqa.selenium.WebDriver;

public class WebBrowser {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	
	public static WebDriver getInstance() {
		return webDriver.get();
	}

	static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}
}
