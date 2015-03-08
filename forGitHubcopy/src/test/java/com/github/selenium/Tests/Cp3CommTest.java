package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertTrue;

import java.util.Random;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.AddCommPage;
import com.github.selenium.PageObjects.AddStore1Page;
import com.github.selenium.PageObjects.AddStore2Page;
import com.github.selenium.PageObjects.AddStore3Page;
import com.github.selenium.PageObjects.AddStore4Page;
import com.github.selenium.PageObjects.CommPage;
import com.github.selenium.PageObjects.ManageStoresPage;
import com.github.selenium.PageObjects.LandingPage;

public class CommTest extends TestSeleniumBase {

	@Test(groups = {"noSafari"})
	@Parameters(value = { "website", "username", "password" })
	public void AddAnnouncement(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		CommPage CommPage = landingPage.getCommPage();

		AddCommPage AddCommPage = CommPage.getAddCommPage();
		
		int random = new Random().nextInt(90);

		AddCommPage.addAnnouncement(random,AddCommPage.getTimeStamp());
		
		assertTrue(CommPage.searchCommunication(random+AddCommPage.getTimeStamp()).size()>0);

	}
	
	//@Test(groups = {"noSafari"})
	@Parameters(value = { "website", "username", "password" })
	public void AddSPAnn(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		CommPage CommPage = landingPage.getCommPage();

		AddCommPage AddCommPage = CommPage.getAddCommPage();
		
		int random = new Random().nextInt(90);

		AddCommPage.addAnnouncement(random,AddCommPage.getTimeStamp()+"£%^%&^");
		
		assertTrue(CommPage.searchCommunication(random+AddCommPage.getTimeStamp()+"£%^%&^").size()>0);

	}
	
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void AddStore(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		CommPage CommPage = landingPage.getCommPage();
		
		ManageStoresPage ManageStoresPage = CommPage.getManageStoresPage();
		
		AddStore1Page AddStore1Page = ManageStoresPage.getAddStore1Page();
		
		AddStore2Page AddStore2Page = AddStore1Page.addStoreName(AddStore1Page.getTimeStamp());
		
		AddStore3Page AddStore3Page = AddStore2Page.getAddStore3Page();
	
		AddStore4Page AddStore4Page = AddStore3Page.getAddStore4Page();
		
		AddStore4Page.addStore();
	
		assertTrue(ManageStoresPage.searchStore(AddStore1Page.getTimeStamp()).size()>0);
		

	}
	
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void AddSPStore(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		CommPage CommPage = landingPage.getCommPage();
		
		ManageStoresPage ManageStoresPage = CommPage.getManageStoresPage();
		
		AddStore1Page AddStore1Page = ManageStoresPage.getAddStore1Page();
		
		AddStore2Page AddStore2Page = AddStore1Page.addStoreName(AddStore1Page.getTimeStamp()+"£%^%&^");
		
		AddStore3Page AddStore3Page = AddStore2Page.getAddStore3Page();
	
		AddStore4Page AddStore4Page = AddStore3Page.getAddStore4Page();
		
		AddStore4Page.addStore();
	
		assertTrue(ManageStoresPage.searchStore(AddStore1Page.getTimeStamp()+"£%^%&^").size()>0);
		

	}
	
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void AddInvalidStore(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		CommPage CommPage = landingPage.getCommPage();
		
		ManageStoresPage ManageStoresPage = CommPage.getManageStoresPage();
		
		AddStore1Page AddStore1Page = ManageStoresPage.getAddStore1Page();
		
		
		
		assertTrue("Some or all expected message not displayed",AddStore1Page.addNoName("").size()==5);
		
	}
	
	
	
}