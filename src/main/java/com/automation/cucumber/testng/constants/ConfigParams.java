package com.automation.cucumber.testng.constants;

public enum ConfigParams{
	
	RUN_LOCAL("runLocal"),
	GRID_HUB_HOST("gridHubHost"),
	GRID_HUB_PORT("gridHubPort"),
	CHROME_DRIVER_EXE_PATH("chromeDriverInterfacePath"),
	FIREFOX_DRIVER_EXE_PATH("firefoxDriverInterfacePath"),
	BROWSER_NAME("browserName"),
	APP_HOST("appHost");
	
	private String param;
	
	private ConfigParams(String param) {
		this.param = param;
	}
	
	public String getPropertyKey() {
		return param;
	}
}
