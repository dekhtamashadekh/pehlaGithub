package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Parameters;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.AddUserPage;
import com.github.selenium.PageObjects.ConcreteAdminPage;
import com.github.selenium.PageObjects.ConcreteLoginPage;
import com.github.selenium.PageObjects.Cp2LandingPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.UserPage;

/**
 * @author dtyagi
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

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();
		concreteLoginPage.get();


		LandingPage landingPage = concreteLoginPage.login(username, password);

		landingPage.get();
		
		System.out.println("delete after use 1");

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();

		cp2LandingPage.get();
		
		System.out.println("delete after use 2");

		ConcreteAdminPage concreteAdminPage = cp2LandingPage.getConcreteAdminPage();
		
		System.out.println("delete after use 3");

		concreteAdminPage.get();

		AddUserPage addUserPage = concreteAdminPage.getAddUserPage();

		addUserPage.get();

		addUserPage.addUser();

		UserPage userPage = concreteAdminPage.getUserPage();
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

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();

		ConcreteAdminPage concreteAdminPage = cp2LandingPage.getConcreteAdminPage();

		AddUserPage addUserPage = concreteAdminPage.getAddUserPage();

		addUserPage.addUser();

		UserPage userPage = concreteAdminPage.getUserPage();

		userPage.get();

		userPage.searchUser("autouser" + addUserPage.getaddUserPageTimeStamp());

		userPage.deleteUser();

		assertEquals(userPage.getNumberOfUsers(addUserPage.getaddUserPageTimeStamp()).size(), 1);

		assertEquals(userPage.getUserGroupText(), "*Deleted");
	}

}