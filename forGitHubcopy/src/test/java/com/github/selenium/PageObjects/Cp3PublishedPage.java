package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.selenium.PageLoadConditions;

/**
 * @author dtyagi
 * 
 */
public class Cp3PublishedPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "publishOptions")
	@CacheLookup
	WebElement ActionsDropDown;

	@FindBy(how = How.ID, using = "appHome")
	@CacheLookup
	WebElement HomeLink;

	@FindBy(how = How.ID, using = "rename-page")
	WebElement RenamePage;

	@FindBy(how = How.CLASS_NAME, using = "icon-share-alt")
	WebElement Publish;

	@FindBy(how = How.ID, using = "delete-page")
	WebElement DeletePage;

	@FindBy(how = How.ID, using = "edit-publication")
	WebElement TopPanel;

	@FindBy(how = How.ID, using = "publish-container")
	WebElement PublishContainer;

	@FindBy(how = How.ID, using = "publish-area")
	WebElement PublishArea;

	@FindBy(how = How.ID, using = "page-actions")
	WebElement PageActions;

	public Cp3PublishedPage() {

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

			list.add(this.Publish.isDisplayed());

			list.add(this.PublishArea.isDisplayed());

			list.add(this.PageActions.isDisplayed());

			list.add(this.PublishContainer.isDisplayed());

			list.add(this.TopPanel.isDisplayed());

			list.add(this.ActionsDropDown.isDisplayed());

			list.add(this.HomeLink.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @throws InterruptedException
	 */
	public void refereshPublishedPage() throws InterruptedException {

		getDriverObject().navigate().refresh();

	}

}
