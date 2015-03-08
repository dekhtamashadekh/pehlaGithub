package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.AddNewPage;
import com.github.selenium.PageObjects.AddStoryPage;
import com.github.selenium.PageObjects.DeletePage;
import com.github.selenium.PageObjects.DraftPage;
import com.github.selenium.PageObjects.FolderPage;
import com.github.selenium.PageObjects.LhsPage;
import com.github.selenium.PageObjects.MySavedSearchPage;
import com.github.selenium.PageObjects.Page;
import com.github.selenium.PageObjects.PublishPage;
import com.github.selenium.PageObjects.PublishedPage;
import com.github.selenium.PageObjects.RenamePage;
import com.github.selenium.PageObjects.RhsPage;
import com.github.selenium.PageObjects.SaveSearchPage;
import com.github.selenium.PageObjects.SubfolderSelectorPanelPage;
import com.github.selenium.PageObjects.LandingPage;

public class Test extends TestSeleniumBase {

	@Test(groups = { "noSafari" })
	@Parameters(value = { "website", "username", "password" })
	public void createPageTest(String website, String username,
			String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		RhsPage RhsPage = landingPage.getRhsPage();

		AddNewPage AddNewPage = RhsPage.getAddNewPage();

		DraftPage DraftPage = AddNewPage.getDraftPage();

		AddStoryPage AddStoryPage = DraftPage.getAddStoryPage();

		AddStoryPage.addButtonStory().get();

		PublishPage PublishPage = DraftPage.getPublishPage();

		PublishedPage PublishedPage = PublishPage
				.getPublishedPage();

		try {

			PublishedPage.refereshPublishedPage();

			PublishedPage.get();

		} catch (UnhandledAlertException n) {

			System.out.println("i got an alert exception");

			Alert alert = getDriverObject().switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(alert.getText());

		}

		assertEquals(RhsPage.getPageStatus(AddNewPage.getTimeStamp()),
				"Live");
	}

	@Test()
	@Parameters(value = { "website", "username", "password" })
	public void lhsTest(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		LhsPage LhsPage = landingPage.getLhsPage();

		SubfolderSelectorPanelPage SubFolderSelectorPanelPage = LhsPage
				.getSubfolderSelectorPanelPage();

		FolderPage FolderPage = SubFolderSelectorPanelPage
				.getFolderPage(3);

		assertTrue(FolderPage.getNumberOfFolders() > 0);

		SaveSearchPage SaveSearchPage = LhsPage.getSaveSearchPage();

		SaveSearchPage.saveSearch();

		LhsPage.get();

		MySavedSearchPage MySavedSearchPage = LhsPage
				.getMySavedSearchPage();

		MySavedSearchPage.applySavedSearch(SaveSearchPage.getTimeStamp());

		assertTrue(FolderPage.getNumberOfFolders() > 01);

	}

	@Test(groups = { "noSafari" })
	@Parameters(value = { "website", "username", "password" })
	public void invalidPageTest(String website, String username,
			String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		RhsPage RhsPage = landingPage.getRhsPage();

		AddNewPage AddNewPage = RhsPage.getAddNewPage();

		AddNewPage.createPage("");

		Alert alert = getDriverObject().switchTo().alert();
		String alertText = alert.getText();
		alert.accept();

		assertEquals(alertText, "Please, type a title for your new page.");

	}

	@Test()
	@Parameters(value = { "website", "username", "password" })
	public void emptySaveTest(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		LhsPage LhsPage = landingPage.getLhsPage();

		SaveSearchPage SaveSearchPage = LhsPage.getSaveSearchPage();

		SaveSearchPage.emptySaveSearch();

		Alert alert = getDriverObject().switchTo().alert();

		String alertText = alert.getText();
		alert.accept();

		assertEquals(alertText, "Please give the search a name before saving.");

	}

	@Test()
	@Parameters(value = { "website", "username", "password" })
	public void renamePage(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		RhsPage RhsPage = landingPage.getRhsPage();

		AddNewPage AddNewPage = RhsPage.getAddNewPage();

		DraftPage DraftPage = AddNewPage.getDraftPage();

		AddStoryPage AddStoryPage = DraftPage.getAddStoryPage();

		AddStoryPage.addButtonStory().get();

		PublishPage PublishPage = DraftPage.getPublishPage();

		PublishedPage PublishedPage = PublishPage
				.getPublishedPage();

		try {

			PublishedPage.refereshPublishedPage();

			PublishedPage.get();

		} catch (UnhandledAlertException n) {

			System.out.println("i got an alert exception");

			Alert alert = getDriverObject().switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(alert.getText());

		}

		RhsPage.get();

		Page Page = RhsPage.clickPageinRHS(AddNewPage
				.getTimeStamp());

		RenamePage RenamePage = Page.editPage().getRenamePage();

		RenamePage.renamePage("Edit" + RenamePage.getTimeStamp())
				.getPublishPage().getPublishedPage();

		getDriverObject().navigate().refresh();

		RhsPage.get();

		assertEquals(
				RhsPage.getPageName("Edit" + RenamePage.getTimeStamp()),
				"Edit" + RenamePage.getTimeStamp());

	}

	@Test()
	@Parameters(value = { "website", "username", "password" })
	public void deletePage(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		RhsPage RhsPage = landingPage.getRhsPage();

		AddNewPage AddNewPage = RhsPage.getAddNewPage();

		DraftPage DraftPage = AddNewPage.getDraftPage();

		AddStoryPage AddStoryPage = DraftPage.getAddStoryPage();

		AddStoryPage.addButtonStory().get();

		PublishPage PublishPage = DraftPage.getPublishPage();

		log.trace("I am a logging message 1");

		PublishedPage PublishedPage = PublishPage
				.getPublishedPage();
		
		log.trace("I am a logging message 2");

		try {

			PublishedPage.refereshPublishedPage();

			PublishedPage.get();

		} catch (UnhandledAlertException n) {

			System.out.println("i got an alert exception");

			Alert alert = getDriverObject().switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(alert.getText());

		}
        
		
		log.trace("I am a logging message 3");
		assertEquals(RhsPage.getPageStatus(AddNewPage.getTimeStamp()),
				"Live");
		log.trace("I am a logging message 4");
		assertEquals("Failed because  page is not found", RhsPage
				.getNumberOfPages(AddNewPage.getTimeStamp()).size(), 1);
		
		log.trace("I am a logging message 5");
		Page Page = RhsPage.clickPageinRHS(AddNewPage
				.getTimeStamp());
		
		log.trace("I am a logging message 6");

		DeletePage DeletePage = Page.editPage().getDeletePage();
		
		log.trace("I am a logging message 7");

		DeletePage.deletePage().getRhsPage();
		
		log.trace("I am a logging message 8");

		RhsPage.get();
		
		log.trace("I am a logging message 9");

		assertEquals("Failed because more than one page is found", RhsPage
				.getNumberOfPages(AddNewPage.getTimeStamp()).size(), 0);

	}

}