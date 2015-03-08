package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author dtyagi Class to represent Cp2 Folder Page
 * 
 */
public class Cp2FolderPage extends LoadableComponent<Cp2FolderPage> {

	@FindBy(how = How.ID, using = "MainScreen")
	WebElement MainScreenFrame;

	@FindBy(how = How.ID, using = "delFolder")
	WebElement DeleteFolder;

	@FindBy(how = How.XPATH, using = "(//button[@type='button'])[text()='Delete all items']")
	WebElement DeleteAllItems;

	@FindBy(how = How.XPATH, using = "(//a[contains(text(),'ADD NEW ITEM')])[2]")
	WebElement AddNewItem;

	@FindBy(how = How.ID, using = "delFilesOptionFolder")
	WebElement OptionsToDeleteFilesInAFolder;

	@FindBy(how = How.XPATH, using = "(//button[@type='button'])[text()='Delete']")
	WebElement DeleteFolderWithFiles;

	@FindBy(how = How.CSS, using = "a.ListFileLink")
	WebElement downloadLink;

	/**
	 * 
	 */
	public Cp2FolderPage() {

		PageFactory.initElements(getDriverObject(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	protected void isLoaded() throws Error {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(getDriverObject(), 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * This is a method to delete empty folder
	 */
	public void deleteEmptyFolder() {

		this.DeleteFolder.click();

		this.DeleteAllItems.click();

	}

	/**
	 * This is a method to delete folder with files
	 */
	public void deleteFolderContainingFile() {

		this.DeleteFolder.click();

		this.OptionsToDeleteFilesInAFolder.click();

		this.DeleteFolderWithFiles.click();

		this.DeleteAllItems.click();

	}

	/**
	 * @return This method returns and instance of CP2 Upload File Page
	 */
	public Cp2UploadFilePage getCp2UploadFilePage() {

		this.AddNewItem.click();

		return new Cp2UploadFilePage();

	}

	/**
	 * @return List of WebElements matching unloaded file name
	 */
	public List<WebElement> getFileName() {

		getDriverObject().switchTo().defaultContent();

		getDriverObject().switchTo().frame(MainScreenFrame);

		return getDriverObject().findElements(By.cssSelector("td > span[title='TestFlow.jpg']"));
	}

	/**
	 * @return a String which is link to download the file
	 */
	public String getFileDownloadLink() {

		return this.downloadLink.getAttribute("href");

	}

}