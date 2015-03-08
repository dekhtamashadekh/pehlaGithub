package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.ConcreteLoginPage;
import com.github.selenium.PageObjects.LandingPage;

/**
 * @author dtyagi
 * 
 */
public class ConcreteLoginTest extends TestSeleniumBase {

	/**
	 * 
	 * This is a test for successful login
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void loginTest(String website, String username, String password)
			throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		LandingPage landingPage = concreteLoginPage.login(username, password);

		assertEquals(landingPage.getAdministrationLink(), "Administration");

	}

	/**
	 * 
	 * This is a test to check invalid login displays expected error message
	 * 
	 * @param website
	 * @param username
	 * @param wrongPassword
	 * @throws Exception
	 */
	@Test
	@Parameters(value = { "website", "username", "wrongPassword" })
	public void invalidLoginTest(String website, String username,
			String wrongPassword) throws Exception {

		getDriverObject().get(website);

		// loadScript();

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		concreteLoginPage.get();

		concreteLoginPage.invalidLogin(username, wrongPassword);

		assertEquals(concreteLoginPage.getErrorMessage(),
				"Invalid login, please try again.");

	}

}