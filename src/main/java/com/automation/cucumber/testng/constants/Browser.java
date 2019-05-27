package com.automation.cucumber.testng.constants;

public enum Browser {

	CHROME("chrome"),
	FIREFOX("firefox");
	
	private String name;
	
	private Browser(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
