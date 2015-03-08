package com.github.selenium.Tests;

import static com.github.selenium.Utility.CheckFileHash.getFileHash;
import static com.github.selenium.Utility.TypeOfHash.SHA1;
import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

import java.io.File;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.ConcreteLoginPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.Utility.FileDownloader;

/**
 * @author dtyagi
 * 
 */
public class Cp3DownloadTest extends TestSeleniumBase {

	@Test
	/**
	 * 
	 * This test is not in use at the moment
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void DownloadTest(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();
		concreteLoginPage.get();

		LandingPage landingPage = concreteLoginPage.login(username, password);
		landingPage.get();

		FileDownloader fileDownloader = new FileDownloader(getDriverObject());

		// landingPage.searchFile();

		fileDownloader.setURI(landingPage.getFile());

		File secretFile = fileDownloader.downloadFile();

		int httpStatusCode = fileDownloader.getLastDownloadHTTPStatus();

		assertEquals(httpStatusCode, 200);

		assertEquals(getFileHash(secretFile, SHA1), ("75843d15224b70be7b97134508b9cdc8a2d79186"));

	}
	
	

}