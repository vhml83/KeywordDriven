package com.titanium.operations;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UIOperation {
	
	WebDriver driver;
	
	public UIOperation(WebDriver driver) {
		this.driver = driver;
	}
	
	public void perform(Properties p, String operation, String objectName, String objectType, String value) throws Exception {
		System.out.println("");
		switch(operation.toLowerCase()) {
		case "click":
				driver.findElement(this.getObject(p, objectName, objectType)).click();
				break;
		case "settext":
				driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
				break;
		case "gotourl":
				driver.get(p.getProperty(value));
				break;
		case "gettext":
				driver.findElement(this.getObject(p, objectName, objectType)).getText();
				break;
		case "selectbytext":
				new Select(driver.findElement(this.getObject(p, objectName, objectType))).selectByVisibleText(value);
				break;
		case "close":
				driver.quit();
				break;
		default:
				break;
		}
	}

	private By getObject(Properties p, String objectName, String objectType) throws Exception {
		switch(objectType.toLowerCase()) {
		case "id":
			return By.id(p.getProperty(objectName));
		case "xpath":
			return By.xpath(p.getProperty(objectName));
		case "name":
			return By.name(p.getProperty(objectName));
		case "css":
			return By.cssSelector(p.getProperty(objectName));
		case "classname":
			return By.className(p.getProperty(objectName));
		case "link":
			return By.linkText(p.getProperty(objectName));
		case "partiallink":
			return By.partialLinkText(p.getProperty(objectName));
		case "tagname":
			return By.tagName(p.getProperty(objectName));
		default:
			throw new Exception("Wrong object type");
		}
	}
}
