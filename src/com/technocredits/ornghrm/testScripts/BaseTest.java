package com.technocredits.ornghrm.testScripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.technocredits.ornghrm.base.PreRequisiteSteps;
import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;
import com.technocredits.ornghrm.pages.LoginPage;

public class BaseTest{
	
	@BeforeMethod
	public void commonTests() throws ElementNotEnabledException {
		System.out.println("VERIFY- Open Browser and load URL");
		PreRequisiteSteps.start();
		LoginPage loginpage = new LoginPage();
		System.out.println("VERIFY- Logo is displayed");
		Assert.assertTrue(loginpage.isLogoDisplayed(), "Logo is not displayed");
		
		System.out.println("VERIFY- Login panel is displayed");
		Assert.assertTrue(loginpage.isLoginPanelDisplayed(),"Login panel is not present");
		
		System.out.println("STEP- Login using valid credentials");
		loginpage.performLogin("Admin", "SYpifJ2V2@");
	}
	
	@AfterMethod
	public void closeBrowser() {
		PreRequisiteSteps.tearDown();
	}

}
