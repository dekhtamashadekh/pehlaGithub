package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.AddGroupPage;
import com.github.selenium.PageObjects.AdminPage;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.LandingPage;

/**
 * @author 
 * 
 */
public class AddUserGroupDeleteUserGroupTest extends TestSeleniumBase {

	/**
	 * 
	 * This is a test to add user group
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void addUserGroup(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);

		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		AdminPage AdminPage = LandingPage.getAdminPage();
		AdminPage.get();

		AddGroupPage addGroupPage = AdminPage.getAddGroupPage();
		addGroupPage.get();

		addGroupPage.addUserGroup();

		assertEquals(addGroupPage.getUserGroup().size(), 1);

	}

	/**
	 * This is a test to add and delete user group
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void deleteUserGroup(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);
		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		AdminPage AdminPage = LandingPage.getAdminPage();
		AdminPage.get();

		AddGroupPage addGroupPage = AdminPage.getAddGroupPage();
		addGroupPage.get();

		addGroupPage.addUserGroup();

		assertEquals(addGroupPage.getUserGroup().size(), 1);

		addGroupPage.deleteUserGroup();

		assertEquals(addGroupPage.getUserGroup().size(), 0);

	}

}