package com.github.selenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * @author 
 *         This is class used to try a test more than once before it's sent as a
 *         failed test in test reports
 * 
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private List<String> listToSkip = new ArrayList<String>();

	private final int maxCount = 2;

	/*
	 * Set the retry to true or flase based on ITestResut and number of tries
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 */
	public boolean retry(ITestResult result) {

		int occurrences = Collections.frequency(listToSkip, result.getMethod().getMethodName() + result.getTestClass());

		listToSkip.add(result.getMethod().getMethodName() + result.getTestClass());

		result.setAttribute("retry", "Xamla");

		if (!result.isSuccess()) {

			if (occurrences < maxCount) {

				String message = Thread.currentThread().getName() + ": Error in " + result.getName() + " Retrying ";

				Reporter.log(message);

				return true;

			} else {
				return false;

			}
		}

		return false;
	}

}