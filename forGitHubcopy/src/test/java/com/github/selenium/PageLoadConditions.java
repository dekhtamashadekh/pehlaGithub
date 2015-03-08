package com.github.selenium;

import static com.github.selenium.TestSeleniumBase.getDriverObject;
import static com.github.selenium.Utility.UploadJqueryToWebPage.loadScript;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author 
 * 
 */
public abstract class PageLoadConditions extends LoadableComponent<PageLoadConditions> {
	
	private String AddNewPageTimeStamp;

	private Date AddNewPageTime = new Date();

	static Calendar cal = Calendar.getInstance();
	
	private static WebDriverWait globalWait = new WebDriverWait(getDriverObject(), 2);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	protected void isLoaded() throws Error {
		try {
			loadScript();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {

				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		ExpectedCondition<Boolean> elementLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				Boolean pageIsReadyToUse = false;

				int maxCount = 2;

				for (int count = 0; count < maxCount; count++) {

					if ((!allLoaded().contains(false)) && (!allLoaded().contains(null)) && (allLoaded().size() > 0)) {

						pageIsReadyToUse = true;
					} else {

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							System.out.println("I got interupped byu another thread");
							e.printStackTrace();
						}
						pageIsReadyToUse = false;
					}

				}

				return pageIsReadyToUse;

			}
		};
		ExpectedCondition<Boolean> activeAjaxCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {

				Long value = 0L;

				return ((JavascriptExecutor) driver).executeScript("return jQuery.active;").equals(value);
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriverObject(), 30);

		wait.until(pageLoadCondition);

		wait.until(elementLoadCondition);

		wait.until(activeAjaxCondition);

	}

	/**
	 * @return
	 * All Page objects need to implement this
	 */
	public abstract List<Boolean> allLoaded();
	
	public String getTimeStamp() {
		
		cal.setTime(AddNewPageTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		AddNewPageTimeStamp = df.format(AddNewPageTime);
		
		return AddNewPageTimeStamp;
		
	}
	
	public WebDriverWait getWait(){
		
		return globalWait;
	}

}