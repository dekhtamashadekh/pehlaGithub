package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
public class SaveSearchPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "saveSearch")
	WebElement SaveSearchModal;

	@FindBy(how = How.ID, using = "filter-name-save")
	WebElement FilterName;

	@FindBy(how = How.CLASS_NAME, using = "icon-remove")
	WebElement Remove;

	@FindBy(how = How.CLASS_NAME, using = "icon-ok")
	WebElement Save;

	public SaveSearchPage() {

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

			list.add(this.FilterName.isDisplayed());

			list.add(this.Remove.isDisplayed());

			list.add(this.Save.isDisplayed());

			list.add(this.SaveSearchModal.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @throws InterruptedException
	 */
	public void saveSearch() throws InterruptedException {
		this.FilterName.click();

		this.FilterName.sendKeys(getTimeStamp());

		this.Save.click();

		//		WebElement myDynamicElement = (new WebDriverWait(getDriverObject(), 30))
		//				  .until(ExpectedConditions.invisibilityOfElementLocated(By.id("saveSearch")));

		WebDriverWait wait = new WebDriverWait(getDriverObject(), 30);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("saveSearch")));

		//Thread.sleep(200);
	}

	public void emptySaveSearch() {
		this.FilterName.click();

		this.Save.click();

	}

}
