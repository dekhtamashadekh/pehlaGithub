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
 * @author dtyagi
 * 
 */
public class Cp3AddNewGroupPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "name")
	WebElement UserGroupName;

	@FindBy(how = How.ID, using = "description")
	WebElement UserGroupDescription;

	@FindBy(how = How.XPATH, using = "//button[@type='button']")
	WebElement UserGroupSave;

	public Cp3AddNewGroupPage() {

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

			list.add(this.UserGroupName.isDisplayed());

			list.add(this.UserGroupDescription.isDisplayed());

			list.add(this.UserGroupSave.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public Cp3GroupAddSuccessPage getCp3GroupAddSuccessPage() {

		this.UserGroupName.clear();

		this.UserGroupName.sendKeys("testautogroup" + getTimeStamp());

		this.UserGroupDescription.clear();

		this.UserGroupDescription.sendKeys("testautogroup" + getTimeStamp());

		this.UserGroupSave.click();

		return new Cp3GroupAddSuccessPage();

	}

}
