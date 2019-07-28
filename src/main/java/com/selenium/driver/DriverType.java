package com.selenium.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.CONSTANTS;

public enum DriverType implements DriverSetup {
	FIREFOX {
		public RemoteWebDriver getWebDriverObject() {
			System.setProperty("webdriver.gecko.driver", CONSTANTS.DRIVER_PATH);
			return new FirefoxDriver();
		}
    },
    CHROME {
        public RemoteWebDriver getWebDriverObject() {
        	System.setProperty("webdriver.chrome.driver", CONSTANTS.DRIVER_PATH);
            return new ChromeDriver();
        }
    },
    IE {
        public RemoteWebDriver getWebDriverObject() {
        	System.setProperty("webdriver.ie.driver", CONSTANTS.DRIVER_PATH);
            return new InternetExplorerDriver();
        }
    },
}
