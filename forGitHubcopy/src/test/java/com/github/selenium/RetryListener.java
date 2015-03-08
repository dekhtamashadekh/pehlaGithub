package com.github.selenium;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * @author dtyagi
 *         This class serves as a TestNG listener to apply retryanalyzer
 * 
 */
public class RetryListener implements IAnnotationTransformer {

	/*
	 * Transforms the retryanalyzer annotation to the result of RetryAnalyzer
	 * class
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IAnnotationTransformer#transform(org.testng.annotations.
	 * ITestAnnotation, java.lang.Class, java.lang.reflect.Constructor,
	 * java.lang.reflect.Method)
	 */
	synchronized public void transform(
			ITestAnnotation annotation,
			@SuppressWarnings("rawtypes") Class clazz,
			@SuppressWarnings("rawtypes") Constructor constructor,
			Method method) {

		IRetryAnalyzer retry = annotation.getRetryAnalyzer();

		if (retry == null) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);

		}
	}

}
