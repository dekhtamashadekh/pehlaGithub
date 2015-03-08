package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author dtyagi Class to represent Test Zone Page
 * 
 */
public class TestZonePage extends LoadableComponent<TestZonePage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.ID, using = "addNewFolder")
	WebElement AddNewFolder;

	@FindBy(how = How.ID, using = "txtFolderName")
	WebElement FolderName;

	@FindBy(how = How.XPATH, using = "//button[text()='Add']")
	WebElement AddButton;

	private String testZonePageTimeStamp;

	private Date testZonePageTime = new Date();

	static Calendar cal = Calendar.getInstance();

	/**
	 * 
	 */
	public TestZonePage() {

		cal.setTime(testZonePageTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		testZonePageTimeStamp = df.format(testZonePageTime);

		PageFactory.initElements(getDriverObject(), this);

	}

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
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriverObject(), 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * @return
	 */
	public String getTestZonePageTimeStamp() {

		return testZonePageTimeStamp.toString();

	}

	/**
	 * Adds CP2 folder
	 * 
	 * @throws InterruptedException
	 */
	public void addCp2Folder() throws InterruptedException {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		Thread.sleep(300);

		this.AddNewFolder.click();

		this.FolderName.sendKeys("TestAutoFolder" + testZonePageTimeStamp);

		Thread.sleep(300);

		this.AddButton.click();

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

	}

	/**
	 * @return and instance of Add New Folder Page
	 */
	public AddNewFolderPage getAddNewFolderPage() {
		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		this.AddNewFolder.click();

		return new AddNewFolderPage();
	}

	/**
	 * @param timeStamp
	 * @return List of WebElements which matches the search criteria
	 */
	public List<WebElement> getFolderLink(String timeStamp) {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		return getDriverObject().findElements(By.partialLinkText("TestAutoFolder" + timeStamp));
	}

	/**
	 * @param timestamp
	 * @return an instance of Cp2FolderPage
	 */
	public Cp2FolderPage getCP2FolderPage(String timestamp) {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		//getDriverObject().findElement(By.partialLinkText("TestAutoFolder" + testZonePageTimeStamp)).click();
		getDriverObject().findElement(By.partialLinkText("TestAutoFolder" + timestamp)).click();

		return new Cp2FolderPage();
	}

}