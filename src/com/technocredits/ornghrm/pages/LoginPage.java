package com.technocredits.ornghrm.pages;

import com.technocredits.ornghrm.base.PreRequisiteSteps;
import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;

public class LoginPage extends PreRequisiteSteps {
	
	public boolean isLogoDisplayed() {
		return isElementDisplayed("id", "divLogo", false);
		
	}

	public boolean isLoginPanelDisplayed() {
		return isElementDisplayed("id", "logInPanelHeading", true);
	}
	
	public DashboardPage performLogin(String username, String password) throws ElementNotEnabledException {
		enterUserName(username);
		enterPassword(password);
		click();
		return new DashboardPage();
	}
	
	public LoginPage enterUserName(String username) throws ElementNotEnabledException {
		entertext("id", "txtUsername", false, username);
		return this;
	}
	
	public LoginPage enterPassword(String password) throws ElementNotEnabledException {
		entertext("name", "txtPassword", false, password);
		return this;
	}
	
	public DashboardPage click() {
		clickOnElement("className", "button", false);			
		return new DashboardPage();
	}
	
	public String getLoginErrorMsg() {
		return getElementText("xpath", "//span[@id='spanMessage']", true);
	}
}
