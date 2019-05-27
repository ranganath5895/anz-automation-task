package com.automation.task.common.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.automation.task.common.CommonException;

public class FileReadUtils {

	public static String readFileContents(String filePath) throws CommonException {
	
		File templateFile = new File(filePath);
		
		try {
			StringBuilder fileContents = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(templateFile));
			bufferedReader.lines().forEach(eachLine -> {
				fileContents.append(eachLine);
			});
			bufferedReader.close();
			
			return fileContents.toString();
			
		} catch (IOException e) {
			throw new CommonException("Exception while fetching the HTML Report Template File");
		}
			
	}
}
