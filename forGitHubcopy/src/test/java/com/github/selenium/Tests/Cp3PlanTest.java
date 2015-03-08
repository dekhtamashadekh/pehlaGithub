package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.PlanAddActivity1Page;
import com.github.selenium.PageObjects.PlanCalendarPage;
import com.github.selenium.PageObjects.PlanListPage;
import com.github.selenium.PageObjects.PlanSavePage;

public class PlanTest extends TestSeleniumBase {

	@Test(groups = { "noSafari" })
	@Parameters(value = { "website", "username", "password" })
	public void addEventCalendar(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=planner&home=homePage");

		PlanCalendarPage planCalendarPage = landingPage.getPlanCalendarPage();
		
		PlanAddActivity1Page planAddActivity1Page =planCalendarPage.getPlanAddActivity1Page();
		
		PlanSavePage  planSavePage = planAddActivity1Page.oneStepEvent("testAutoEvent" + planAddActivity1Page.getTimeStamp());
		
		planSavePage.saveEvent();
		
		assertTrue((planCalendarPage.eventInGrid("testAutoEvent" + planAddActivity1Page.getTimeStamp())).size()>0);
		
	;

		

	}
	
	@Test(groups = {"noSafari"})
	@Parameters(value = { "website", "username", "password" })
	public void addEventList(String website, String username, String password) throws Exception {
		
		
		
		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=planner&home=homePage");

		PlanCalendarPage planCalendarPage = landingPage.getPlanCalendarPage();
		
		PlanAddActivity1Page planAddActivity1Page =planCalendarPage.getPlanAddActivity1Page();
		
		PlanSavePage  planSavePage = planAddActivity1Page.oneStepEvent("testAutoEvent" + planAddActivity1Page.getTimeStamp());
		
		planSavePage.saveEvent();
		
		PlanListPage planListPage = planCalendarPage.getPlanListPage();
		
		assertTrue((planListPage.searchEvent("testAutoEvent" + planAddActivity1Page.getTimeStamp())).size()>0);
		
		
		
	}
	
	@Test(groups = { "noSafari" })
	@Parameters(value = { "website", "username", "password" })
	public void addSpecialCharEvent(String website, String username,
			String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(
				website + "core/root/index.cfm?pageId=planner&home=homePage");

		PlanCalendarPage planCalendarPage = landingPage.getPlanCalendarPage();

		PlanAddActivity1Page planAddActivity1Page = planCalendarPage
				.getPlanAddActivity1Page();

		PlanSavePage planSavePage = planAddActivity1Page
				.oneStepEvent("£$%£$%$£"
						+ planAddActivity1Page.getTimeStamp());

		planSavePage.saveEvent();

		assertTrue((planCalendarPage.eventInGrid("£$%£$%$£"
				+ planAddActivity1Page.getTimeStamp())).size() > 0);

		;

	}

}