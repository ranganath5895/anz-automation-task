package com.automation.task.common;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.automation.task.common.helpers.ConfigReader;
import com.automation.task.common.helpers.TestNGHelper;
import com.automation.task.common.reports.ReportConstantStrings;
import com.automation.task.common.reports.ReportGenerationFileHelper;

public class TestReportListener implements ITestListener, IInvokedMethodListener{

	@Override
	public void onStart(ITestContext context) {
		
		try {
			ConfigReader.readAndSetConfigData();
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		if (method.isTestMethod()) {

			WebDriver driver = null;
			try {
				driver = StartBrowserInstance.getWebDriverInstance();
			} catch (CommonException e) {
				Assert.fail("Failed to Launch Browser", e);
			}
			WebBrowser.setWebDriver(driver);

		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		if (method.isTestMethod()) {
			setCustomReportData(testResult);
		}
		
		if (WebBrowser.getInstance() != null) {
			WebBrowser.getInstance().quit();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
	
		String oldtext = null;;
		try {
			oldtext = ReportGenerationFileHelper.readHTMLReportTemplateContent();
		} catch (CommonException e) {
			Assert.fail("Error while generating the report...",e.getCause());
		}

		try {
			String serviceLogsContent = ReportConstantStrings.getCompleteRowContent();
			if (serviceLogsContent != null) {
				String newtext = oldtext.replace("#content#", serviceLogsContent);
				String fileName = "automation-report.html";
				ReportGenerationFileHelper.writeContentInToFile(fileName, newtext);
			} else {
				System.out.println("Error while retriving the Logs");
			}
		} catch (Exception ex) {
		}
		
	}
	
	private void setCustomReportData(ITestResult testResult) {

		ITestNGMethod testmethod = testResult.getMethod();
		String canonicalClassName = testResult.getInstanceName();
		String methodName = testmethod.getMethodName();
		
		String parameters = "";
		if(testResult.getParameters().length > 0) {
			parameters = testResult.getParameters()[0].toString();
		}

		String statckTraces = TestNGHelper.getStackTraces(testResult);
		String testLogs = constructTableForTestCaseLogs(Reporter.getOutput(testResult));
		ReportConstantStrings.setTemplateRowContent(canonicalClassName, methodName, testResult.getStatus(), parameters,
				statckTraces, testLogs);

	}

   private String constructTableForTestCaseLogs(List<String> testCaseLogs) {
	   
	   StringBuilder stringBuilderForTestCaseLogs = new StringBuilder();
	   
	   int stepCounter = 1;
	   
	   for(String eachTestCaseLog : testCaseLogs){
		   stringBuilderForTestCaseLogs.append("<br>");
		   stringBuilderForTestCaseLogs.append("&nbsp; "+stepCounter+": &nbsp;");
		   stringBuilderForTestCaseLogs.append(eachTestCaseLog);
		   stepCounter = stepCounter + 1;
	   }
	   
	   return stringBuilderForTestCaseLogs.toString();
   }	
}
