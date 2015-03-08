package com.github.selenium.Tests;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TrialTest {
	static int invalidimg;

	public static void countBrokenImage(WebDriver driver, String website) {
		try {

			driver.get(website);
			invalidimg = 0;
			List<WebElement> allImages = driver.findElements(By.tagName("img"));
			System.out.println("Total images are " + allImages.size());
			for (int i = 0; i < allImages.size(); i++) {
				WebElement img = (WebElement) allImages.get(i);
				if (img != null) {
					verifyimgActive(img);
				}
			}

			System.out.println("Total invalid images are " + invalidimg);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void verifyimgActive(WebElement img) {
		try {
			HttpResponse response = HttpClients.createDefault().execute(
					new HttpGet(img.getAttribute("src")));
			if (response.getStatusLine().getStatusCode() != 200)
				invalidimg++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}