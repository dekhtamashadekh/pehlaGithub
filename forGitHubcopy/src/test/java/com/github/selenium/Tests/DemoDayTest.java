package com.github.selenium.Tests;

import static java.lang.System.getProperty;

import org.testng.annotations.Test;

import com.github.selenium.TestSeleniumBase;

public class DemoDayTest extends TestSeleniumBase {

	@Test
	public void imgTest() {

		TrialTest.countBrokenImage(getDriverObject(), "http://www.imdb.com");
	}

	@Test(timeOut = 5000)
	public void login() throws Exception {

		getDriverObject().get(getProperty("website"));

	}

	@Test(timeOut = 5000, threadPoolSize = 3, invocationCount = 10)
	public void login1() throws Exception {

		getDriverObject().get(getProperty("website"));

	}

	@Test(timeOut = 1000, threadPoolSize = 3, invocationCount = 10, successPercentage = 99)
	public void login3() throws Exception {

		System.out.println("Read from db");

	}

	@Test(timeOut = 2000)
	public void timeOutTestMethodOne() throws InterruptedException {

		Thread.sleep(5000);

		System.out.println("TimeOut Test Method One!!");

	}

	@Test(timeOut = 5000)
	public void timeOutTestMethodTwo() throws InterruptedException {

		Thread.sleep(3000);

		System.out.println("TimeOut Test Method Two!!");

	}

	@Test(timeOut = 2000)
	public void timeOutTestMethodRunForEver() {

		while (true) {

			// Do nothing

		}

	}
}