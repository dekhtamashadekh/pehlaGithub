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
 * @author 
 * 
 */
public class DraftPage extends PageLoadConditions {

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

	public DraftPage() {

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
	public AddStoryPage getAddStoryPage() {

		this.AddStoryLink.click();

		return new AddStoryPage();

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public PublishPage getPublishPage() throws InterruptedException {

		Thread.sleep(300);

		this.Publish.click();

		return new PublishPage();

	}

	public RenamePage getRenamePage() {

		RenamePage.click();

		return new RenamePage();

	}

	public DeletePage getDeletePage() throws InterruptedException {

		this.rhsExpander.click();

		
		getWait().until(ExpectedConditions.elementToBeClickable(this.DeletePage));

		this.DeletePage.click();

		return new DeletePage();

	}

}