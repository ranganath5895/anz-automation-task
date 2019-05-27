package com.automation.cucumber.testng.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.automation.cucumber.testng.CommonException;
import com.automation.cucumber.testng.testdata.UserData;

import lombok.Data;


@Data
public class MuchBorrowPage {

	@FindBy(how=How.ID,using="application_type_single")
	private WebElement borrowApplicationTypeSingle;
	
	@FindBy(how=How.XPATH,using="//select[@title='Number of dependants']")
	private WebElement noOfDependents;
	
	@FindBy(how=How.ID,using="borrow_type_home")
	private WebElement propertyTypeToBuy;
	
	@FindBy(how=How.XPATH,using="//input[@aria-labelledby='q2q1']")
	private WebElement primaryIncomeTextField;
	
	@FindBy(how=How.XPATH,using="//input[@aria-labelledby='q2q2']")
	private WebElement otherIncomeTextField;
	
	@FindBy(how=How.ID,using="expenses")
	private WebElement livingExpensesTextField;
	
	@FindBy(how=How.ID,using="homeloans")
	private WebElement currentHomeLoansRePaymentTextField;
	
	@FindBy(how=How.ID,using="otherloans")
	private WebElement otherLoanRepaymentsTextField;
	
	@FindBy(how=How.XPATH,using="//input[@aria-labelledby='q3q4']")
	private WebElement otherCommitmentsTextField;
	
	@FindBy(how=How.ID,using="credit")
	private WebElement totalCrediCardLimits;
	
	@FindBy(how=How.XPATH,using="//button[contains(@class,'btn--borrow__calculate')]")
	private WebElement borrowCalculateButton;
	
	@FindBy(how=How.XPATH,using="//span[@class='borrow__result__text' and @aria-live='assertive']")
	private WebElement eligibleAmountTextField;
	
	@FindBy(how=How.XPATH,using="//span[@class='borrow__error__text']")
	private WebElement borrowErrorMessageTextLabel;
	
	@FindBy(how=How.XPATH,using="//button[@class='start-over']")
	private WebElement startOverButton;
	
	/**
	 * 
	 * This will fill the User Details in Loan Application form.
	 * 
	 * @param userData {@link UserData}}
	 * @throws CommonException
	 */
	
	public void fillDataForHomeLoan(UserData userData) throws CommonException {
		
		getBorrowApplicationTypeSingle().click();;
		
		Select selectOfNoOfDependents = new Select(noOfDependents);
		selectOfNoOfDependents.selectByVisibleText(userData.getNoOfDependents().toString());
		getPropertyTypeToBuy().click();;
		getPrimaryIncomeTextField().sendKeys(userData.getPrimaryIncome().toString());
		getOtherIncomeTextField().sendKeys(userData.getOtherIncome().toString());
		getLivingExpensesTextField().sendKeys(userData.getLivingExpenses().toString());
		getCurrentHomeLoansRePaymentTextField().sendKeys(userData.getCurrentHomeLoanRepayments().toString());
		getOtherLoanRepaymentsTextField().sendKeys(userData.getOtherLoanPayments().toString());
		getOtherCommitmentsTextField().sendKeys(userData.getOtherCommitments().toString());
		getTotalCrediCardLimits().sendKeys(userData.getTotalCreditCardLimit().toString());

		Reporter.log(" Personal Details for Loan Eligibility Check Entered Successfully");
	}
	
	public void validateAllFieldsAreResettedToDefaults(UserData userData) {
		
		
	}
}
