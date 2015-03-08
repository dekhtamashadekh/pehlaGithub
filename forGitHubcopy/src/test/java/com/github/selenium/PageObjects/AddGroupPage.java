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
 * @author dtyagi Class to represent  CP2 Add Group page
 * 
 */
public class AddGroupPage extends LoadableComponent<AddGroupPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.ID, using = "name")
	WebElement UserGroupName;

	@FindBy(how = How.ID, using = "description")
	WebElement UserGroupDescription;

	@FindBy(how = How.XPATH, using = "//button[@type='button']")
	WebElement UserGroupSave;

	@FindBy(how = How.XPATH, using = "(//button[@type='button'])[3]")
	WebElement UserGroupOk;

	@FindBy(how = How.CSS, using = "#deleteGroup > span.ui-button-text")
	WebElement DeleteUserGroup;

	@FindBy(how = How.XPATH, using = "(//button[@type='button'])[4]")
	WebElement DeleteUserGroupConfirm;

	@FindBy(how = How.XPATH, using = "//span[contains (text(), 'OK' ) and contains (@class, 'ui-button-text')]")
	WebElement DeleteUserGroupOk;

	private String userGroupTimeStamp;

	private Date userGroupTime = new Date();

	static Calendar cal = Calendar.getInstance();

	/**
	 * 
	 */
	public AddGroupPage() {

		cal.setTime(userGroupTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		userGroupTimeStamp = df.format(userGroupTime);

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
	 * @return This returns the timestamp of this class
	 */
	public String getUserGroupTimeStamp() {

		return userGroupTimeStamp.toString();

	}

	/**
	 * 
	 * This method add the user group
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void addUserGroup() throws InterruptedException {

		this.UserGroupName.clear();

		this.UserGroupName.sendKeys("testautogroup" + userGroupTimeStamp);

		this.UserGroupDescription.clear();

		this.UserGroupDescription.sendKeys("testautogroup" + userGroupTimeStamp);

		this.UserGroupSave.click();

		Thread.sleep(100);

		this.UserGroupOk.click();

		Thread.sleep(100);

	}

	/**
	 * This method add delete the user group
	 */
	public void deleteUserGroup() {

		getDriverObject().findElement(By.linkText("testautogroup" + userGroupTimeStamp)).click();

		this.DeleteUserGroup.click();

		this.DeleteUserGroupConfirm.click();

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		this.DeleteUserGroupOk.click();

	}

	/**
	 * This method gets a list of WebElements matching the name of usergroup
	 * 
	 * @return List of WebElement
	 */
	public List<WebElement> getUserGroup() {

		return getDriverObject().findElements(By.linkText("testautogroup" + userGroupTimeStamp));

	}

}
