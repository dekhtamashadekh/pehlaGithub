package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

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
 * @author dtyagi Class to represent Concrete CP2 Admin Page
 * 
 */
public class ConcreteAdminPage extends LoadableComponent<ConcreteAdminPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.LINK_TEXT, using = "User Management")
	WebElement UserManagement;

	@FindBy(how = How.LINK_TEXT, using = "New user")
	WebElement AddNewUser;

	@FindBy(how = How.LINK_TEXT, using = "User groups")
	WebElement UserGroups;

	@FindBy(how = How.CSS, using = "span.ui-button-text")
	WebElement AddNewGroup;

	@FindBy(how = How.LINK_TEXT, using = "Users")
	WebElement Users;

	/**
	 * 
	 */
	public ConcreteAdminPage() {
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
	 * @return an instance of Add User Group page
	 */
	public AddGroupPage getAddGroupPage() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		this.UserManagement.click();

		this.UserGroups.click();

		this.AddNewGroup.click();

		return new AddGroupPage();
	}

	/**
	 * @return an instance of Add User page
	 */
	public AddUserPage getAddUserPage() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		this.UserManagement.click();

		this.AddNewUser.click();

		return new AddUserPage();
	}

	/**
	 * @return an instance of User Page
	 */
	public UserPage getUserPage() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		if (!this.Users.isDisplayed()) {
			this.UserManagement.click();
		} else {
			System.out.println("User Management Not Expanded");
		}

		this.Users.click();

		return new UserPage();
	}

}
