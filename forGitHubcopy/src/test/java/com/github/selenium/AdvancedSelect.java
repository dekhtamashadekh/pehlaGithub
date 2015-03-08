package com.github.selenium;

import static com.github.selenium.TestSeleniumBase.getDriverObject;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * @author dtyagi
 * This is a class to imporve Selenium select functionality
 * This waits if the dropdown is loading
 *
 */
public class AdvancedSelect extends Select {

	public WebDriverWait wait = new WebDriverWait(getDriverObject(), 30);

	public AdvancedSelect(WebElement element) {
		super(element);
	}

	@Override
	public void selectByIndex(int index) {
		String match = String.valueOf(index);

		try {

			ExpectedCondition<Boolean> notLoading = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					Boolean elementReady = false;

					int maxCount = 2;

					for (int count = 0; count < maxCount; count++) {

						if (!StringUtils.containsIgnoreCase(getOptions().get(0).getText(), "loading") && (!getOptions().get(0).getText().equals(""))) {

							elementReady = true;
						} else {

							try {
								Thread.sleep(30);
							} catch (InterruptedException e) {
								System.out.println("I got interupped byu another thread");
								e.printStackTrace();
							}
							elementReady = false;
						}

					}

					return elementReady;

				}
			};

			wait.until(notLoading);

		} catch (StaleElementReferenceException e) {
			System.out.println("stale elemnent during select. Trying again");

		}

		boolean matched = false;
		for (WebElement option : getOptions()) {
			if (match.equals(option.getAttribute("index"))) {
				setSelected(option);
				if (!isMultiple()) {
					return;
				}
				matched = true;
			}
		}
		if (!matched) {
			throw new NoSuchElementException("Cannot locate option with index: " + index);
		}
	}

	private void setSelected(WebElement option) {
		if (!option.isSelected()) {
			option.click();
		}
	}

}
