package com.selenium.driver;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;

import com.selenium.utils.LocatorPropFile;

import utils.CONSTANTS;

import static com.selenium.driver.DriverType.CHROME;
import static com.selenium.driver.DriverType.FIREFOX;
import static com.selenium.driver.DriverType.IE;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

public class DriverFactory {

	private static WebDriver driver;
	private static DriverType driverType;
	private LocatorPropFile locatorPropFile = new LocatorPropFile();

	protected final static Logger logger = LogManager.getLogger(DriverFactory.class);
	
	private static DateFormat dateFormat;

	public static void captureScreenShot(ITestResult result) throws IOException {
		String destDir = CONSTANTS.SCREENSHOT_PATH;
		dateFormat = new SimpleDateFormat("yyyy_MMM_dd_HH_mm");
		String passfailMethod = result.getMethod().getRealClass().getSimpleName();

		// To capture screenshot. ScreenshotOnPassFail
		File scrFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

		// Set file name with combination of test class name + date time.
		String destFile = passfailMethod + " - " + dateFormat.format(new Date()) + ".png";

		// Store file at destination folder location
		FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				captureScreenShot(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void initiatlizeDriver() {
		String browser = System.getProperty("browser");
		browser.toLowerCase();
		switch (browser) {
		case "chrome":
			driverType = CHROME;
			break;

		case "firefox":
			driverType = FIREFOX;
			break;

		case "ie":
			driverType = IE;
			break;

		default:
			logger.info("DriverType not set.");
		}
		try {
			driver = driverType.getWebDriverObject();
			logger.info("Broswer in use: " + browser.toUpperCase());
			try {
				locatorPropFile.createElementLocatorPropertyFile();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NullPointerException e) {
			logger.info(
					"EXITING EXECUTION : Check the value of browser tag in pom file - \n\tIt should be one of the following: chrome, firefox, IE\n\n");
			System.exit(1);
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
