package com.github.selenium;

/**
 * @author dtyagi
 *         This is class which stores all the browser types as enums and pass
 *         them to class configuring the selenium object to execute tests
 */
public enum TestBrowserType {

	FIREFOX("firefox"),
	CHROME("chrome"),
	IE("ie"),
	SAFARI("safari"),
	OPERA("opera"),
	GHOSTDRIVER("ghostdriver"),
	REMOTE("remote"),
	HTMLUNIT("htmlunit");

	private final String browser;

	TestBrowserType(String browser) {
		this.browser = browser;
	}

	/**
	 * @return The selected browser is returned if it matches the browser enum
	 *         list
	 */
	public String getBrowser() {
		return browser;
	}

}