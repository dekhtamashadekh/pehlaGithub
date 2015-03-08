package com.github.selenium.Utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;

import com.github.selenium.TestSeleniumBase;

/**
 * @author dtyagi
 * 
 *         This is a class to load JQuery in webpage
 * 
 */
public class UploadJqueryToWebPage {



	/**
	 * @throws IOException
	 *             This methods loads JQuery in a page
	 */
	public static void loadScript() throws IOException {



		final String PART1 = "var script = document.createElement('SCRIPT'); ";

		final String PART2 = " script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js'; ";

		final String PART3 = "script.type = 'text/javascript'; ";

		final String isLoaded = "if (!window.jQuery) {document.getElementsByTagName('head')[0].appendChild(script);} " + "else {}";

		TestSeleniumBase.getDriverObject().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) TestSeleniumBase.getDriverObject();

		js.executeScript(PART1 + PART2 + PART3 + isLoaded);

	}

	/**
	 * This is the method which reads JQuery from a file and converts it into a
	 * String
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}

	}

}
