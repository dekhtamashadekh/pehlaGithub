package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.selenium.PageLoadConditions;
import com.github.selenium.TestSeleniumBase;

/**
 * @author dtyagi
 * 
 */
public class ConcreteLoginPage extends PageLoadConditions {
	@FindBy(how = How.ID, using = "login-username")
	WebElement username;

	@FindBy(how = How.NAME, using = "Password")
	WebElement password;

	@FindBy(how = How.ID, using = "login-submit-btn")
	WebElement submit;

	@FindBy(how = How.ID, using = "login-tandc")
	WebElement termsAndConditions;

	@FindBy(how = How.ID, using = "error")
	WebElement invalidLoginMessage;

	/**
	 * 
	 */
	public ConcreteLoginPage() {
		PageFactory.initElements(getDriverObject(), this);
		this.get();
	}

	@Override
	public List<Boolean> allLoaded() {

		List<Boolean> list = new ArrayList<Boolean>();

		try {

			list.add(this.username.isDisplayed());

			list.add(this.password.isDisplayed());

			list.add(this.termsAndConditions.isDisplayed());

			list.add(this.submit.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public LandingPage login(String username, String password) {

		this.password.click();
		this.password.clear();
		this.password.sendKeys(password);
		this.username.click();
		this.username.clear();
		this.username.sendKeys(username);

		this.termsAndConditions.click();
		this.submit.click();
		return new LandingPage();

	}
	
	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public ConcreteLoginPage invalidLogin(String username, String password) {

		this.password.click();
		this.password.clear();
		this.password.sendKeys(password);
		this.username.click();
		this.username.clear();
		this.username.sendKeys(username);

		this.termsAndConditions.click();
		this.submit.click();
		return new ConcreteLoginPage();

	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String jQueryLoadCheck() throws IOException {

		JavascriptExecutor js = (JavascriptExecutor) TestSeleniumBase.getDriverObject();

		String toGetAdministrationText = "return jQuery(\"a\").text()";

		String textAdministartionTab = (String) js.executeScript(toGetAdministrationText);

		return textAdministartionTab.trim();

	}

	/**
	 * @return
	 */
	public String getErrorMessage() {

		return this.invalidLoginMessage.getText().trim();
	}

}