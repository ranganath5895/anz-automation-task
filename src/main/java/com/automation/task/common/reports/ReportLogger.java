package com.automation.task.common.reports;

import java.util.Objects;
import java.util.regex.Matcher;

import org.testng.Reporter;

import com.automation.task.common.CommonException;

/**
 * 
 * Consists of Methods to Log the Verifications and Steps.
 * 
 * @author Ranganath Chenna
 *
 */
public class ReportLogger {

    public static void log(String logMessage,boolean...takeScreenshot) throws CommonException{
    
    	logMessage = Matcher.quoteReplacement(logMessage); //This is to handle $ symbol in Text
    	
    	if(Objects.nonNull(takeScreenshot) && takeScreenshot.length > 0){
    	
	    	String screenshotFileName = ReportGenerationFileHelper.copyScreentShot();
	    	
	    	String screenshotLink = generateScreenshotLink("./Screenshots/"+screenshotFileName,screenshotFileName.replace(".png", "").trim());
	    	
	    	logMessage = logMessage + "&nbsp;&nbsp;" + screenshotLink.trim();
	    	
	    	Reporter.log(logMessage);
    	
    	}
    	else {
    		
    		Reporter.log(logMessage);
    	}
    	
    }
    
    private static String generateScreenshotLink(String srcOfScreenshot,String screenshotName) {
    	
    	String templateContentForScreenshot = "<div id="+screenshotName+" class=\"overlay\"><a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav("+screenshotName+")\">&times;</a><div class=\"overlay-content\" >\n" + 
    			"                            <img style='height: auto; width: 90%;' src=\""+srcOfScreenshot+"\" />\n" + 
    			"                        </div>\n" + 
    			"                      </div>\n" + 
    			"                      <u>\n" + 
    			"                        <span style=\"font-size:15px;cursor:pointer\" onclick=\"openNav("+screenshotName+")\">Screenshot</span> \n" + 
    			"                      </u>";
    	return templateContentForScreenshot;
    	
    }
}
