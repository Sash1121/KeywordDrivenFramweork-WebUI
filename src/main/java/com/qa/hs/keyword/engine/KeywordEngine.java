package com.qa.hs.keyword.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	
	public Base base;
	public WebElement element;
	
	public static Workbook workbook;
	public static Sheet sheetname;
	
	public final String Scenario_File_Path = "C:\\Users\\Shashank\\AutomationProjects\\KeywordDrivenHubSpot\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\HubSpot.xlsx";
	
	public void startExecution(String sheetName) throws Exception {
		
		String locatorName = null;
		String locatorValue = null;
		FileInputStream fis = null;
		//Read Data
		try {
			fis = new FileInputStream(Scenario_File_Path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheetname = workbook.getSheet(sheetName);
		int k = 0;
		//to capture locator, value and action from excel
		for(int i=0; i<(sheetname.getLastRowNum()); i++) {
			try {
			String locatorColValue = sheetname.getRow(i+1).getCell(k+1).toString().trim();//get locator column value
			if(!locatorColValue.equalsIgnoreCase("NA")) {
				locatorName = locatorColValue.split("=")[0].trim();//name of locator
				locatorValue = locatorColValue.split("=")[1].trim();//value of locator
			
			}
			
			String action = sheetname.getRow(i+1).getCell(k+2).toString().trim();//get action column value
			String value = sheetname.getRow(i+1).getCell(k+3).toString().trim();//get value column value
			
			//Switch Case for browser actions
			switch (action) {
			case "open browser":
				base = new Base();
				prop = base.init_properties();
				if(value.isEmpty() || value.equalsIgnoreCase("NA")) {
					driver = base.init_driver(prop.getProperty("browser"));
				}else {
					driver = base.init_driver(value);
				}
				break;
				
			case "enter url":
				if(value.isEmpty() || value.equalsIgnoreCase("NA")) {
					driver.get(prop.getProperty("url"));;
				}else {
					driver.get(value);	
				}
				break;
				
			case "quit":
				driver.close();
				break;

			default:
				break;
			}
			
			//Switch Case to locate elements and perform actions
			switch (locatorName) {
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click")) {
					element.click();
				}
				locatorName = null;
				break;
				
			case "name":
				element = driver.findElement(By.name(locatorValue));
				if(action.equalsIgnoreCase("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				}else if(action.equalsIgnoreCase("click")) {
					element.click();
				}
				locatorName = null;
				break;
				
			case "linktext":
				element = driver.findElement(By.linkText(locatorValue));
				element.click();
				locatorName = null;
				break;

			default:
				break;
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
		
	}

}
