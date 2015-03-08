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
 * @author 
 * 
 */
public class UserGroupPage extends PageLoadConditions {
	
	@FindBy(how = How.ID, using = "addGroup")
	WebElement AddNewUserGroup;

	

	public UserGroupPage() {

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
			
			list.add(this.AddNewUserGroup.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}
	
	
	public AddNewGroupPage getAddNewGroupPage() {
		
		this.AddNewUserGroup.click();
		
		return new AddNewGroupPage();
		
	}
	
	public List<WebElement> getUserGroup(String timeStamp) {

		return getDriverObject().findElements(By.linkText("testautogroup" + timeStamp));

	}

}
