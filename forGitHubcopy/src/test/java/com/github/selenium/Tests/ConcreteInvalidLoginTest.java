package com.github.selenium.Tests;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.ConcreteLoginPage;

/**
 * @author dtyagi
 * 
 */
public class ConcreteInvalidLoginTest extends TestSeleniumBase {

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
	public void invalidLoginTest(String website, String username, String wrongPassword) throws Exception {

		getDriverObject().get(website);

		// loadScript();

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		concreteLoginPage.get();

		concreteLoginPage.invalidLogin(username, wrongPassword);


		assertEquals(concreteLoginPage.getErrorMessage(), "Invalid login, please try again.");

	}

}