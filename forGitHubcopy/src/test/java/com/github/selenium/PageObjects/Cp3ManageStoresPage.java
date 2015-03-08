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

import com.github.selenium.PageLoadConditions;

/**
 * @author 
 * 
 */
public class ManageStoresPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "btnAddStore")
	WebElement AddStore;

	@FindBy(how = How.ID, using = "keywords")
	WebElement TextSearch;

	@FindBy(how = How.ID, using = "filter")
	WebElement Filter;

	public ManageStoresPage() {

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

			list.add(this.AddStore.isDisplayed());

			list.add(this.TextSearch.isDisplayed());

			list.add(this.Filter.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public AddStore1Page getAddStore1Page() {

		this.AddStore.click();
		return new AddStore1Page();

	}

	public List<WebElement> searchStore(String name) {

		this.TextSearch.sendKeys(name);

		this.Filter.click();

		return getDriverObject().findElements(By.xpath("//table[@id='storeTable']/tbody/tr/td[contains(text(), '" + name + "')]"));

	}

}
