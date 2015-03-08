package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.selenium.PageLoadConditions;

/**
 * @author dtyagi
 * 
 */
public class Cp3DraftPage extends PageLoadConditions {

	@FindBy(how = How.LINK_TEXT, using = "Add Story")
	WebElement AddStoryLink;

	@FindBy(how = How.ID, using = "page-permissions")
	WebElement Permissions;

	@FindBy(how = How.ID, using = "rename-page")
	WebElement RenamePage;

	@FindBy(how = How.CLASS_NAME, using = "icon-share-alt")
	WebElement Publish;

	@FindBy(how = How.CSS, using = "a#delete-page")
	WebElement DeletePage;

	@FindBy(how = How.ID, using = "edit-publication")
	WebElement TopPanel;

	@FindBy(how = How.ID, using = "publish-container")
	WebElement PublishContainer;

	@FindBy(how = How.ID, using = "publish-area")
	WebElement PublishArea;

	@FindBy(how = How.ID, using = "page-actions")
	WebElement PageActions;

	@FindBy(how = How.CSS, using = "#sharePanel > div.handle")
	WebElement rhsExpander;

	public Cp3DraftPage() {

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

			list.add(this.AddStoryLink.isDisplayed());

			list.add(this.Publish.isDisplayed());

			list.add(this.PublishArea.isDisplayed());

			list.add(this.PageActions.isDisplayed());

			list.add(this.PublishContainer.isDisplayed());

			list.add(this.TopPanel.isDisplayed());

			list.add(this.DeletePage.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 */
	public Cp3AddStoryPage getCp3AddStoryPage() {

		this.AddStoryLink.click();

		return new Cp3AddStoryPage();

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public Cp3PublishPage getCp3PublishPage() throws InterruptedException {

		Thread.sleep(300);

		this.Publish.click();

		return new Cp3PublishPage();

	}

	public Cp3RenamePage getRenamePage() {

		RenamePage.click();

		return new Cp3RenamePage();

	}

	public Cp3DeletePage getDeletePage() throws InterruptedException {

		this.rhsExpander.click();

		
		getWait().until(ExpectedConditions.elementToBeClickable(this.DeletePage));

		this.DeletePage.click();

		return new Cp3DeletePage();

	}

}