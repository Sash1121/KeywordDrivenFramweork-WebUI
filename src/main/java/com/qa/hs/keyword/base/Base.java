package com.qa.hs.keyword.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public WebDriver init_driver(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
		    driver = new ChromeDriver(co);
		    driver.manage().window().maximize();
		}
		return driver;
	}
	
	public Properties init_properties() throws Exception {
		prop = new Properties();
		try {
		File f = new File("C:\\Users\\Shashank\\AutomationProjects\\KeywordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
		FileInputStream fis = new FileInputStream(f);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	

}
