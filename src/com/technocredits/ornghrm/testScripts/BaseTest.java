package com.technocredits.ornghrm.testScripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.technocredits.ornghrm.base.PreRequisiteSteps;
import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;
import com.technocredits.ornghrm.pages.DashboardPage;
import com.technocredits.ornghrm.pages.LoginPage;
import com.technocredits.ornghrm.pages.MyInfoPage;
import com.technocredits.ornghrm.pages.MyInfo_personalInfoPage;

public class BaseTest {
	
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
		
		DashboardPage dashboard = goto_dashboard();
		System.out.println("VERIFY- Dashboard is selected as default menu after login");
		Assert.assertTrue(dashboard.isDashboardSelectedAsDefaultMenu(),"DashBoard is not the default menu");
		
		System.out.println("VERIFY- User profile is displayed");
		Assert.assertTrue(dashboard.isUserProfileDisplayed(), "User profile is not present");	
	}
	
	MyInfoPage goto_MyInfo() {
		 MyInfoPage myinfo = new MyInfoPage();
		 myinfo.clickOnMyInfo();
		 return myinfo;
	}
	
	MyInfo_personalInfoPage goto_PIPage() {
		MyInfo_personalInfoPage piPage = new MyInfo_personalInfoPage();
		return piPage;
	}
	
	DashboardPage goto_dashboard() {
		DashboardPage dashboard = new DashboardPage();
		return dashboard;
	}
	
	@AfterMethod
	public void tearDown() {
		PreRequisiteSteps.tearDown();
	}

}
