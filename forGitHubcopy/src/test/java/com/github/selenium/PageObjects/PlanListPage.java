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

import com.github.selenium.PageLoadConditions;

/**
 * @author 
 * 
 */
public class PlanListPage extends PageLoadConditions {

	@FindBy(how = How.XPATH, using = "//label/input[@id='listview-btn']/following-sibling::span[(contains(text(),'List'))]" )
	WebElement List;

	@FindBy(how = How.ID, using = "activities-tab")
	WebElement Activities;

	@FindBy(how = How.ID, using = "campaigns-tab")
	WebElement Campaigns;

	public PlanListPage() {

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

			list.add(List.isDisplayed());

			list.add(Activities.isDisplayed());

			list.add(Campaigns.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public List<WebElement> searchEvent(String name) {

		return getDriverObject().findElements(By.xpath("//div[@id='list-view']/table/tbody/tr/td/a[(contains(text(),'" + name + "'))]"));
	}

}
