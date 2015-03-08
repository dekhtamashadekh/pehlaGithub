package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

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
public class PublishPage extends PageLoadConditions {

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Add Story")
	WebElement AddStoryLink;

	@FindBy(how = How.ID, using = "new-story-type")
	WebElement NewStoryType;

	@FindBy(how = How.ID, using = "new-story-template")
	WebElement NewStoryTemplate;

	@FindBy(how = How.ID, using = "ok-publish-draft")
	WebElement OkPublishButton;

	@FindBy(how = How.CSS, using = ".modal-backdrop.fade.in")
	WebElement PublishModalDialouge;

	public PublishPage() {

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

			list.add(this.OkPublishButton.isDisplayed());

			list.add(this.PublishModalDialouge.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public PublishedPage getPublishedPage() throws InterruptedException {

		this.OkPublishButton.click();

		Thread.sleep(300);

		return new PublishedPage();

	}

}
