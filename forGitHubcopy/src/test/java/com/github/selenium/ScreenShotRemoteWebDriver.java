package com.github.selenium;

import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

// This is a class to give remote webdriver screenshot capability

public class ScreenShotRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {
	/**
	 * @param url
	 *            The url of the Selenium hub
	 * @param capabilities
	 *            The capabilities of the Selenium remote webdriver
	 */
	public ScreenShotRemoteWebDriver(URL url, DesiredCapabilities capabilities) {
		super(url, capabilities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.openqa.selenium.TakesScreenshot#getScreenshotAs(org.openqa.selenium
	 * .OutputType)
	 */
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
			return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
		}

		return null;
	}
}