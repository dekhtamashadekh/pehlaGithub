package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.selenium.AdvancedSelect;
import com.github.selenium.PageLoadConditions;

/**
 * @author 
 * 
 */

public class LhsPage extends PageLoadConditions {

	@FindBy(how = How.CSS, using = "#filterPanel.active")
	WebElement PanelExpanded;

	@FindBy(how = How.CSS, using = "#filterPanel > div.handle")
	WebElement LhsExpander;

	@FindBy(how = How.CSS, using = "input.input.search-query")
	WebElement LhsSearchField;

	@FindBy(how = How.CSS, using = "a[href='#saveSearch'][class='btn save-btn']")
	WebElement SaveSearchButton;

	@FindBy(how = How.CSS, using = "a[href='#mySavedSearches'][class='btn load-btn']")
	WebElement MySaveSearchButton;

	@FindBy(how = How.CLASS_NAME, using = "zoneSelect")
	WebElement SelectZoneDropDown;

	@FindBy(how = How.CLASS_NAME, using = "modal-backdrop.fade.in")
	WebElement ModalShouldBeHidden;

	//These elements will not be loaded at the time of page load

	@FindBy(how = How.CLASS_NAME, using = "navigator-container")
	WebElement SubFolderSelector;

	public LhsPage() {
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

			list.add(this.PanelExpanded.isDisplayed());

			list.add(this.LhsExpander.isDisplayed());

			list.add(this.LhsSearchField.isDisplayed());

			list.add(this.SaveSearchButton.isDisplayed());

			list.add(this.MySaveSearchButton.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */

	public SubfolderSelectorPanelPage getSubfolderSelectorPanelPage() throws InterruptedException {

		new AdvancedSelect(this.SelectZoneDropDown).selectByIndex(2);

		return new SubfolderSelectorPanelPage();

	}

	/**
	 * @return
	 */

	public SaveSearchPage getSaveSearchPage() {

		this.SaveSearchButton.click();
		return new SaveSearchPage();

	}

	/**
	 * @return
	 */

	public MySavedSearchPage getMySavedSearchPage() {

		this.MySaveSearchButton.click();

		return new MySavedSearchPage();

	}

}