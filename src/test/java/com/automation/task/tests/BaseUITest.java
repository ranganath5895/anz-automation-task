package com.automation.task.tests;

import org.testng.annotations.BeforeClass;

import com.automation.task.tests.utils.NavigationsUtil;

/**
 *
 * This Class to create All Helper Class Objects to give to the Test Classes
 * 
 * @author Ranganath Chenna
 *
 */
public class BaseUITest {

	protected NavigationsUtil navigationsUtil;
	
	@BeforeClass
	public void setUp() {
		navigationsUtil = new NavigationsUtil();
	}
}
