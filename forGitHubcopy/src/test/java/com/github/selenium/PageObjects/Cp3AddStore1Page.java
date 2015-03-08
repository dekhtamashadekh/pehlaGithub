package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
public class Cp3AddStore1Page extends PageLoadConditions {

	@FindBy(how = How.ID, using = "store_name")
	WebElement StoreName;

	@FindBy(how = How.ID, using = "client_store_id")
	WebElement StoreId;

	@FindBy(how = How.ID, using = "region_id")
	WebElement RegionId;

	@FindBy(how = How.ID, using = "country_id")
	WebElement CountryId;

	@FindBy(how = How.ID, using = "store_status")
	WebElement StoreStatus;

	@FindBy(how = How.ID, using = "btnSubmitStore")
	WebElement Save;

	public Cp3AddStore1Page() {

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

			list.add(this.CountryId.isDisplayed());

			list.add(this.StoreName.isDisplayed());

			list.add(this.StoreId.isDisplayed());

			list.add(this.RegionId.isDisplayed());

			list.add(this.StoreStatus.isDisplayed());

			list.add(this.Save.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public Cp3AddStore2Page addStoreName(String name) {

		this.StoreName.sendKeys(name);

		this.StoreId.sendKeys(name);

		new AdvancedSelect(this.RegionId).selectByIndex(02);

		new AdvancedSelect(this.CountryId).selectByIndex(02);

		new AdvancedSelect(this.StoreStatus).selectByIndex(02);

		//Clicking on save using jquery because this element changes position on page load
		//So will not work on Chrome , which is fast

		JavascriptExecutor jsSlectName = (JavascriptExecutor) getDriverObject();
		jsSlectName.executeScript("$('#btnSubmitStore').click()");

		//this.Save.click();

		return new Cp3AddStore2Page();

	}

	public List<WebElement> addNoName(String name) {

		//Clicking on save using jquery because this element changes position on page load
		//So will not work on Chrome , which is fast

		JavascriptExecutor jsSlectName = (JavascriptExecutor) getDriverObject();
		jsSlectName.executeScript("$('#btnSubmitStore').click()");

		List<WebElement> errorMessages = new ArrayList<WebElement>();

		errorMessages.add(getDriverObject().findElement(By.xpath("//label[(contains(text(),'Please enter a store name'))][@class='error']")));

		errorMessages.add(getDriverObject().findElement(By.xpath("//label[(contains(text(),'Please enter a unique store ID'))][@class='error']")));

		errorMessages.add(getDriverObject().findElement(By.xpath("//label[(contains(text(),'Please select a region'))][@class='error']")));

		errorMessages.add(getDriverObject().findElement(By.xpath("//label[(contains(text(),'Please select a country'))][@class='error']")));

		errorMessages.add(getDriverObject().findElement(By.xpath("//label[(contains(text(),'Please select a store status'))][@class='error']")));

		return errorMessages;

	}

}
