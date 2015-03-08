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
 * @author  Class to represent  Upload File Page
 * 
 */
public class UploadFilePage extends LoadableComponent<UploadFilePage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.ID, using = "fileUploaderInterfaceFrame")
	WebElement FileUploaderInterfaceFrame;

	@FindBy(how = How.LINK_TEXT, using = "Simplified")
	WebElement SimplifiedUploaderLink;

	@FindBy(how = How.NAME, using = "Filedata")
	WebElement FilePath;

	@FindBy(how = How.NAME, using = "btnUpload3")
	WebElement UploadButton;

	/**
	 * 
	 */
	public UploadFilePage() {
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
	// This is a 
			protected
			void
			isLoaded() throws Error {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriverObject(), 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * This method uploads a file
	 */
	public void uploadFile() {

		getDriverObject().switchTo().frame(FileUploaderInterfaceFrame);

		this.SimplifiedUploaderLink.click();

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		getDriverObject().switchTo().frame(FileUploaderInterfaceFrame);

		this.FilePath.sendKeys("T:\\automation\\testFiles\\TestFlow.jpg");

		this.UploadButton.click();

	}

}