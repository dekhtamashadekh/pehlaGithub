package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;
import static com.github.selenium.Utility.UploadJqueryToWebPage.loadScript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
public class LandingPage extends PageLoadConditions {

	@FindBy(how = How.CSS, using = "#filterPanel > div.handle")
	WebElement lhsExpander;

	@FindBy(how = How.CSS, using = "input.input.search-query")
	WebElement lhsSearchField;

	@FindBy(how = How.CSS, using = "a.download-arrow")
	WebElement downloadLink;

	@FindBy(how = How.LINK_TEXT, using = "Administration")
	WebElement Administration;

	@FindBy(how = How.LINK_TEXT, using = "Logout")
	WebElement Logout;

	@FindBy(how = How.CSS, using = "a[href='#saveSearch'][class='btn save-btn']")
	WebElement searchButton;

	@FindBy(how = How.CSS, using = "a[href='#mySavedSearches'][class='btn load-btn']")
	WebElement saveSearchButton;

	@FindBy(how = How.ID, using = "pubPag")
	WebElement MainPagePanel;

	@FindBy(how = How.CSS, using = "#sharePanel > div.handle")
	WebElement rhsExpander;

	@FindBy(how = How.ID, using = "publishOptions")
	WebElement ActionDropdown;

	@FindBy(how = How.CSS, using = "a > img[title ^='CP2']")
	WebElement cp2Link;

	public LandingPage() {
		PageFactory.initElements(getDriverObject(), this);
		this.get();

	}

	/**
	 * @return
	 * @throws IOException
	 */
	public String getAdministrationLink() throws IOException {

		loadScript();

		JavascriptExecutor js = (JavascriptExecutor) TestSeleniumBase.getDriverObject();

		String toGetAdministrationText = "return $(\"a:contains('Administration')\").text()";

		String textAdministartionTab = (String) js.executeScript(toGetAdministrationText);

		return textAdministartionTab.trim();

	}

	/**
	 * @return
	 */
	public String getFile() {

		this.lhsExpander.click();
		this.lhsSearchField.sendKeys("automationFile.jpg");
		this.lhsSearchField.sendKeys(Keys.ENTER);

		return this.downloadLink.getAttribute("href");

	}

	/**
	 * @return
	 */
	public Cp2LandingPage getCp2LandingPage() {

		this.cp2Link.click();

		return new Cp2LandingPage();

	}

	/**
	 * @return
	 */
	@Override
	public List<Boolean> allLoaded() {

		List<Boolean> list = new ArrayList<Boolean>();

		try {

			//list.add(this.searchButton.isDisplayed());

			//list.add(this.saveSearchButton.isDisplayed());

			//list.add(this.resetAllButton.isDisplayed());

			list.add(this.rhsExpander.isDisplayed());
			list.add(this.lhsExpander.isDisplayed());
			list.add(this.Administration.isDisplayed());
			list.add(this.Logout.isDisplayed());
			list.add(this.ActionDropdown.isDisplayed());
			list.add(this.MainPagePanel.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public Cp3RhsPage getCp3RhsPage() throws InterruptedException {

		this.rhsExpander.click();

		return new Cp3RhsPage();

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public Cp3LhsPage getCp3LhsPage() throws InterruptedException {

		this.lhsExpander.click();

		return new Cp3LhsPage();

	}

	public Cp3AdminPage getCp3AdminPage() {

		this.Administration.click();

		return new Cp3AdminPage();

	}

	public Cp3CommPage getCp3CommPage() {

		return new Cp3CommPage();

	}

	public PlanCalendarPage getPlanCalendarPage() {

		return new PlanCalendarPage();

	}

}