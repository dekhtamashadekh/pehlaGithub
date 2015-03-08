package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
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
 * @author  Class to represent User Page
 * 
 */
public class UserPage extends LoadableComponent<UserPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.NAME, using = "search_firstname")
	WebElement SearchFirstname;

	@FindBy(how = How.NAME, using = "search_butt")
	WebElement SearchButton;

	@FindBy(how = How.LINK_TEXT, using = "Edit Details")
	WebElement EditDetails;

	@FindBy(how = How.LINK_TEXT, using = "Delete")
	WebElement Delete;

	@FindBy(how = How.XPATH, using = "//tbody/tr/td[@class='AdminDminor'][text()='Group:']/following-sibling::*")
	WebElement UserGroupText;

	private String userPageTimeStamp;

	private Date userPageTime = new Date();

	static Calendar cal = Calendar.getInstance();

	/**
	 * 
	 */
	public UserPage() {

		cal.setTime(userPageTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		userPageTimeStamp = df.format(userPageTime);

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
	 * @return time stamp for this class
	 */
	public String getUserGroupTimeStamp() {

		return userPageTimeStamp.toString();

	}

	/**
	 * Searches the user
	 * 
	 * @param name
	 */
	public void searchUser(String name) {

		this.SearchFirstname.sendKeys(name);

		this.SearchButton.click();
	}

	/**
	 * Deletes the user
	 * 
	 * @throws InterruptedException
	 */
	public void deleteUser() throws InterruptedException {

		this.Delete.click();

		Thread.sleep(5000);
		// Get a handle to the open alert, prompt or confirmation

		Alert alert = getDriverObject().switchTo().alert();

		// Get the text of the alert or prompt
		alert.getText();

		// And acknowledge the alert (equivalent to clicking "OK")
		alert.accept();
	}

	/**
	 * @return an instance of edit user page
	 */
	public EditUserPage getEditUserPage() {

		this.EditDetails.click();

		return new EditUserPage();
	}

	/**
	 * @return an String which is the name of this user group
	 */
	public String getUserGroupText() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		return this.UserGroupText.getText();

	}

	/**
	 * @param timeStamp
	 * @return a List of WebElements which matches the search criteria
	 */
	public List<WebElement> getNumberOfUsers(String timeStamp) {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		return getDriverObject().findElements(By.xpath("//tbody/tr/td[text()='autouser" + timeStamp + " autouser" + timeStamp + "']"));

	}

}
