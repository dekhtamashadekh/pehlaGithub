package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.github.selenium.AdvancedSelect;

/**
 * @author dtyagi Class to represent Concrete CP2 Add User Page
 * 
 */
public class AddUserPage extends LoadableComponent<AddUserPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.LINK_TEXT, using = "New user")
	WebElement NewUser;

	@FindBy(how = How.ID, using = "addUserUsername")
	WebElement UserName;

	@FindBy(how = How.ID, using = "addUserOrgID")
	WebElement UserOrgID;

	@FindBy(how = How.ID, using = "addUserFirstName")
	WebElement UserFirstName;

	@FindBy(how = How.ID, using = "addUserLastName")
	WebElement UserLastName;

	@FindBy(how = How.ID, using = "addUserEmail")
	WebElement UserEmail;

	@FindBy(how = How.ID, using = "addUserGroupID")
	WebElement UserGroupID;

	@FindBy(how = How.ID, using = "addUserSubmit")
	WebElement UserSubmit;

	@FindBy(how = How.CSS, using = "input[type='Button'][name='Finish'][value='Back']")
	WebElement BackButton;

	private String addUserPageTimeStamp;

	private Date addUserPageTime = new Date();

	static Calendar cal = Calendar.getInstance();

	/**
	 * 
	 */
	public AddUserPage() {

		cal.setTime(addUserPageTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		addUserPageTimeStamp = df.format(addUserPageTime);

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
	 * @return Time Stamp of this page
	 */
	public String getaddUserPageTimeStamp() {

		return addUserPageTimeStamp.toString();

	}

	/**
	 * This methods add a user
	 */
	public void addUser() {

		if (getDriverObject().findElements(By.id("addUserUsername")).size() > 0) {
			this.UserName.sendKeys("autouser" + addUserPageTimeStamp + "@autouser.com");
		} else {
			System.out.println("UserName field not present");
		}

		new AdvancedSelect(UserOrgID).selectByIndex(4);

		this.UserFirstName.sendKeys("autouser" + addUserPageTimeStamp);

		this.UserLastName.sendKeys("autouser" + addUserPageTimeStamp);

		this.UserEmail.sendKeys("autouser" + addUserPageTimeStamp + "@autouser.com");

		new AdvancedSelect(UserGroupID).selectByIndex(5);

		this.UserSubmit.click();

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		this.BackButton.click();
	}

}
