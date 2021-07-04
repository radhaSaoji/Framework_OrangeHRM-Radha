package com.technocredits.ornghrm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.technocredits.ornghrm.base.PreRequisiteSteps;

public class DashboardPage extends PreRequisiteSteps{
	
	public boolean isDashboardDisplayed() {
		return isElementDisplayed("xpath", "//li[@class='page-title']", true);
	}
	
	public boolean isDashboardSelectedAsDefaultMenu() {
		System.out.println(getCurrentPageURL());
		return getCurrentPageURL().endsWith("dashboard");
	}
	
	public List<String> getWidgetsTitle() {
		List<WebElement> list = getElements("xpath", "//div[@class='widget-header']/span[2]", true);
		List<String> widgetList = new ArrayList<String>();
		for(WebElement e:list) {
			String elementName = getElementText(e);
				widgetList.add(elementName);
		}
		System.out.println(widgetList);
		return widgetList;
	}
	
	public boolean isUserProfileDisplayed() {
		return isElementDisplayed("xpath", "//div[@id='menu-profile']", true);
	}
	
	public void clickOnUserProfile() {
		clickOnElement("xpath", "//span[@id='account-job']/i", true);
	}
	
	public boolean isUserMenuDisplayed(){
		return isElementDisplayed("xpath", "//ul[@id='user_menu']", false);
	}
	
	public void clickOnUserMenu(String menu) {
		clickOnElement("linktext", menu, true);
	}
	
	public boolean isEmployeeCountMoreThan0() {
		String empcount = getElementText("xpath", "//p[starts-with(text(),'Employees:')]", true);
		String [] ch = empcount.split(" ");
		if(Integer.valueOf(ch[1])>0) {
			clickOnElement("xpath", "//a[@id='heartbeatSubmitBtn']",false);
			return true;
		}
		else {
			clickOnElement("xpath", "//a[@id='heartbeatCancelBtn']", false);
			return false;
		}
	}
	
	public List<String> areAllElementsCollapsible() {
		List<WebElement> list = getElements("xpath", "//ul[@class='collapsible collapsible-accordion']/li[contains(@id,'menu_')][contains(@class,'level1 ')]/div", false);
		List<String> collapsibleElementList = new ArrayList<>();
		for(WebElement element:list) {
			if(getAttributeValue(element, "class").equals("collapsible-body"))
				collapsibleElementList.add(getElementText(element));
		}
		return collapsibleElementList;
	}
}
