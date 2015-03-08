package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
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
public class Cp3AddStore3Page extends PageLoadConditions {

	@FindBy(how = How.ID, using = "btnSubmitStore")
	WebElement Save;

	public Cp3AddStore3Page() {

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

			list.add(this.Save.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public Cp3AddStore4Page getCp3AddStore4Page() {

		//Clicking on save using jquery because this element changes position on page load
		//So will not work on Chrome , which is fast

		JavascriptExecutor jsSlectName = (JavascriptExecutor) getDriverObject();
		jsSlectName.executeScript("$('#btnSubmitStore').click()");

		//this.Save.click();

		return new Cp3AddStore4Page();
	}

}
