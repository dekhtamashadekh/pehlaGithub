package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.selenium.PageLoadConditions;

/**
 * @author 
 * 
 */
public class DeletePage extends PageLoadConditions {

	@FindBy(how = How.XPATH, using = "//div[@id='deletePage']/div/div[@class='modal-footer']/button[@id='delete-page']")
	WebElement Delete;

	@FindBy(how = How.CSS, using = "#deletePage.hide")
	WebElement DeleteHeader1;

	@FindBy(how = How.CSS, using = "#deletePage.in")
	WebElement DeleteHeader2;

	public DeletePage() {

		PageFactory.initElements(getDriverObject(), this);
		this.get();

	}

	/**
	 * @return
	 */
	@Override
	public List<Boolean> allLoaded() {

		List<Boolean> list = new ArrayList<Boolean>();

		try {

			list.add(this.Delete.isDisplayed());

			list.add(this.DeleteHeader1.isDisplayed());

			list.add(this.DeleteHeader2.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public LandingPage deletePage() {
		System.out.println("a debug message-1");
		this.Delete.click();
		System.out.println("a debug message0");
		WebDriverWait localWait = new WebDriverWait(getDriverObject(), 2);
		localWait.until(ExpectedConditions.alertIsPresent());
		System.out.println("a debug message1");
		Alert alert = getDriverObject().switchTo().alert();
		System.out.println("a debug message2");
		String alertText = alert.getText();
		System.out.println("a debug message3");
		alert.accept();
		System.out.println("a debug message4");
		localWait.until(
				ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
		System.out.println("a debug message5");

		assertEquals(alertText, "The page has been successfully deleted.");
		System.out.println("a debug message6");

		return new LandingPage();

	}

}
