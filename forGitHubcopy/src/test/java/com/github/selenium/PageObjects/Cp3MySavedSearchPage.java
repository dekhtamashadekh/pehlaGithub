package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.selenium.PageLoadConditions;

/**
 * @author dtyagi
 * 
 */
public class Cp3MySavedSearchPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "mySavedSearches")
	WebElement SavedSearchesModal;

	public Cp3MySavedSearchPage() {

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

			list.add(this.SavedSearchesModal.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @param timeStamp
	 */
	public Cp3FolderPage applySavedSearch(String timeStamp) {

		String getApplyButton = "return jQuery(\"td:contains('" + timeStamp + "')\").siblings(jQuery(\"td\")).children().children(\"a:contains('Apply')\").get(0)";

		WebElement applyButton = (WebElement) ((JavascriptExecutor) getDriverObject()).executeScript(getApplyButton);

		applyButton.click();

		return new Cp3FolderPage();

	}

}
