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
 * @author dtyagi
 * 
 */
public class Cp3AddStoryPage extends PageLoadConditions {

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Add Story")
	WebElement AddStoryLink;

	@FindBy(how = How.ID, using = "new-story-type")
	WebElement NewStoryType;

	@FindBy(how = How.ID, using = "new-story-template")
	WebElement NewStoryTemplate;

	@FindBy(how = How.ID, using = "add-new-story")
	WebElement CreateButtonForAddNewStory;

	public Cp3AddStoryPage() {

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

			list.add(this.CreateButtonForAddNewStory.isDisplayed());

			list.add(this.NewStoryTemplate.isDisplayed());

			list.add(this.NewStoryType.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 */
	public Cp3DraftPage addButtonStory() {

		new AdvancedSelect(this.NewStoryType).selectByIndex(1);

		new AdvancedSelect(this.NewStoryTemplate).selectByIndex(1);

		this.CreateButtonForAddNewStory.click();

		return new Cp3DraftPage();

	}

}
