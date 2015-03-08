package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.selenium.AdvancedSelect;
import com.github.selenium.PageLoadConditions;

/**
 * @author dtyagi
 * 
 */
public class PlanAddActivity1Page extends PageLoadConditions {

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/div/ul/li[@class='form-title-status']/div/input[@name='title']")
	WebElement Title;

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/div/ul/li/div/div/select[@name='planID']")
	WebElement Plan;

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/div/ul/li/div/div/select[@name='activityID']")
	WebElement Swimlane;

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/div/ul/li/div/div/select[@name='categoryID']")
	WebElement Category;

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/div/ul/li/div/p/input[@name='startDate']")
	WebElement StartDate;

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/div/ul/li/div/p/input[@name='endDate']")
	WebElement EndDate;

	@FindBy(how = How.XPATH, using = "//div[@id='activityForm']/form/fieldset/button[@class='skipNext']")
	WebElement SkipEnd;

	public PlanAddActivity1Page() {

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

			list.add(this.Title.isDisplayed());

			list.add(this.Plan.isDisplayed());

			list.add(this.Swimlane.isDisplayed());

			list.add(this.Category.isDisplayed());

			list.add(this.StartDate.isDisplayed());

			list.add(this.EndDate.isDisplayed());

			list.add(this.SkipEnd.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public PlanSavePage oneStepEvent(String name) {

		this.Title.click();
		this.Title.sendKeys(name);
		new AdvancedSelect(this.Plan).selectByIndex(01);
		new AdvancedSelect(this.Swimlane).selectByIndex(01);
		new AdvancedSelect(this.Category).selectByIndex(01);

		getWait().until(ExpectedConditions.elementToBeClickable(this.StartDate));

		this.StartDate.click();

		// This if else because there is a bug in Plan date picker. Some days of
		// the month, today's date is not displayed
		if (getDriverObject().findElements(
				By.xpath("//a[@class='ui-state-default ui-state-highlight']"))
				.size() == 1) {
			getDriverObject()
					.findElement(
							By.xpath("//a[@class='ui-state-default ui-state-highlight']"))
					.click();
		} else {
			getDriverObject().findElement(
					By.xpath("(//a[@class='ui-state-default'])[1]")).click();
		}
		
		getWait().until(ExpectedConditions.elementToBeClickable(this.StartDate));
		this.EndDate.click();
		getDriverObject().findElement(
				By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']"))
				.click();
		getDriverObject().findElement(
				By.xpath("(//a[@class='ui-state-default'])[1]")).click();

		this.SkipEnd.click();

		return new PlanSavePage();

	}

}
