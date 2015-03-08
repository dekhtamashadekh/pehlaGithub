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
import com.github.selenium.PageObjects.ConcreteLoginPage;
import com.github.selenium.PageObjects.Cp2FolderPage;
import com.github.selenium.PageObjects.Cp2LandingPage;
import com.github.selenium.PageObjects.Cp2UploadFilePage;
import com.github.selenium.PageObjects.LandingPage;
import com.github.selenium.PageObjects.TestZonePage;
import com.github.selenium.Utility.FileDownloader;

/**
 * @author dtyagi
 * 
 */
public class Cp2FolderTest extends TestSeleniumBase {

	@Test
	/**
	 * 
	 * This is a test to create a CP2 Folder
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void createAFolder(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();

		concreteLoginPage.get();

		LandingPage landingPage = concreteLoginPage.login(username, password);
		landingPage.get();

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();
		cp2LandingPage.get();

		TestZonePage testZonePage = cp2LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addCp2Folder();

		testZonePage.get();
		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

	}

	@Test
	/**
	 * 
	 * This is a test to create CP2 folder and upload file into it
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void uploadAFileToCp2Folder(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();
		concreteLoginPage.get();

		LandingPage landingPage = concreteLoginPage.login(username, password);
		landingPage.get();

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();
		cp2LandingPage.get();

		TestZonePage testZonePage = cp2LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addCp2Folder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		Cp2FolderPage cp2FolderPage = testZonePage.getCP2FolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		cp2FolderPage.get();

		Cp2UploadFilePage cp2UploadFilePage = cp2FolderPage.getCp2UploadFilePage();
		cp2UploadFilePage.get();

		cp2UploadFilePage.uploadFile();

		assertEquals(cp2FolderPage.getFileName().size(), 1);

	}

	@Test
	/**
	 * 
	 * This is a test to create and delete empty CP2 folder
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void deleteEmptyCp2Folder(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();
		concreteLoginPage.get();

		LandingPage landingPage = concreteLoginPage.login(username, password);
		landingPage.get();

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();
		cp2LandingPage.get();

		TestZonePage testZonePage = cp2LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addCp2Folder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		Cp2FolderPage cp2FolderPage = testZonePage.getCP2FolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		cp2FolderPage.get();

		cp2FolderPage.deleteEmptyFolder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 0);

	}

	@Test
	/**
	 * 
	 * This is a test to create a CP2 folder, upload file into it and delete it
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Parameters(value = { "website", "username", "password" })
	public void deleteCp2FolderWithFile(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();
		concreteLoginPage.get();

		LandingPage landingPage = concreteLoginPage.login(username, password);
		landingPage.get();

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();
		cp2LandingPage.get();

		TestZonePage testZonePage = cp2LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addCp2Folder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		Cp2FolderPage cp2FolderPage = testZonePage.getCP2FolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		cp2FolderPage.get();

		Cp2UploadFilePage cp2UploadFilePage = cp2FolderPage.getCp2UploadFilePage();
		cp2UploadFilePage.get();

		cp2UploadFilePage.uploadFile();

		assertEquals(cp2FolderPage.getFileName().size(), 1);

	}

	/**
	 * This is a test to create a cp2 folder, upload file to it and then
	 * download and verify the downloaded file
	 * 
	 * @param website
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@Test
	@Parameters(value = { "website", "username", "password" })
	public void downloadAFileToCp2FolderBeta(String website, String username, String password) throws Exception {

		getDriverObject().get(website);

		ConcreteLoginPage concreteLoginPage = new ConcreteLoginPage();
		concreteLoginPage.get();

		LandingPage landingPage = concreteLoginPage.login(username, password);
		landingPage.get();

		Cp2LandingPage cp2LandingPage = landingPage.getCp2LandingPage();
		cp2LandingPage.get();

		TestZonePage testZonePage = cp2LandingPage.getTestZonePage();
		testZonePage.get();

		AddNewFolderPage addNewFolderPage = testZonePage.getAddNewFolderPage();
		addNewFolderPage.get();

		addNewFolderPage.addCp2Folder();

		assertEquals(testZonePage.getFolderLink(addNewFolderPage.getAddNewFolderPageTimeStamp()).size(), 1);

		Cp2FolderPage cp2FolderPage = testZonePage.getCP2FolderPage(addNewFolderPage.getAddNewFolderPageTimeStamp());
		cp2FolderPage.get();

		Cp2UploadFilePage cp2UploadFilePage = cp2FolderPage.getCp2UploadFilePage();
		cp2UploadFilePage.get();

		cp2UploadFilePage.uploadFile();

		assertEquals(cp2FolderPage.getFileName().size(), 1);

		FileDownloader fileDownloader = new FileDownloader(getDriverObject());

		fileDownloader.setURI(cp2FolderPage.getFileDownloadLink());

		File secretFile = fileDownloader.downloadFile();

		int httpStatusCode = fileDownloader.getLastDownloadHTTPStatus();

		assertEquals(httpStatusCode, 200);

		assertEquals(getFileHash(secretFile, SHA1), "5f1a3b352439e88701a7975cc8285c5bd0bf7258");

	}

}