package com.technocredits.ornghrm.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;
import com.technocredits.ornghrm.constants.ConstantPath;

public class PreRequisiteSteps {
	
	static protected WebDriver driver;
	private static WebDriverWait wait;
	
	public static void start(){
		start("https://radhas-trials71.orangehrmlive.com/auth/login");
	}
	
	public static void start(String url) {
		System.setProperty(ConstantPath.CHROMEDRIVER, ConstantPath.CHROMEEXE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		wait = new WebDriverWait(driver, ConstantPath.MAXWAIT);
	}
	
	protected WebElement getElement(String locatorType, String locator, boolean isWaitRequired) {
		WebElement element;
		
		switch(locatorType.toUpperCase()) {
			case "XPATH":
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				else
					element = driver.findElement(By.xpath(locator));
				break;
				
			case "ID":
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
				else
					element = driver.findElement(By.id(locator));
				break;
				
			case "NAME":
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
				else
					element = driver.findElement(By.name(locator));
				break;
				
			case "CLASSNAME":
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
				else
					element = driver.findElement(By.className(locator));
				break;
				
			case "LINKTEXT":
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
				else
					element = driver.findElement(By.linkText(locator));
				break;
				
			default:
				element =null;
				System.out.println("invalid locatorType");
		}
		return element;
	}
	
	protected List<WebElement> getElements(String locatorType, String locator, boolean isWaitRequired) {
		List<WebElement> list;
		
		switch(locatorType.toUpperCase()) {
			case "XPATH":
				if(isWaitRequired)
					list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
				else
					list = driver.findElements(By.xpath(locator));
				break;
				
			case "ID":
				if(isWaitRequired)
					list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locator)));
				else
					list = driver.findElements(By.id(locator));
				break;
				
			case "NAME":
				if(isWaitRequired)
					list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locator)));
				else
					list = driver.findElements(By.name(locator));
				break;
				
			case "CLASSNAME":
				if(isWaitRequired)
					list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locator)));
				else
					list = driver.findElements(By.className(locator));
				break;
				
			default:
				list =null;
				System.out.println("invalid locatorType");
		}
		return list;
	}
	
	protected void scrollToElement(WebElement element) {
		if(!element.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true)", element);
		}
	}
	
	protected boolean isElementDisplayed(WebElement element) {
		if(element.isDisplayed())
			return true;
		else {
			scrollToElement(element);
			return element.isDisplayed();
		}
	}
	 
	protected boolean isElementDisplayed(String locatorType, String locator,boolean isWaitRequired) {
		try {
			WebElement element = getElement(locatorType, locator, isWaitRequired);
			return isElementDisplayed(element);
		}catch(Exception exc) {
			return false;
		}
	}
	
	protected String getElementText(WebElement element) {
		scrollToElement(element);
		return element.getText();
	}
	
	protected String getElementText(String locatorType, String locator, boolean isWaitRequired) {
		return getElement(locatorType, locator, isWaitRequired).getText();
	}
	
	protected void entertext(String locatorType, String locator, boolean isWaitRequired, String text) throws ElementNotEnabledException {
		WebElement element = getElement(locatorType, locator, isWaitRequired);
		if(element.isEnabled())
			element.sendKeys(text);
		else
			throw new ElementNotEnabledException("Element not enabled for Locator- "+locator+" and locator Type- "+locatorType);
	}
	
	protected void clickOnElement(String locatorType, String locator, boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locator, isWaitRequired);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();;
	}
	
	protected String getAttributeValue(String locatorType, String locator, boolean isWaitRequired, String atribute) {
		WebElement element = getElement(locatorType, locator, isWaitRequired);
		return element.getAttribute(atribute);
	}
	
	protected String getAttributeValue(WebElement element, String atribute) {
		return element.getAttribute(atribute);
	}
	
	protected String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}
	
	public static void tearDown() {
		driver.close();
	}
}
