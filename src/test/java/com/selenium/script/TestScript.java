package com.selenium.script;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;

import utils.CONSTANTS;

public class TestScript extends DriverFactory {
	
	WebDriver driver;
	Properties propObj;
	FileInputStream objfile = null;
	
	@BeforeClass
	public void beforCls() throws IOException {
		driver = DriverFactory.getDriver();
		propObj = new Properties();
		objfile = new FileInputStream(CONSTANTS.ELEMENT_LOCATOR_PROPERTY_FILE_PATH);
		propObj.load(objfile);
	}
	
	@Test
	public void first() {
		driver.get("https://www.google.com");
		driver.findElement(By.xpath(propObj.getProperty("gmailBtn"))).click();
	}

}
