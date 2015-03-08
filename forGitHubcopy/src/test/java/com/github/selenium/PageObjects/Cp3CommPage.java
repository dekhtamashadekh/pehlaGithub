package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.github.selenium.PageLoadConditions;

/**
 * @author dtyagi
 * 
 */
public class Cp3CommPage extends PageLoadConditions {
	
	@FindBy(how = How.ID, using = "storecommnavbutton1")
	WebElement AddCommunication;
	
	@FindBy(how = How.ID, using = "storecommnavbutton2")
	WebElement ManagePermission;
	
	@FindBy(how = How.ID, using = "storecommnavbutton3")
	WebElement ManageTags;
	
	@FindBy(how = How.ID, using = "storecommnavbutton4")
	WebElement ManageStores;
	
	
	@FindBy(how = How.ID, using = "keywords")
	WebElement TextSearchField;
	
	@FindBy(how = How.ID, using = "filter")
	WebElement FilterButton;
	
	
	

	public Cp3CommPage() {

		PageFactory.initElements(getDriverObject(), this);
		this.get();

	}

	/**
	 * @return
	 */
	@Override
	public List<Boolean> allLoaded() {

		List<Boolean> list = new ArrayList<Boolean>();

		try {
			
			list.add(this.AddCommunication.isDisplayed());
			
			list.add(this.ManagePermission.isDisplayed());
			
			list.add(this.ManageTags.isDisplayed());
			
			list.add(this.ManageStores.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	
	
	public Cp3AddCommPage getCp3AddCommPage() {
		this.AddCommunication.click();
		
		return new Cp3AddCommPage();
	}
	
	public List<WebElement> searchCommunication(String name) {
		
		this.TextSearchField.sendKeys(name);
		
		this.FilterButton.click();
		
		return getDriverObject().findElements(By.xpath("//table[@id='storeCommsTable']/tbody/tr/td[contains(text(),'" +name+ "')]"));
		
	}
	
	
	public Cp3ManageStoresPage getCp3ManageStoresPage(){
		
		this.ManageStores.click();
		return new Cp3ManageStoresPage();
	}
	
	
}
