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
import com.github.selenium.TestSeleniumBase;

/**
 * @author dtyagi
 * 
 */
public class Cp3RhsPage extends PageLoadConditions {

	@FindBy(how = How.CSS, using = "a[href='#createNewPageModal']")
	WebElement createNewPageLink;

	@FindBy(how = How.CSS, using = "#sharePanel.active")
	WebElement PanelExpanded;

	public Cp3RhsPage() {
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

			list.add(this.createNewPageLink.isDisplayed());
			list.add(this.createNewPageLink.isEnabled());
			list.add(this.PanelExpanded.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public Cp3AddNewPage getCp3AddNewPage() throws InterruptedException {

		this.createNewPageLink.click();

		return new Cp3AddNewPage();
	}

	/**
	 * @param timeStamp
	 * @return
	 */
	public String getPageStatus(String timeStamp) {

		String jQueryToGetStatus = "return jQuery(\".title a:contains('"
				+ timeStamp + "')\").prev().text()";
		JavascriptExecutor js = (JavascriptExecutor) TestSeleniumBase
				.getDriverObject();

		return (String) js.executeScript(jQueryToGetStatus);

	}

	public Cp3Page clickPageinRHS(String pageName) {

		getDriverObject().findElement(
				By.xpath("//div/p/a[(contains(text(),'" + pageName + "'))]"))
				.click();

		return new Cp3Page();

	}

	public String getPageName(String stamp) {

		return getDriverObject().findElement(
				By.xpath("//p[@class='title']/a[(contains(text()," + stamp
						+ "))]")).getText();

	}

	public List<WebElement> getNumberOfPages(String name) {

		return getDriverObject().findElements(
				By.xpath("//div/p/a[(contains(text(),'" + name + "'))]"));

	}

}