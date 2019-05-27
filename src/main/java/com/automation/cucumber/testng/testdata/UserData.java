package com.automation.cucumber.testng.testdata;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserData {

	private Long noOfDependents;
	private Long primaryIncome;
	private Long otherIncome;
	private Long livingExpenses;
	private Long currentHomeLoanRepayments;
	private Long otherLoanPayments;
	private Long otherCommitments;
	private Long totalCreditCardLimit;
	private Long estimatedLimit;
}
