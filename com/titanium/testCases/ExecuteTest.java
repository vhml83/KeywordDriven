package com.titanium.testCases;


import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.titanium.excelExportAndFileIO.ReadExcelFile;
import com.titanium.operations.ReadObject;
import com.titanium.operations.UIOperation;

public class ExecuteTest {
	String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	
	@Test
	public void testLogin() throws Exception {
		System.setProperty("webdriver.chrome.driver", chromePath);			
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		ReadExcelFile file = new ReadExcelFile();
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperation operation = new UIOperation(driver);
		
		Sheet sheet = file.readExcel(System.getProperty("user.dir") + "\\resources\\", "TestCase.xlsx", "KeywordFramework");
		
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum(); 
		for (int i = 1; i < rowCount; i++){
			Row row = sheet.getRow(i);
			if(row.getCell(0).toString().length() == 0){
				System.out.println(row.getCell(1).toString()+"--"+row.getCell(2).toString()+"--"+
									row.getCell(3).toString()+"--"+row.getCell(4).toString());
				operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(4).toString());
			} else {
				System.out.println("New Testcase->" + row.getCell(0).toString() + " Started!");
			}
		}
	}
}