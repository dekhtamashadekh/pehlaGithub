package com.github.selenium.Tests;

//This is Beta class, still under development and test

import static com.github.selenium.Utility.CheckFileHash.getFileHash;
import static com.github.selenium.Utility.TypeOfHash.SHA1;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;
import com.github.selenium.PageObjects.AddNewFolderPage;
import com.github.selenium.PageObjects.LoginPage;
import com.github.selenium.PageObjects.FolderPage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.UploadFilePage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.TestZonePage;
import com.github.selenium.Utility.FileDownloader;

/**
 * @author 
 * 
 */
public class FolderTest extends TestSeleniumBase {

	@Test
	/**
	 * 
	 * This is a test to create a  Folder
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void createAFolder(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();

		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);
		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		TestZonePage testZonePage = LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addFolder();

		testZonePage.get();
		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

	}

	@Test
	/**
	 * 
	 * This is a test to create  folder and upload file into it
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void uploadAFileToFolder(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);
		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		TestZonePage testZonePage = LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addFolder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		FolderPage FolderPage = testZonePage.getFolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		FolderPage.get();

		UploadFilePage UploadFilePage = FolderPage.getUploadFilePage();
		UploadFilePage.get();

		UploadFilePage.uploadFile();

		assertEquals(FolderPage.getFileName().size(), 1);

	}

	@Test
	/**
	 * 
	 * This is a test to create and delete empty  folder
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void deleteEmptyFolder(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);
		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		TestZonePage testZonePage = LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addFolder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		FolderPage FolderPage = testZonePage.getFolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		FolderPage.get();

		FolderPage.deleteEmptyFolder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 0);

	}

	@Test
	/**
	 * 
	 * This is a test to create a  folder, upload file into it and delete it
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void deleteFolderWithFile(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);
		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		TestZonePage testZonePage = LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addFolder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		FolderPage FolderPage = testZonePage.getFolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		FolderPage.get();

		UploadFilePage UploadFilePage = FolderPage.getUploadFilePage();
		UploadFilePage.get();

		UploadFilePage.uploadFile();

		assertEquals(FolderPage.getFileName().size(), 1);

	}

	/**
	 * This is a test to create a  folder, upload file to it and then
	 * download and verify the downloaded file
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void downloadAFileToFolderBeta(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		LoginPage LoginPage = new LoginPage();
		LoginPage.get();

		LandingPage landingPage = LoginPage.login(username, password);
		landingPage.get();

		LandingPage LandingPage = landingPage.getLandingPage();
		LandingPage.get();

		TestZonePage testZonePage = LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addFolder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		FolderPage FolderPage = testZonePage.getFolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		FolderPage.get();

		UploadFilePage UploadFilePage = FolderPage.getUploadFilePage();
		UploadFilePage.get();

		UploadFilePage.uploadFile();

		assertEquals(FolderPage.getFileName().size(), 1);

		FileDownloader fileDownloader = new FileDownloader(getDriverObject());

		fileDownloader.setURI(FolderPage.getFileDownloadLink());

		File secretFile = fileDownloader.downloadFile();

		int httpStatusCode = fileDownloader.getLastDownloadHTTPStatus();

		assertEquals(httpStatusCode, 200);

		assertEquals(getFileHash(secretFile, SHA1), "5f1a3b352439e88701a7975cc8285c5bd0bf7258");

	}

}