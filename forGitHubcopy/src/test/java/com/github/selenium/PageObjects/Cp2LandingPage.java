package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.List;

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
 * @author dtyagi Class to represent Cp2 Landing Page
 * 
 */
public class Cp2LandingPage extends LoadableComponent<Cp2LandingPage> {

	@FindBy(how = How.ID, using = "HeaderFrame")
	WebElement HeaderFrameElement;

	@FindBy(how = How.CSS, using = "#mm99 > span")
	WebElement AdminTab;

	@FindBy(how = How.XPATH, using = "//li/a[@title='Archive']")
	List<WebElement> links;

	/**
	 * 
	 */
	public Cp2LandingPage() {
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
	 * @return an instance of  Admin Page
	 */
	public AdminPage getAdminPage() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(HeaderFrameElement);

		this.AdminTab.click();

		return new AdminPage();

	}

	/**
	 * @return an instance of Test Zone Page
	 */
	public TestZonePage getTestZonePage() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(HeaderFrameElement);

		this.links.get(0).click();

		return new TestZonePage();

	}

}