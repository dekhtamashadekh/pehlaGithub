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
 * @author  Class to represent Edit User Page
 * 
 */
public class EditUserPage extends LoadableComponent<EditUserPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.LINK_TEXT, using = "Click here to change the password")
	WebElement ChangePasswordLink;

	@FindBy(how = How.ID, using = "client_new_password")
	WebElement NewPasswordField;

	@FindBy(how = How.ID, using = "client_new_password2")
	WebElement NewPasswordConfirmationField;

	@FindBy(how = How.CSS, using = "input.FormButton")
	WebElement EnterUpdatedPassword;

	private String editUserPageTimeStamp;

	private Date editUserPageTime = new Date();

	static Calendar cal = Calendar.getInstance();

	/**
	 * 
	 */
	public EditUserPage() {

		cal.setTime(editUserPageTime);

		DateFormat df = new SimpleDateFormat("ssmmddMMyyyy");

		editUserPageTimeStamp = df.format(editUserPageTime);

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
	 * 
	 * This method changes the password while logged in as a different user
	 * 
	 * @throws InterruptedException
	 */
	public void changePassword() throws InterruptedException {

		this.ChangePasswordLink.click();

		String mainWindow = getDriverObject().getWindowHandle();

		Thread.sleep(5000);

		getDriverObject().switchTo().window(getDriverObject().getWindowHandle());

		getDriverObject().switchTo().window("passwordChangeWindow");

		this.NewPasswordField.sendKeys("Password!123");

		this.NewPasswordConfirmationField.sendKeys("Password!123");

		this.EnterUpdatedPassword.click();

		getDriverObject().switchTo().window(mainWindow);

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

	}

	/**
	 * @return a String which is TimeStamp for this class
	 */
	public String getUserPageTimeStamp() {

		return editUserPageTimeStamp.toString();

	}

}
