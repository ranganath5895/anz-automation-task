Feature: Checking the User Eligibility
  I want to test the user eligibie loan amount
  Scenario: Checking With Valid Sets Of Data
    Given Fill the User Details
    |noOfDependents|primaryIncome|otherIncome|livingExpenses|currentHomeLoanRepayments|otherLoanPayments|otherCommitments|totalCreditCardLimit|estimatedLimit|
    |0|80000|10000|500|0|100|0|10000|479000|
    When Submit the application form
    Then check the amount is equal to expected

  Scenario: Checking With Valid Sets Of Data
    Given Fill the User Details
    |noOfDependents|primaryIncome|otherIncome|livingExpenses|currentHomeLoanRepayments|otherLoanPayments|otherCommitments|totalCreditCardLimit|estimatedLimit|
    |0|82600|10000|500|0|100|0|10000|479000|
    When Submit the application form
    Then check the amount is equal to expected
  
  Scenario: Fill only Living Expenses and Check Loan Amount
    Given Add Living Expenses
    When Submit the form With Less Fields
    Then Check for the error Message
    
	Scenario: Checking the functionality of StartOver Button
    Given Fill the User Details
    |noOfDependents|primaryIncome|otherIncome|livingExpenses|currentHomeLoanRepayments|otherLoanPayments|otherCommitments|totalCreditCardLimit|estimatedLimit|
    |0|80000|10000|500|0|100|0|10000|479000|
    When Submit the application form
    When Click on Start Over Button
    Then Verify all fields are reset or not
    