package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * @author dtyagi Class to represent Concrete CP2 Add New Folder Page
 * 
 */
public class AddNewFolderPage extends LoadableComponent<AddNewFolderPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.ID, using = "addNewFolder")
	WebElement AddNewFolder;

	@FindBy(how = How.ID, using = "txtFolderName")
	WebElement FolderName;

	@FindBy(how = How.XPATH, using = "//button[text()='Add']")
	WebElement AddButton;

	private String addNewFolderPageTimeStamp;

	private Date addNewFolderPageTime = new Date();

	static Calendar cal = Calendar.getInstance();

	/**
	 * 
	 */
	public AddNewFolderPage() {

		cal.setTime(addNewFolderPageTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		addNewFolderPageTimeStamp = df.format(addNewFolderPageTime);

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
	 * @return Timestamp of this class
	 */
	public String getAddNewFolderPageTimeStamp() {

		return addNewFolderPageTimeStamp.toString();

	}

	/**
	 * Creates the CP2 folder
	 * 
	 */
	public void addCp2Folder() {

		this.FolderName.sendKeys("TestAutoFolder" + addNewFolderPageTimeStamp);

		this.AddButton.click();

	}

}