package com.automation.task.tests.utils;

import org.openqa.selenium.support.PageFactory;

import com.automation.task.common.CommonException;
import com.automation.task.common.WebBrowser;
import com.automation.task.common.helpers.ConfigReader;
import com.automation.task.common.reports.ReportLogger;
import com.automation.task.pages.MuchBorrowPage;

public class NavigationsUtil {

	public MuchBorrowPage navigateToBorrowPage() throws CommonException {
		
		String relativePageURLforTestCase = "/personal/home-loans/calculators-tools/much-borrow/";
		String urlToBorrowPage = ConfigReader.getConfigData().getAppHost() +  relativePageURLforTestCase;
		
		WebBrowser.getInstance().get(urlToBorrowPage);
		
		MuchBorrowPage muchBorrowPage = PageFactory.initElements(WebBrowser.getInstance(), MuchBorrowPage.class);
		
		if(muchBorrowPage.getBorrowCalculateButton().isEnabled() && muchBorrowPage.getBorrowCalculateButton().isDisplayed()) {
			ReportLogger.log(WebBrowser.getInstance().getTitle()+" Page is Opened Successfully", true);
			return muchBorrowPage;
		}
	
		ReportLogger.log("Home Load Borrowing Power Caluculator Page is Failed to Open", true);
		throw new CommonException("Failed to Open the Expected Page");
	
	}
}
