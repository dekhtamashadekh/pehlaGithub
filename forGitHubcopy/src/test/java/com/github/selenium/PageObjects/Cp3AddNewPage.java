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
public class Cp3AddNewPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "page-title")
	@CacheLookup
	WebElement PageTitle;

	@FindBy(how = How.ID, using = "create-new-publisher-page")
	@CacheLookup
	WebElement CreateButton;

	public Cp3AddNewPage() {

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

			list.add(this.CreateButton.isDisplayed());

			list.add(this.PageTitle.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public Cp3DraftPage getCp3DraftPage() throws InterruptedException {

		this.PageTitle.click();

		this.PageTitle.sendKeys("Test Page" + getTimeStamp());

		Thread.sleep(300);

		this.CreateButton.click();

		return new Cp3DraftPage();
	}

	public void createPage(String name) throws InterruptedException {

		this.PageTitle.click();

		this.PageTitle.sendKeys(name);

		this.CreateButton.click();

	}

}
