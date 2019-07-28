package utils;
/**
 * 
 * This class is created for all the constants in the app 
 * @author JayantKandpal
 *
 */
public interface CONSTANTS {
	
	public static String USER_DIR = System.getProperty("user.dir");
	public static String CONFIG_PATH = USER_DIR+"/src/test/resources/config.properties";
	public static String DRIVER_PATH = USER_DIR+"/src/test/resources/drivers/"+System.getProperty("browserDriver");
	public static String SCREENSHOT_PATH = USER_DIR+"/output/screenshots";
	public static String ELEMENT_LOCATOR_PROPERTY_FILE_PATH = USER_DIR+"/src/test/resources/elementLocators.properties";
	public static String LOCALIZATION_XML = System.getProperty("localization") + "-locators.xml";
	public static String LOCALIZATION_XML_FILE_PATH = USER_DIR + "/src/test/resources/localizationXML/"+ LOCALIZATION_XML;
}