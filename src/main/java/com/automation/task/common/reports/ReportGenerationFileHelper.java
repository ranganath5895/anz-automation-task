package com.automation.task.common.reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.automation.task.common.CommonException;
import com.automation.task.common.WebBrowser;
import com.automation.task.common.helpers.FileReadUtils;

/**
 * 
 * Custom Automation Report Generation Helper
 * 
 * @author Ranganath Chenna
 *
 */

public class ReportGenerationFileHelper {
	
	
	public static void writeContentInToFile(String fileName, String content) throws CommonException{

		File file = new File(fileName);
		try {
			if(!file.exists())
				file.createNewFile();

			FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(content);
			bw.close();
		}catch (IOException e) {
			throw new CommonException("Exception while Generating the file....", e.getCause());
		}

	}
	
	public static String copyScreentShot() throws CommonException{
	
		
		String screenshotsFolder = "Screenshots";
		
		String screenshotName =  Calendar.getInstance().getTimeInMillis()+"".trim() + ".png";
		
		File scrFile = ((TakesScreenshot) WebBrowser.getInstance())
				.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(screenshotsFolder+File.separator+screenshotName));
		} catch (IOException e) {
			throw new CommonException("Exception while Taking Screenshot the file....", e.getCause());
		}
		
		return screenshotName;
	   
	}
	
	public static String readHTMLReportTemplateContent() throws CommonException {
		
		 String htmlTemplateBody = FileReadUtils.readFileContents("src/main/resources/reportTemplate/HtmlReportTemplate.txt");
	  
		 return htmlTemplateBody;
	}
	
}
