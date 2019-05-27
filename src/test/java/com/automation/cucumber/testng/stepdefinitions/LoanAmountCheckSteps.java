package com.automation.cucumber.testng.stepdefinitions;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.automation.cucumber.testng.CommonException;
import com.automation.cucumber.testng.ConfigReader;
import com.automation.cucumber.testng.WebBrowser;
import com.automation.cucumber.testng.pages.MuchBorrowPage;
import com.automation.cucumber.testng.testdata.UserData;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoanAmountCheckSteps {

	String loanAmountCheckURL = "/personal/home-loans/calculators-tools/much-borrow/";
	
	private UserData userData;
	private MuchBorrowPage muchBorrowPage;
	
	@Given("Fill the User Details")
	public void Fill_the_User_Details(DataTable dataTable) throws CommonException {
		
		userData = dataTable.asList(UserData.class).get(0);
		
		muchBorrowPage = PageFactory.initElements(WebBrowser.getInstance(), MuchBorrowPage.class);
		
		String url = ConfigReader.getConfigData().getAppHost() + loanAmountCheckURL;
		WebBrowser.getInstance().get(url);
		muchBorrowPage.fillDataForHomeLoan(userData);
	}
	
	@When("Submit the application form")
	public void Submit_the_application_form() throws CommonException {
		
		Reporter.log("Filled User Details before clicking Caluculate Button",true);
		muchBorrowPage.getBorrowCalculateButton().click();
	}
	
	@Then("check the amount is equal to expected")
	public void check_the_amount_is_equal_to_expected() throws CommonException {
				
		String actualEligibleAmount = muchBorrowPage.getEligibleAmountTextField().getText();
		Reporter.log("Eligible Amount :: "+actualEligibleAmount);
		
		actualEligibleAmount = actualEligibleAmount.substring(actualEligibleAmount.indexOf("$"));
		String expected = "$"+userData.getEstimatedLimit().toString().replace("000", ",000");
		
		Reporter.log("Expected ::: "+expected);
		Reporter.log("Actual ::: "+actualEligibleAmount);
		assertEquals(actualEligibleAmount, expected);
		Reporter.log("Verification is completed successfully",true);
	} 
	
	@When(" Click on Start Over Button")
	public void Click_on_Start_Over_Button() throws CommonException {
		
		Reporter.log("Clicking on StartOver Button",true);
		muchBorrowPage.getStartOverButton().click();
	}
	
	@Then("Verify all fields are reset or not")
	public void Verify_all_fields_are_reset_or_not() {
		muchBorrowPage.validateAllFieldsAreResettedToDefaults(userData);
	}
}


