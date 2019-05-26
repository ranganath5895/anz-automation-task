package com.automation.task.common.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import com.automation.task.common.CommonException;
import com.automation.task.common.constants.ConfigData;
import com.automation.task.common.constants.ConfigParams;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * This Class will help read the config.properties values.
 * 
 * @author Ranganath Chenna
 *
 */
@Slf4j
public class ConfigReader {

	private static ConfigData configData;
	
	/**
	 * Reading the config data 
	 * @throws CommonException 
	 *
	 */
	public static void readAndSetConfigData() throws CommonException{
		
		Properties props = new Properties();
		
		try {
			File file = new File("src/test/resources/automation.properties");
			props.load(new FileInputStream(file.getAbsolutePath()));
		} catch (IOException | NullPointerException e) {
			throw new CommonException("[Config File Missed -- Error] -- Please provide the config file in Location : src/test/resources/automation.properties"); 
		}
		
		String browser = props.getProperty(ConfigParams.BROWSER_NAME.getPropertyKey());
		String appHost = props.getProperty(ConfigParams.APP_HOST.getPropertyKey());
		String runlocally = props.getProperty(ConfigParams.RUN_LOCAL.getPropertyKey());
		String gridHubHost = props.getProperty(ConfigParams.GRID_HUB_HOST.getPropertyKey());
		Integer gridHubPort = 0;
		try {
			gridHubPort = Integer.parseInt(props.getProperty(ConfigParams.GRID_HUB_PORT.getPropertyKey()).toString());
		}catch (NumberFormatException e) {}
		String chromeDriverInterfacePath = props.getProperty(ConfigParams.CHROME_DRIVER_EXE_PATH.getPropertyKey());
		String firefoxDriverInterfacePath = props.getProperty(ConfigParams.FIREFOX_DRIVER_EXE_PATH.getPropertyKey());
		
		configData = new ConfigData( 	runlocally,
										gridHubHost,
										gridHubPort,
										chromeDriverInterfacePath,
										firefoxDriverInterfacePath,
										browser,
										appHost);
		
		// Reading VmArgs and Update the Config Values
		if( getSystemProperty(ConfigParams.BROWSER_NAME.getPropertyKey()).isPresent() ) {
			configData.setBrowserName(getSystemProperty(ConfigParams.BROWSER_NAME.getPropertyKey()).get());
		}
		
		if( getSystemProperty(ConfigParams.APP_HOST.getPropertyKey()).isPresent() ) {
			configData.setAppHost(getSystemProperty(ConfigParams.APP_HOST.getPropertyKey()).get());
		}
		
		if( getSystemProperty(ConfigParams.RUN_LOCAL.getPropertyKey()).isPresent() ) {
			configData.setRunLocal(getSystemProperty(ConfigParams.RUN_LOCAL.getPropertyKey()).get());
		}
		
		if( getSystemProperty(ConfigParams.GRID_HUB_HOST.getPropertyKey()).isPresent() ) {
			configData.setGridHubHost(getSystemProperty(ConfigParams.GRID_HUB_HOST.getPropertyKey()).get());
		}
		
		if( getSystemProperty(ConfigParams.GRID_HUB_PORT.getPropertyKey()).isPresent() ) {
			String gridHubPortVmArg = getSystemProperty(ConfigParams.GRID_HUB_PORT.getPropertyKey()).get();
			configData.setGridHubPort(Integer.parseInt(gridHubPortVmArg));
		}
		
		if( getSystemProperty(ConfigParams.CHROME_DRIVER_EXE_PATH.getPropertyKey()).isPresent() ) {
			configData.setChromeDriverInterfacePath(getSystemProperty(ConfigParams.CHROME_DRIVER_EXE_PATH.getPropertyKey()).get());
		}
		
		if( getSystemProperty(ConfigParams.FIREFOX_DRIVER_EXE_PATH.getPropertyKey()).isPresent() ) {
			configData.setFirefoxDriverInterfacePath(getSystemProperty(ConfigParams.FIREFOX_DRIVER_EXE_PATH.getPropertyKey()).get());
		}
	}

	public static ConfigData getConfigData() {
		return configData;
	}
	
	private static Optional<String> getSystemProperty(String key) {
		logger.info("Fetching key {} from VM Args",key);
		String value = System.getProperty(key);
	    if (null == value || value.isEmpty()) {
	        return Optional.empty();
	    }
	    return Optional.of(value);
	}
}
