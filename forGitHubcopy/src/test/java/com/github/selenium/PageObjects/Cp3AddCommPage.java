package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.selenium.AdvancedSelect;
import com.github.selenium.PageLoadConditions;

/**
 * @author dtyagi
 * 
 */
public class Cp3AddCommPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "department_id")
	WebElement From;

	@FindBy(how = How.ID, using = "comm_title")
	WebElement Title;

	@FindBy(how = How.ID, using = "comm_body_ifr")
	WebElement DescriptionIframe;

	@FindBy(how = How.XPATH, using = "//div[@id='thecheckboxes']/label[1]/span")
	WebElement FirstStore;

	@FindBy(how = How.ID, using = "publish_date")
	WebElement PublishDate;

	@FindBy(how = How.XPATH, using = "(//button[@name='btnSubmitComm'])[2]")
	WebElement SavePublish;

	public Cp3AddCommPage() {

		PageFactory.initElements(getDriverObject(), this);
		this.get();

	}

	/**
	 * @return
	 */
	@Override
	public String getTimeStamp() {
		return new SimpleDateFormat("dd MMM yyyy").format(new Date());

	}

	/**
	 * @return
	 */
	@Override
	public List<Boolean> allLoaded() {

		List<Boolean> list = new ArrayList<Boolean>();

		try {

			list.add(this.From.isDisplayed());

			list.add(this.Title.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public Cp3CommPage addAnnouncement(int name, String date) {

		new AdvancedSelect(this.From).selectByIndex(02);

		this.Title.sendKeys(name + date);

		getDriverObject().switchTo().frame(DescriptionIframe);

		WebElement editable = getDriverObject().switchTo().activeElement();

		editable.sendKeys("Test Comm Description " + date);

		getDriverObject().switchTo().defaultContent();

		// this.FirstStore.click();
		// Once again this will not work properly in chrome because this button
		// is not designed properly
		// and Chrome wants to click in the center, hence the JS/jQuery
		// workaround

		((JavascriptExecutor) getDriverObject())
				.executeScript("$('div#thecheckboxes > label >span').first().click()");

		this.PublishDate.sendKeys(date);

		this.SavePublish.click();

		return new Cp3CommPage();

	}

}
