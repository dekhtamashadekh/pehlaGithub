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
public class PlanCalendarPage extends PageLoadConditions {

	@FindBy(how = How.ID, using = "calendarview-btn")
	WebElement CalendarButton;

	@FindBy(
			how = How.XPATH,
			using = "//label/input[@id='listview-btn']/following-sibling::span[(contains(text(),'List'))]")
	WebElement List;

	@FindBy(how = How.ID, using = "monitorview-btn")
	WebElement History;

	@FindBy(how = How.ID, using = "addnewactivity-button")
	WebElement AddActivity;

	@FindBy(how = How.ID, using = "exportExcelOnly")
	WebElement Export;

	@FindBy(how = How.ID, using = "print")
	WebElement Print;

	@FindBy(how = How.ID, using = "thegrid")
	WebElement WholeGrid;

	@FindBy(how = How.CLASS_NAME, using = "gridswimlane")
	List<WebElement> SwimLanes;

	public PlanCalendarPage() {

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

			list.add(this.CalendarButton.isDisplayed());

			list.add(this.List.isDisplayed());

			list.add(this.History.isDisplayed());

			list.add(this.AddActivity.isDisplayed());

			list.add(this.Export.isDisplayed());

			list.add(this.Print.isDisplayed());

			list.add(this.WholeGrid.isDisplayed());

			list.add(this.SwimLanes.size() > 0);

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	public PlanAddActivity1Page getPlanAddActivity1Page() {

		this.AddActivity.click();
		return new PlanAddActivity1Page();
	}

	public List<WebElement> eventInGrid(String name) {

		return getDriverObject().findElements(By.xpath("//div[@class='gridswimlane']/div/p[(contains(text(),'" + name + "'))]"));

	}

	public PlanListPage getPlanListPage() {

		this.List.click();

		return new PlanListPage();
	}

}
