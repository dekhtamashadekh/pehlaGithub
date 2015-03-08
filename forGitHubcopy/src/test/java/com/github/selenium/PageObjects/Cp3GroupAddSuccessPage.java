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
public class GroupAddSuccessPage extends PageLoadConditions {
	
	@FindBy(how = How.CSS, using = "ui-dialog-buttonset.ui-button-text")
	WebElement UserGroupOk;

	public GroupAddSuccessPage() {

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
			
			list.add(this.UserGroupOk.isDisplayed());

		} catch (StaleElementReferenceException e) {

			System.out.println("Some elements were stale, trying again");

		}

		return list;

	}
	
	public UserGroupPage pressOk() {
		
		this.UserGroupOk.click();
		
		return new UserGroupPage();
	}

}
