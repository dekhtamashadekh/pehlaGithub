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
public class Cp3Page extends PageLoadConditions {

	@FindBy(how = How.CLASS_NAME, using = "storyGroupsHolder")
	WebElement MainPanel;

	@FindBy(how = How.ID, using = "publishOptions")
	WebElement Action;

	public Cp3Page() {

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

			list.add(this.MainPanel.isDisplayed());

			list.add(this.Action.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public Cp3DraftPage editPage() {

		new AdvancedSelect(Action).selectByValue("edit-page");

		return new Cp3DraftPage();
	}

}
