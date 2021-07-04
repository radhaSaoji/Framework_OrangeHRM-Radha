package com.technocredits.ornghrm.pages;

import com.technocredits.ornghrm.base.PreRequisiteSteps;
import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;

public class RetryLoginPage extends PreRequisiteSteps{
	
	public DashboardPage performLogin(String username, String password) throws ElementNotEnabledException {
		enterUserName(username);
		enterPassword(password);
		click();
		return new DashboardPage();
	}
	
	public RetryLoginPage enterUserName(String username) throws ElementNotEnabledException {
		entertext("id", "txtUsername", false, username);
		return this;
	}
	
	public RetryLoginPage enterPassword(String password) throws ElementNotEnabledException {
		entertext("name", "txtPassword", false, password);
		return this;
	}
	
	public DashboardPage click() {
		clickOnElement("id", "btnLogin", false);			
		return new DashboardPage();
	}
	
	public String getURLofPage() {
		return getCurrentPageURL();
	}
}
