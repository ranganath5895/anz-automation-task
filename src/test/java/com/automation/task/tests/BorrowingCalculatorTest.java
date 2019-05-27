package com.automation.task.tests;

import static org.testng.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.automation.task.common.CommonException;
import com.automation.task.common.TestReportListener;
import com.automation.task.common.helpers.FileReadUtils;
import com.automation.task.common.helpers.TestDataHelper;
import com.automation.task.common.reports.ReportLogger;
import com.automation.task.pages.MuchBorrowPage;
import com.automation.task.testdata.UserData;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * Automating the borrowing calculator 
 * 
 * @author Ranganath Chenna
 */
@Listeners(TestReportListener.class)
public class BorrowingCalculatorTest extends BaseUITest{

	@DataProvider(name="usersData")
	public <T> Object[][] getUsersData() throws CommonException{
	
		  String jsonBody = FileReadUtils.readFileContents("src/test/resources/testData/borrowHomeLoanScenarios.json");
	      List<UserData> usersData = TestDataHelper.getObjectsFromString(jsonBody, UserData.class);
	      
	      Object[][] data = new Object[usersData.size()][1];
	      
	      for(int i = 0; i < usersData.size() ; i++) {
	    	  data[i][0] = usersData.get(i);
	      }
	      
	      return data;
	}
	
	/**
	 * 
	 *  TEST1
	 * 
	 *  Fill the details and calculate the Eligible Borrow Amount
	 * @throws CommonException 
	 */
	@Test(dataProvider="usersData")
	public void caluculateEligibleBorrowAmount(UserData userData) throws CommonException {
		
		MuchBorrowPage muchBorrowPage = navigationsUtil.navigateToBorrowPage();
		muchBorrowPage.fillDataForHomeLoan(userData);
				
		muchBorrowPage.getBorrowCalculateButton().click();
		
		String actualEligibleAmount = muchBorrowPage.getEligibleAmountTextField().getText();
		ReportLogger.log("Eligible Amount :: "+actualEligibleAmount,true);
		
		
		actualEligibleAmount = actualEligibleAmount.substring(actualEligibleAmount.indexOf("$"));
		String expected = "$"+userData.getEstimatedLimit().toString().replace("000", ",000");
		
		assertEquals(actualEligibleAmount, expected);
	}
	
	
	/**
	 * 
	 * TEST2
	 * 
	 * Clears the loan eligibility form and verify all fields are cleared which was filled in {@link BorrowingCalculatorTest#caluculateEligibleBorrowAmount()}
	 * @throws FileNotFoundException 
	 * @throws JsonIOException 
	 * @throws JsonSyntaxException 
	 * @throws CommonException 
	 */
	@Test
	public void clearLoanEligibilityForm() throws CommonException {
		
	   String jsonBody = FileReadUtils.readFileContents("src/test/resources/testData/borrowHomeLoanScenarios.json");
	   List<UserData> usersData = TestDataHelper.getObjectsFromString(jsonBody, UserData.class);
	        
       MuchBorrowPage muchBorrowPage = navigationsUtil.navigateToBorrowPage();
	   muchBorrowPage.fillDataForHomeLoan(usersData.get(0));
	   muchBorrowPage.getBorrowCalculateButton().click();
	   
	   muchBorrowPage.getStartOverButton().click();
	   ReportLogger.log("After Cleaing the Application Form", true);
	   
	   muchBorrowPage.validateAllFieldsAreResettedToDefaults(usersData.get(0));
	   
	}
	
	
	/**
	 * 
	 * TEST3
	 * 
	 * Fill only Living Expenses amount and verify the error Message
	 * @throws CommonException 
	 * @throws FileNotFoundException 
	 * @throws JsonIOException 
	 * @throws JsonSyntaxException 
	 */
	@Test
	public void fillOnlyLivingExpensesAndCheck() throws CommonException {
	
	   String expectedErrorMessage = "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641.";
		
       MuchBorrowPage muchBorrowPage = navigationsUtil.navigateToBorrowPage();
	   muchBorrowPage.getLivingExpensesTextField().sendKeys("1");
	   
	   muchBorrowPage.getBorrowCalculateButton().click();
	   
	   String actualErrorMessage = muchBorrowPage.getBorrowErrorMessageTextLabel().getText();
	   ReportLogger.log("Actual Error Message :: "+actualErrorMessage);
	   ReportLogger.log("With Only Living Expenses -- Form Submission", true);
	   assertEquals(actualErrorMessage, expectedErrorMessage);
	}
}
