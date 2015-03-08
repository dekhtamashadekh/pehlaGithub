package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.Cp3AddNewPage;
import com.github.selenium.PageObjects.Cp3AddStoryPage;
import com.github.selenium.PageObjects.Cp3DeletePage;
import com.github.selenium.PageObjects.Cp3DraftPage;
import com.github.selenium.PageObjects.Cp3FolderPage;
import com.github.selenium.PageObjects.Cp3LhsPage;
import com.github.selenium.PageObjects.Cp3MySavedSearchPage;
import com.github.selenium.PageObjects.Cp3Page;
import com.github.selenium.PageObjects.Cp3PublishPage;
import com.github.selenium.PageObjects.Cp3PublishedPage;
import com.github.selenium.PageObjects.Cp3RenamePage;
import com.github.selenium.PageObjects.Cp3RhsPage;
import com.github.selenium.PageObjects.Cp3SaveSearchPage;
import com.github.selenium.PageObjects.Cp3SubfolderSelectorPanelPage;
import com.github.selenium.PageObjects.LandingPage;

public class Cp3Test extends TestSeleniumBase {

	@Test(groups = { "noSafari" })
	@Parameters(value = { "website", "username", "password" })
	public void createCp3PageTest(String website, String username,
			String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		Cp3RhsPage cp3RhsPage = landingPage.getCp3RhsPage();

		Cp3AddNewPage cp3AddNewPage = cp3RhsPage.getCp3AddNewPage();

		Cp3DraftPage cp3DraftPage = cp3AddNewPage.getCp3DraftPage();

		Cp3AddStoryPage cp3AddStoryPage = cp3DraftPage.getCp3AddStoryPage();

		cp3AddStoryPage.addButtonStory().get();

		Cp3PublishPage cp3PublishPage = cp3DraftPage.getCp3PublishPage();

		Cp3PublishedPage Cp3PublishedPage = cp3PublishPage
				.getCp3PublishedPage();

		try {

			Cp3PublishedPage.refereshPublishedPage();

			Cp3PublishedPage.get();

		} catch (UnhandledAlertException n) {

			System.out.println("i got an alert exception");

			Alert alert = getDriverObject().switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(alert.getText());

		}

		assertEquals(cp3RhsPage.getPageStatus(cp3AddNewPage.getTimeStamp()),
				"Live");
	}

	@Test()
	@Parameters(value = { "website", "username", "password" })
	public void lhsTest(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		Cp3LhsPage cp3LhsPage = landingPage.getCp3LhsPage();

		Cp3SubfolderSelectorPanelPage cp3SubFolderSelectorPanelPage = cp3LhsPage
				.getCp3SubfolderSelectorPanelPage();

		Cp3FolderPage cp3FolderPage = cp3SubFolderSelectorPanelPage
				.getCp3FolderPage(3);

		assertTrue(cp3FolderPage.getNumberOfFolders() > 0);

		Cp3SaveSearchPage cp3SaveSearchPage = cp3LhsPage.getCp3SaveSearchPage();

		cp3SaveSearchPage.saveSearch();

		cp3LhsPage.get();

		Cp3MySavedSearchPage cp3MySavedSearchPage = cp3LhsPage
				.getCp3MySavedSearchPage();

		cp3MySavedSearchPage.applySavedSearch(cp3SaveSearchPage.getTimeStamp());

		assertTrue(cp3FolderPage.getNumberOfFolders() > 01);

	}

	@Test(groups = { "noSafari" })
	@Parameters(value = { "website", "username", "password" })
	public void invalidCp3PageTest(String website, String username,
			String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		Cp3RhsPage cp3RhsPage = landingPage.getCp3RhsPage();

		Cp3AddNewPage cp3AddNewPage = cp3RhsPage.getCp3AddNewPage();

		cp3AddNewPage.createPage("");

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

		Cp3LhsPage cp3LhsPage = landingPage.getCp3LhsPage();

		Cp3SaveSearchPage cp3SaveSearchPage = cp3LhsPage.getCp3SaveSearchPage();

		cp3SaveSearchPage.emptySaveSearch();

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

		Cp3RhsPage cp3RhsPage = landingPage.getCp3RhsPage();

		Cp3AddNewPage cp3AddNewPage = cp3RhsPage.getCp3AddNewPage();

		Cp3DraftPage cp3DraftPage = cp3AddNewPage.getCp3DraftPage();

		Cp3AddStoryPage cp3AddStoryPage = cp3DraftPage.getCp3AddStoryPage();

		cp3AddStoryPage.addButtonStory().get();

		Cp3PublishPage cp3PublishPage = cp3DraftPage.getCp3PublishPage();

		Cp3PublishedPage Cp3PublishedPage = cp3PublishPage
				.getCp3PublishedPage();

		try {

			Cp3PublishedPage.refereshPublishedPage();

			Cp3PublishedPage.get();

		} catch (UnhandledAlertException n) {

			System.out.println("i got an alert exception");

			Alert alert = getDriverObject().switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(alert.getText());

		}

		cp3RhsPage.get();

		Cp3Page cp3Page = cp3RhsPage.clickPageinRHS(cp3AddNewPage
				.getTimeStamp());

		Cp3RenamePage cp3RenamePage = cp3Page.editPage().getRenamePage();

		cp3RenamePage.renamePage("Edit" + cp3RenamePage.getTimeStamp())
				.getCp3PublishPage().getCp3PublishedPage();

		getDriverObject().navigate().refresh();

		cp3RhsPage.get();

		assertEquals(
				cp3RhsPage.getPageName("Edit" + cp3RenamePage.getTimeStamp()),
				"Edit" + cp3RenamePage.getTimeStamp());

	}

	@Test()
	@Parameters(value = { "website", "username", "password" })
	public void deletePage(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		Cp3RhsPage cp3RhsPage = landingPage.getCp3RhsPage();

		Cp3AddNewPage cp3AddNewPage = cp3RhsPage.getCp3AddNewPage();

		Cp3DraftPage cp3DraftPage = cp3AddNewPage.getCp3DraftPage();

		Cp3AddStoryPage cp3AddStoryPage = cp3DraftPage.getCp3AddStoryPage();

		cp3AddStoryPage.addButtonStory().get();

		Cp3PublishPage cp3PublishPage = cp3DraftPage.getCp3PublishPage();

		log.trace("I am a logging message 1");

		Cp3PublishedPage Cp3PublishedPage = cp3PublishPage
				.getCp3PublishedPage();
		
		log.trace("I am a logging message 2");

		try {

			Cp3PublishedPage.refereshPublishedPage();

			Cp3PublishedPage.get();

		} catch (UnhandledAlertException n) {

			System.out.println("i got an alert exception");

			Alert alert = getDriverObject().switchTo().alert();
			// Get the text of the alert or prompt
			System.out.println(alert.getText());

		}
        
		
		log.trace("I am a logging message 3");
		assertEquals(cp3RhsPage.getPageStatus(cp3AddNewPage.getTimeStamp()),
				"Live");
		log.trace("I am a logging message 4");
		assertEquals("Failed because CP3 page is not found", cp3RhsPage
				.getNumberOfPages(cp3AddNewPage.getTimeStamp()).size(), 1);
		
		log.trace("I am a logging message 5");
		Cp3Page cp3Page = cp3RhsPage.clickPageinRHS(cp3AddNewPage
				.getTimeStamp());
		
		log.trace("I am a logging message 6");

		Cp3DeletePage cp3DeletePage = cp3Page.editPage().getDeletePage();
		
		log.trace("I am a logging message 7");

		cp3DeletePage.deletePage().getCp3RhsPage();
		
		log.trace("I am a logging message 8");

		cp3RhsPage.get();
		
		log.trace("I am a logging message 9");

		assertEquals("Failed because more than one page is found", cp3RhsPage
				.getNumberOfPages(cp3AddNewPage.getTimeStamp()).size(), 0);

	}

}