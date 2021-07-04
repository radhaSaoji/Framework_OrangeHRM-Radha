package com.technocredits.ornghrm.testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.technocredits.ornghrm.base.PreRequisiteSteps;
import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;
import com.technocredits.ornghrm.pages.DashboardPage;
import com.technocredits.ornghrm.pages.LoginPage;
import com.technocredits.ornghrm.pages.RetryLoginPage;
import com.technocredits.ornghrm.utility.ExcelOperationsLoginData;

public class LoginTestCase_CredentialCombos {
	LoginPage loginpage;
	
	@BeforeMethod
	public void openBrowser() {
		System.out.println("VERIFY- Open Browser and load URL");
		PreRequisiteSteps.start();
		loginpage = new LoginPage();
		
		System.out.println("VERIFY- Logo is displayed");
		Assert.assertTrue(loginpage.isLogoDisplayed(), "Logo is not displayed");
		
		System.out.println("VERIFY- Login panel is displayed");
		Assert.assertTrue(loginpage.isLoginPanelDisplayed(),"Login panel is not present");
	}
	
	public void loginValidation() {
		
		DashboardPage homepage = new DashboardPage();
		System.out.println("VERIFY- Dashboard is displayed");
		Assert.assertTrue(homepage.isDashboardDisplayed(),"Dashboard is not displayed");
		
		System.out.println("Step- Logout");
		homepage.clickOnUserProfile();
		homepage.clickOnUserMenu("Logout");
	}
	
	@Test(dataProvider="loginCredData")
	public void loginDataValidation(String username, String password, String message) throws ElementNotEnabledException {
		loginpage = new LoginPage();
		RetryLoginPage retryLogin = new RetryLoginPage();
		
		loginpage.performLogin(username, password);
		
		if(message.equals("Password cannot be empty")) {
			Assert.assertEquals(loginpage.getLoginErrorMsg(), message);
		}
		else if(message.equals("Username cannot be empty")) {
			Assert.assertEquals(loginpage.getLoginErrorMsg(), message);
		}
		else if(message.equals("retryLogin")) {
			retryLogin.performLogin("Admin", "SYpifJ2V2@");
			loginValidation();
		}
		else if(message.equals("pass")) {
			loginValidation();
		}	
	}
	
	@DataProvider(name = "loginCredData")
	public Object[][] getLoginData() throws IOException{
		return ExcelOperationsLoginData.readFormExcel("Login.xlsx", "Data", true);
	}
	
	@AfterMethod
	public void closeBrowser() {
		PreRequisiteSteps.tearDown();
	}
}
