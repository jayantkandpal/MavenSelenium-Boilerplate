package com.selenium.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utils.CONSTANTS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocatorPropFile {
	
	/**
	 * @purpose: this class is used to load the latest data a property file from excel sheet.
	 */
	
	private final static Logger logger = LogManager.getLogger(LocatorPropFile.class);
	
	public void createElementLocatorPropertyFile() throws ParserConfigurationException, SAXException, IOException {

		Properties obj;
		OutputStream objfile = null;

		obj = new Properties();
		objfile = new FileOutputStream(CONSTANTS.ELEMENT_LOCATOR_PROPERTY_FILE_PATH);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(CONSTANTS.LOCALIZATION_XML_FILE_PATH));
		NodeList list = document.getElementsByTagName("*");
		for (int i = 0; i < list.getLength(); i++) {
			String nodeName = list.item(i).getNodeName();
			String nodeValue = list.item(i).getTextContent();
			
			if (i != 0)
				obj.setProperty(nodeName, nodeValue);
		}
		String s = obj.getProperty("local");
		if(s.equalsIgnoreCase(System.getProperty("localization").toLowerCase()))
			logger.info("Element locator property file generated for : " + obj.getProperty("local") + " localization.");
		else
			logger.info("Property Not found");
		obj.store(objfile, null);
	}

}
