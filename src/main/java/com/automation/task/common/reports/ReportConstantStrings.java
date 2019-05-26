package com.automation.task.common.reports;


public class ReportConstantStrings {


	private static String templateRowContent;
	private static String completeRowContent;
	private static String SNO = "#SNO#";
	private static String CLASSNAME = "#CLASSNAME#";
	private static String METHODNAME = "#METHODNAME#";
	private static String STATUS = "#STATUS#";
	private static String PARAMS = "#PARAMS#";
	private static String STACKTRACES = "#STACKTRACES#";
	private static String LOGS	=	"#LOGS#";
	private static int testCounter = 0;

	public static void setTemplateRowContent(String className,String methodName,int status,String params,String stacktraces, String logs) {
		
		testCounter = testCounter + 1;
		
		String testResult = "";
		
		if(status == 1){
			testResult = "PASSED";
		}
		else if(status == 2){
			testResult = "FAILED";
		}
		else if(status == 3){
			testResult = "SKIPPED";
		}
		else{
			testResult = "UNKNOWN";
		}
		
		String rowTeplateContentData = getTemplateRowContent();
		rowTeplateContentData = rowTeplateContentData.replaceAll(SNO, String.valueOf(testCounter));
		rowTeplateContentData = rowTeplateContentData.replaceAll(CLASSNAME, className);
		rowTeplateContentData = rowTeplateContentData.replaceAll(METHODNAME, methodName);
		rowTeplateContentData = rowTeplateContentData.replaceAll(STATUS, testResult);
		if( ! params.contains("Ljava.lang.Object")){
			rowTeplateContentData = rowTeplateContentData.replaceAll(PARAMS, params);
		}
		else{
			rowTeplateContentData = rowTeplateContentData.replaceAll(PARAMS, "");
		}
		
		if(stacktraces != null){	
			rowTeplateContentData = rowTeplateContentData.replace(STACKTRACES, stacktraces);
		}
		else{
			rowTeplateContentData = rowTeplateContentData.replaceAll(STACKTRACES, "");
		}
		
		if(logs != null){
			rowTeplateContentData = rowTeplateContentData.replaceAll(LOGS, logs);
		}
		else{
			rowTeplateContentData = rowTeplateContentData.replaceAll(LOGS, "");
		}
		
		setCompleteRowContent(rowTeplateContentData);
	}
	
	/**
	 * Gets the template row content.
	 *
	 * @return the template row content
	 */
	public static String getTemplateRowContent() {
		templateRowContent = "<tr class='RowToClick'><td>" + SNO + "</td><td>" + CLASSNAME
				+ "</td><td>" + METHODNAME + "</td><td>" + STATUS + "</td><td>" + PARAMS + "</td></tr>"
				+"<tr style='display: none;'> <td> </td> <td>"
				+ STACKTRACES + "</td><td>"
				+ LOGS + "</td> </tr>";
		return templateRowContent;
	}
	
	public static String getCompleteRowContent() {
		return completeRowContent;
	}
	
	/**
	 * 
	 * Sets the complete row content.
	 *
	 * @param newCompleteRowcontent the new complete row content
	 */
	private static void setCompleteRowContent(String newCompleteRowcontent) {

		if ((null != completeRowContent) && (completeRowContent.length() > 0)) {
			completeRowContent = completeRowContent + newCompleteRowcontent;
		} else {
			completeRowContent = newCompleteRowcontent;
		}
	}
}
