package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
public class Cp3SubfolderSelectorPanelPage extends PageLoadConditions {

	@FindBy(how = How.CLASS_NAME, using = "navigator-container")
	WebElement SubFolderSelector;

	@FindBy(how = How.CLASS_NAME, using = "navLink")
	List<WebElement> SubFolders;

	public Cp3SubfolderSelectorPanelPage() {
		PageFactory.initElements(getDriverObject(), this);

		this.get();

	}

	/**
	 * @return
	 */
	@Override
	public List<Boolean> allLoaded() {

		if (getDriverObject().findElement(By.cssSelector("input.input.search-query")).isDisplayed()) {

		} else {

			getDriverObject().findElement(By.cssSelector("#filterPanel > div.handle")).click();
		}

		List<Boolean> list = new ArrayList<Boolean>();

		try {

			list.add(this.SubFolderSelector.isDisplayed());

			list.add(this.SubFolders.size() > 2);

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @param subFolderNumber
	 * @return
	 * @throws InterruptedException
	 */
	public Cp3FolderPage getCp3FolderPage(int subFolderNumber) throws InterruptedException {

		//String scripToSelectSecondFolder = "jQuery('.navLink.vault')["+(--subFolderNumber)+"].click()";

		String scripToSelectSecondFolder = "return jQuery('.navLink.vault').get(" + (--subFolderNumber) + ")";

		JavascriptExecutor js = (JavascriptExecutor) getDriverObject();

		WebElement folderSelectLink = (WebElement) js.executeScript(scripToSelectSecondFolder);

		folderSelectLink.click();

		return new Cp3FolderPage();

	}

}