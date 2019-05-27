package com.automation.task.common.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.MethodHelper;

/**
 * The Class TestNGHelper.
 */
public class TestNGHelper extends  MethodHelper{

	/**
	 * Gets the dependent methods.
	 *
	 * @param testResult the test result
	 * @return the dependent methods
	 */
	public static ITestNGMethod[] getDependentMethods(ITestResult testResult){
		ITestNGMethod[] dependentMethods = findDependedUponMethods(testResult.getMethod(),testResult.getTestContext().getAllTestMethods());
		return dependentMethods;
	}
	
	/**
	 * Helper method to convert the StackTrace information into a String.
	 * 
	 * @param aThrowable
	 *            - the {@link Throwable} object
	 * @return the String representation of the stack trace information for the {@link Throwable} object specified.
	 */
	public static String getStackTraceInfo(Throwable aThrowable) {

		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
	
	/**
	 * Gets the stack traces.
	 *
	 * @param testResult the test result
	 * @return the stack traces
	 */
	public static String getStackTraces(ITestResult testResult){
		
		String stackTraceData = null;
		
		if( testResult.getMethod().isTest() && testResult.getStatus() == 3){
			
			
			ITestNGMethod[] dependentMethods = getDependentMethods(testResult);
			
			if( (dependentMethods.length == 0)){
				
				for(ITestResult eachOne :  testResult.getTestContext().getFailedConfigurations().getAllResults()){
					
					Throwable e = eachOne.getThrowable();
					
					if (e != null) {
						
						final Writer result = new StringWriter();
						final PrintWriter printWriter = new PrintWriter(result);
						e.printStackTrace(printWriter);
						stackTraceData = TestNGHelper.getStackTraceInfo(e);
						
					}
				 }
			} 
			else{
				
				Throwable e = testResult.getThrowable();
				
				if (e != null) {
				
					stackTraceData = TestNGHelper.getStackTraceInfo(e);
					
				
				}
			}
			
			
			if(  (testResult.getTestContext().getFailedConfigurations().getAllResults().size() == 0) ){
				
				Throwable e = testResult.getThrowable();
				
				if (e != null) {
				
					stackTraceData = getStackTraceInfo(e);
				}
			}
			
		}
		else {
			Throwable e = testResult.getThrowable();
			
			if (e != null) {
			
				stackTraceData = getStackTraceInfo(e);
				
			}
		}
		
		return stackTraceData;
	}

}
