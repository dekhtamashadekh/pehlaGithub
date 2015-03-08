package com.github.selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

/**
 * @author 
 * 
 *         This is a TestNG listener class which takes screenshot at the time of
 *         failure
 * 
 */
public class TestScreenshotListener extends TestListenerAdapter {

	private static ResourceBundle _prop = ResourceBundle.getBundle("selenium");

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 * Here what happens at the time of test failure is changed by overriding
	 * onTestFailure method
	 */
	@Override
	public void onTestFailure(ITestResult tr) {

		System.out.println("current directory" + _prop.getString("jenkinsProjectName"));

		File screenshot = new File("../" + _prop.getString("jenkinsProjectName") + "/automation/testScreenshot", System.currentTimeMillis() + tr.getName() + ".png");
		if (!screenshot.exists()) {
			new File(screenshot.getParent()).mkdirs();
			try {
				screenshot.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			new FileOutputStream(screenshot).write(((TakesScreenshot) TestSeleniumBase.getDriverObject()).getScreenshotAs(OutputType.BYTES));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Written screenshot to " + screenshot.getAbsolutePath());

		String NewFileNamePath = "Screenshot <br/><a href='" + "testScreenshot/" + screenshot.getName() + "'><img width='200' src='" + "testScreenshot/" + screenshot.getName() + "'></a>";

		Reporter.log(NewFileNamePath);
	}

}