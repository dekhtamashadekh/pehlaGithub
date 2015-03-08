package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertTrue;

import java.util.Random;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.ConcreteLoginPage;
import com.github.selenium.PageObjects.Cp3AddCommPage;
import com.github.selenium.PageObjects.Cp3AddStore1Page;
import com.github.selenium.PageObjects.Cp3AddStore2Page;
import com.github.selenium.PageObjects.Cp3AddStore3Page;
import com.github.selenium.PageObjects.Cp3AddStore4Page;
import com.github.selenium.PageObjects.Cp3CommPage;
import com.github.selenium.PageObjects.Cp3ManageStoresPage;
import com.github.selenium.PageObjects.LandingPage;

public class Cp3CommTest extends TestSeleniumBase {

	@Test(groups = {"noSafari"})
	@Parameters(value = { "website", "username", "password" })
	public void cp3AddAnnouncement(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		Cp3CommPage cp3CommPage = landingPage.getCp3CommPage();

		Cp3AddCommPage cp3AddCommPage = cp3CommPage.getCp3AddCommPage();
		
		int random = new Random().nextInt(90);

		cp3AddCommPage.addAnnouncement(random,cp3AddCommPage.getTimeStamp());
		
		assertTrue(cp3CommPage.searchCommunication(random+cp3AddCommPage.getTimeStamp()).size()>0);

	}
	
	//@Test(groups = {"noSafari"})
	@Parameters(value = { "website", "username", "password" })
	public void cp3AddSPAnn(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		Cp3CommPage cp3CommPage = landingPage.getCp3CommPage();

		Cp3AddCommPage cp3AddCommPage = cp3CommPage.getCp3AddCommPage();
		
		int random = new Random().nextInt(90);

		cp3AddCommPage.addAnnouncement(random,cp3AddCommPage.getTimeStamp()+"£%^%&^");
		
		assertTrue(cp3CommPage.searchCommunication(random+cp3AddCommPage.getTimeStamp()+"£%^%&^").size()>0);

	}
	
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void cp3AddStore(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		Cp3CommPage cp3CommPage = landingPage.getCp3CommPage();
		
		Cp3ManageStoresPage cp3ManageStoresPage = cp3CommPage.getCp3ManageStoresPage();
		
		Cp3AddStore1Page cp3AddStore1Page = cp3ManageStoresPage.getCp3AddStore1Page();
		
		Cp3AddStore2Page cp3AddStore2Page = cp3AddStore1Page.addStoreName(cp3AddStore1Page.getTimeStamp());
		
		Cp3AddStore3Page cp3AddStore3Page = cp3AddStore2Page.getCp3AddStore3Page();
	
		Cp3AddStore4Page cp3AddStore4Page = cp3AddStore3Page.getCp3AddStore4Page();
		
		cp3AddStore4Page.addStore();
	
		assertTrue(cp3ManageStoresPage.searchStore(cp3AddStore1Page.getTimeStamp()).size()>0);
		

	}
	
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void cp3AddSPStore(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		Cp3CommPage cp3CommPage = landingPage.getCp3CommPage();
		
		Cp3ManageStoresPage cp3ManageStoresPage = cp3CommPage.getCp3ManageStoresPage();
		
		Cp3AddStore1Page cp3AddStore1Page = cp3ManageStoresPage.getCp3AddStore1Page();
		
		Cp3AddStore2Page cp3AddStore2Page = cp3AddStore1Page.addStoreName(cp3AddStore1Page.getTimeStamp()+"£%^%&^");
		
		Cp3AddStore3Page cp3AddStore3Page = cp3AddStore2Page.getCp3AddStore3Page();
	
		Cp3AddStore4Page cp3AddStore4Page = cp3AddStore3Page.getCp3AddStore4Page();
		
		cp3AddStore4Page.addStore();
	
		assertTrue(cp3ManageStoresPage.searchStore(cp3AddStore1Page.getTimeStamp()+"£%^%&^").size()>0);
		

	}
	
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void cp3AddInvalidStore(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		getDriverObject().get(website + "core/root/index.cfm?pageId=storeComms&page_layout=vintage");

		Cp3CommPage cp3CommPage = landingPage.getCp3CommPage();
		
		Cp3ManageStoresPage cp3ManageStoresPage = cp3CommPage.getCp3ManageStoresPage();
		
		Cp3AddStore1Page cp3AddStore1Page = cp3ManageStoresPage.getCp3AddStore1Page();
		
		
		
		assertTrue("Some or all expected message not displayed",cp3AddStore1Page.addNoName("").size()==5);
		
	}
	
	
	
}