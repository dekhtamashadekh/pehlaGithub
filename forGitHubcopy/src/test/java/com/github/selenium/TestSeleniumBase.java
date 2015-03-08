package com.github.selenium;

import static com.github.selenium.TestBrowserType.FIREFOX;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * @author 
 * 
 *         This is the master class where selenium( aka webdriver, aka driver)
 *         object is configured and a public getter method is provided so it can
 *         be accessed from other classes
 * 
 */
public class TestSeleniumBase {

	/**
	 * ResourceBundle class is used to read from properties file
	 * 
	 */
	private static ResourceBundle _prop = ResourceBundle.getBundle("selenium");

	private static TestBrowserType testBrowserType;

	/**
	 * Creating a synchronised list containing instances of WebDriver
	 * 
	 */
	private static List<WebDriver> webDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());

	/**
	 * Creating the ThreadLocal variable of type WebDriver
	 */
	private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>() {

		/*
		 * 
		 * 
		 * This is the method which sets the initial variable and state of our
		 * local thread, which is WebDriver in our case
		 * 
		 * (non-Javadoc)
		 * 
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected WebDriver initialValue() {
			WebDriver driver = null;

			try {
				driver = loadWebDriver();
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}

			//			driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
			//
			//			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			//
			//			driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

			webDrivers.add(driver);

			driver.manage().window().maximize();

			return driver;

		}
	};

	/**
	 * @param browser
	 *            The browser to be used. For example "firefox", "ie" etc
	 *            If no browser type is given, will default to firefox
	 */

	@BeforeSuite
	@Parameters(value = { "browser" })
	public static void setUpTest(String browser) {

		for (TestBrowserType testBrowser : TestBrowserType.values()) {

			if (testBrowser.toString().toLowerCase().equals(browser.toLowerCase())) {
				testBrowserType = testBrowser;
			}
		}
		if (null == testBrowserType) {
			System.err.println("Unknown browser specified, defaulting to 'Firefox'...");
			testBrowserType = FIREFOX;

		}
	}

	/**
	 * close all the browser instance created and stored in List webDrivers,
	 * after test suite is finished
	 */
	@AfterSuite(alwaysRun = true)
	public static void tearDown() {

		try {

			for (WebDriver driver : webDrivers) {

				if (driver != null) {

					driver.quit();

				} else
					System.out.println("driver is now null");
			}
		} catch (Exception e) {

			System.out.println("Tear down failed " + e.getMessage());

		}

	}

	/**
	 * Clears the cookies after each test is run, so every test is a new journey
	 */
	@AfterMethod(alwaysRun = true)
	public static void clearCookies() {

		try {

			getDriverObject().manage().deleteAllCookies();

		} catch (org.openqa.selenium.UnhandledAlertException o){
			System.out.println(o.getAlertText());
		}
		
		catch (Exception e) {

			System.out.println("Clear cookies failed " + e.getMessage());

		}

	}

	/**
	 * Closing the WebDriver after each class. This is basically duplication of
	 * the work done
	 * in @AfterSuite but it's required to close the webdriver after the class
	 * otherwise thread pool limit is breached and tests are stuck
	 */
	@AfterClass(alwaysRun = true)
	public static void closeDriver() {

		try {

			if (getDriverObject() != null) {

				getDriverObject().quit();
			}

			else {
				System.out.println("getDriverObject() is null ");
			}
		} catch (Exception e) {

			System.out.println("CloseDriver failed " + e.getMessage());

		}

	}

	/**
	 * @return WebDriver for thread calling it is returned.
	 * 
	 */
	public static WebDriver getDriverObject() {

		return driverForThread.get();

	}

	/**
	 * @param capabilityType
	 *            The browser used for testing. For example IE, Chrome etc
	 * @return Capabilities or configurations based on browser type
	 */
	private static DesiredCapabilities generateDesiredCapabilities(TestBrowserType capabilityType) {

		DesiredCapabilities capabilities;

		switch (capabilityType) {
			case IE:
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				capabilities.setCapability("requireWindowFocus", true);
				break;

			case SAFARI:
				capabilities = DesiredCapabilities.safari();
				capabilities.setCapability("safari.cleanSession", true);
				break;

			case OPERA:
				capabilities = DesiredCapabilities.opera();
				capabilities.setCapability("opera.arguments", "-nowin -nomail");
				break;

			case GHOSTDRIVER:
				capabilities = DesiredCapabilities.phantomjs();
				capabilities.setCapability("takesScreenshot", true);

				break;

			case CHROME:
				capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
				HashMap<String, String> chromePreferences = new HashMap<String, String>();
				chromePreferences.put("profile.password_manager_enabled", "false");
				capabilities.setCapability("chrome.prefs", chromePreferences);
				break;

			case FIREFOX:
			default:
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
				firefoxProfile.setPreference("browser.download.dir", "/tmp/selenium-talk");
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("firefox_profile", firefoxProfile);
		}

		return capabilities;

	}

	/**
	 * @return This returns the WebDriver to be used in ThreadLocal object
	 * @throws MalformedURLException
	 *             Throws this MalformedURLException if the URL doesn't fulfil
	 *             HTTP protocol
	 */
	private static WebDriver loadWebDriver() throws MalformedURLException {

		switch (testBrowserType) {

			case FIREFOX:

				return new ScreenShotRemoteWebDriver(new URL(_prop.getString("URLSeleniumHub")), generateDesiredCapabilities(testBrowserType));

			case CHROME:

				return new ScreenShotRemoteWebDriver(new URL(_prop.getString("URLSeleniumHub")), generateDesiredCapabilities(testBrowserType));

			case IE:

				return new ScreenShotRemoteWebDriver(new URL(_prop.getString("URLSeleniumHub")), generateDesiredCapabilities(testBrowserType));

			case SAFARI:

				return new ScreenShotRemoteWebDriver(new URL(_prop.getString("URLSeleniumHub")), generateDesiredCapabilities(testBrowserType));

			case OPERA:

				return new ScreenShotRemoteWebDriver(new URL(_prop.getString("URLSeleniumHub")), generateDesiredCapabilities(testBrowserType));

			case GHOSTDRIVER:
			default:

				return new ScreenShotRemoteWebDriver(new URL(_prop.getString("URLSeleniumHub")), generateDesiredCapabilities(testBrowserType));

		}

	}
	
	protected final Logger log;

	public TestSeleniumBase() {
	log = LoggerFactory.getLogger(getClass());
	}

}
