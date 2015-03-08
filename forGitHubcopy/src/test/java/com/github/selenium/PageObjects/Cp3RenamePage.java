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
public class Cp3RenamePage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "renamed-page-title")
	WebElement InputField;

	@FindBy(how = How.XPATH, using = "//div[@class='modal-footer']/button[@id='save-rename-page']")
	WebElement Save;

	public Cp3RenamePage() {

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

			list.add(this.InputField.isDisplayed());
			
			list.add(this.Save.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again"
					+ e.getMessage());

		}

		return list;

	}

	public Cp3DraftPage renamePage(String name) {

		InputField.click();

		InputField.clear();

		InputField.sendKeys(name);

		Save.click();

		return new Cp3DraftPage();

	}

}
