package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Parameters;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.AddUserPage;
import com.github.selenium.PageObjects.AdminPage;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.UserPage;

/**
 * @author 
 * 
 */
public class UserTest extends TestSeleniumBase {

	/**
	 * 
	 * This is a test to create a user
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	//@Test
	@Parameters(value = { "website", "username", "password" })
	public void createUserTest(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();


		LandingPage landingPage = LoginPage.login(username, password);

		landingPage.get();
		
		System.out.println("delete after use 1");

		LandingPage LandingPage = landingPage.getLandingPage();

		LandingPage.get();
		
		System.out.println("delete after use 2");

		AdminPage AdminPage = LandingPage.getAdminPage();
		
		System.out.println("delete after use 3");

		AdminPage.get();

		AddUserPage addUserPage = AdminPage.getAddUserPage();

		addUserPage.get();

		addUserPage.addUser();

		UserPage userPage = AdminPage.getUserPage();
		System.out.println("delete after use 4");

		userPage.get();

		userPage.searchUser("autouser" + addUserPage.getaddUserPageTimeStamp());

		assertEquals(userPage.getNumberOfUsers(addUserPage.getaddUserPageTimeStamp()).size(), 1);

	}

	/**
	 * 
	 * This is a test to create and delete a user
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	//@Test
	@Parameters(value = { "website", "username", "password" })
	public void deleteUserTest(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LandingPage landingPage = LoginPage.login(username, password);

		LandingPage LandingPage = landingPage.getLandingPage();

		AdminPage AdminPage = LandingPage.getAdminPage();

		AddUserPage addUserPage = AdminPage.getAddUserPage();

		addUserPage.addUser();

		UserPage userPage = AdminPage.getUserPage();

		userPage.get();

		userPage.searchUser("autouser" + addUserPage.getaddUserPageTimeStamp());

		userPage.deleteUser();

		assertEquals(userPage.getNumberOfUsers(addUserPage.getaddUserPageTimeStamp()).size(), 1);

		assertEquals(userPage.getUserGroupText(), "*Deleted");
	}

}