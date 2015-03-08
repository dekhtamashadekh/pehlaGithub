package com.github.selenium.PageObjects;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import java.util.ArrayList;
import java.util.List;

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
public class AdminPage extends PageLoadConditions {

	@FindBy(how = How.LINK_TEXT, using = "User Management")
	WebElement UserManagement;

	//	@FindBy(how = How.LINK_TEXT, using = "New user")
	//	WebElement AddNewUser;
	//
	@FindBy(how = How.LINK_TEXT, using = "User groups")
	WebElement UserGroups;

	//
	//	@FindBy(how = How.CSS, using = "span.ui-button-text")
	//	WebElement AddNewGroup;
	//
		//@FindBy(how = How.ID, using = "adminMenu")
		//WebElement AiseHe;
	
	

	public AdminPage() {

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
			
			

			list.add(this.UserManagement.isDisplayed());

			//			list.add(this.AddNewUser.isDisplayed());
			//			
			//			list.add(this.UserGroups.isDisplayed());
			//			
			//			list.add(this.AddNewGroup.isDisplayed());
			//			
			//			list.add(this.Users.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}

	/**
	 * @return an instance of Add User Group page
	 */
	public UserGroupPage getUserGroupPage() {

		this.UserManagement.click();

		this.UserGroups.click();

		return new UserGroupPage();
	}

}
