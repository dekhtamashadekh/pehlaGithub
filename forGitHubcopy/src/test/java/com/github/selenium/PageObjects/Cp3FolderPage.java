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
public class Cp3FolderPage extends PageLoadConditions {

	@FindBy(how = How.CLASS_NAME, using = "vaultView")
	WebElement SubFolder;

	@FindBy(how = How.ID, using = "display-options")
	WebElement DisplayOptions;

	@FindBy(how = How.ID, using = "vaultActions")
	WebElement ActionDropdown;

	public Cp3FolderPage() {
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

			list.add(this.ActionDropdown.isDisplayed());

			list.add(this.DisplayOptions.isDisplayed());

			list.add(this.SubFolder.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 */
	public int getNumberOfFolders() {

		String jQueryNumberOfFolders = "return jQuery('.vaultView .vaultItem').length";

		JavascriptExecutor js = (JavascriptExecutor) getDriverObject();

		return ((Long) js.executeScript(jQueryNumberOfFolders)).intValue();

	}

}