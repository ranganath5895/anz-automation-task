package com.automation.task.common.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigData {

	private String runLocal;
	private String gridHubHost;
	private Integer gridHubPort;
	private String chromeDriverInterfacePath;
	private String firefoxDriverInterfacePath;
	private String browserName;
	private String appHost;
}
