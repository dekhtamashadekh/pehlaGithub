package com.github.selenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Listens for failed tests that need to be rerun.
 */
public class RetryTestListener extends TestListenerAdapter implements ITestListener {

	private List<String> listToSkip = new ArrayList<String>();

	private final int maxCount = 3;

	@Override
	public void onTestFailure(ITestResult tr) {

		listToSkip.add(tr.getMethod().getMethodName() + tr.getTestClass());

		int occurrences = Collections.frequency(listToSkip, tr.getMethod().getMethodName() + tr.getTestClass());

		if (occurrences < maxCount) {

			tr.setStatus(ITestResult.SKIP);

		} else {

			tr.setStatus(ITestResult.FAILURE);

		}
		super.onTestFailure(tr);
	}

//	@Override
//	public void onTestSuccess(ITestResult tr) {
//		System.out.println("I am in onTestSuccess class RetryTestListener");
//		super.onTestSuccess(tr);
//		//count = 1;
//	}

//	@Override
//	public void onFinish(ITestContext context) {
//		System.out.println("I am in onFinish class RetryTestListener");
//		for (int i = 0; i < context.getAllTestMethods().length; i++) {
//			if (context.getAllTestMethods()[i].getCurrentInvocationCount() == 2) {
//				if (context.getFailedTests().getResults(context.getAllTestMethods()[i]).size() == 2 || context.getPassedTests().getResults(context.getAllTestMethods()[i]).size() == 1) {
//					context.getFailedTests().removeResult(context.getAllTestMethods()[i]);
//				}
//			}
//		}
//
//	}

//	@SuppressWarnings("unused")
//	private Set<ITestNGMethod> findDuplicates(Set<ITestResult> listContainingDuplicates) {
//		System.out.println("I am in findDuplicates class RetryTestListener");
//		Set<ITestNGMethod> toRemove = new HashSet<ITestNGMethod>();
//		Set<ITestNGMethod> testSet = new HashSet<ITestNGMethod>();
//
//		for (ITestResult test : listContainingDuplicates) {
//			if (!testSet.add(test.getMethod())) {
//				toRemove.add(test.getMethod());
//			}
//		}
//		return toRemove;
//
//	}

}
