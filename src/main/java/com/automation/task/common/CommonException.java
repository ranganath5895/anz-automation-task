package com.automation.task.common;

/**
 * 
 * This is Common Exception Class which will be used in all Exception Handlers of Automation
 * 
 * @author Ranganath Chenna
 *
 */

public class CommonException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }
}
