package com.automation.cucumber.testng.stepdefinitions;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.automation.cucumber.testng.CommonException;
import com.automation.cucumber.testng.ConfigReader;
import com.automation.cucumber.testng.WebBrowser;
import com.automation.cucumber.testng.pages.MuchBorrowPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoanAmountNegativeCheckSteps {

	String loanAmountCheckURL = "/personal/home-loans/calculators-tools/much-borrow/";
	private MuchBorrowPage muchBorrowPage;
	
	@Given("Add Living Expenses")
	public void Add_Living_Expenses() throws CommonException {
				
		muchBorrowPage = PageFactory.initElements(WebBrowser.getInstance(), MuchBorrowPage.class);
		
		String url = ConfigReader.getConfigData().getAppHost() + loanAmountCheckURL;
		WebBrowser.getInstance().get(url);
		muchBorrowPage.getLivingExpensesTextField().sendKeys("1");
	}
	
	@When("Submit the form With Less Fields")
	public void Submit_the_application_form_with_less_fields() throws CommonException {
		
		Reporter.log("Filled User Details before clicking Caluculate Button",true);
		muchBorrowPage.getBorrowCalculateButton().click();
	}
	
	@Then("Check for the error Message")
	public void check_for_the_error_message() throws CommonException {
		
		String expectedErrorMessage = "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641.";
				
		String actualErrorMessage = muchBorrowPage.getBorrowErrorMessageTextLabel().getText();
		Reporter.log("Expected Error Message :: "+expectedErrorMessage);
		Reporter.log("Actual Error Message :: "+actualErrorMessage);
	    Reporter.log("With Only Living Expenses -- Form Submission", true);
	    assertEquals(actualErrorMessage, expectedErrorMessage);
	} 
}


