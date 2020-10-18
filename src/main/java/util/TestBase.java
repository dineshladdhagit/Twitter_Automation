package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public TestUtil utils;
	public static Logger log = Logger.getLogger(TestBase.class.getName());

	//Constructor
	public TestBase() {
		utils = new TestUtil();
	}

	//Initialization Driver Method
	public static void initializeDriver() {
		String browserName = prop.getProperty("BROWSER");

		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
			//System.setProperty("webdriver.chrome.driver", "/Users/dineshladdha/Downloads/chromedriver");
			//driver = new ChromeDriver();
		}
		else if(browserName.equals("ff")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		
			//System.setProperty("webdriver.gecko.driver", "/Users/dineshladdha/Downloads/geckodriver");
			//driver = new ChromeDriver();
		}
		log.info("Initialized browserName "+browserName+" instance");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICT_WAIT, TimeUnit.SECONDS);
		
		// Launch the URL
		String url = prop.getProperty("URL");
		driver.get(url);
		log.info("Launched "+url+" url");
	}

	
	public static void loadConfiguration(){
		//Log4J configuration
		PropertyConfigurator.configure("src//main//resources//config//log4j.properties");
		//Read Properties File
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src/main/resources/config/config.properties");
			prop.load(fis);
			log.info("Successfully Loaded confirguration properties");
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}



}
