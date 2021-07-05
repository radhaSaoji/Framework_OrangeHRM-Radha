package com.technocredits.ornghrm.testScripts;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.technocredits.ornghrm.base.PreRequisiteSteps;
import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;
import com.technocredits.ornghrm.pages.DashboardPage;
import com.technocredits.ornghrm.pages.LoginPage;
import com.technocredits.ornghrm.utility.ExcelOperationsLoginData;

public class DashboardTest extends BaseTest{
	
	@Test
	public void UserprofileValidation() throws ElementNotEnabledException {
		
		DashboardPage dashboard = goto_dashboard();
		System.out.println("VERIFY- Dashboard is displayed");
		Assert.assertTrue(dashboard.isDashboardDisplayed(),"Dashboard is not displayed");
		
		System.out.println("VERIFY- Widgets are displayed on Dashboard page");
		List<String> actualListOfWidgets = dashboard.getWidgetsTitle();
		Assert.assertEquals(actualListOfWidgets.size(), 11,"Some of the menu are missing");
		try {
			List<String> expectedListOfWidgets= ExcelOperationsLoginData.getAllRowsAtColumn("WidgetTitles.xlsx", "Widget", 0, true);
			Collections.sort(expectedListOfWidgets);
			Collections.sort(actualListOfWidgets);
			Assert.assertEquals(expectedListOfWidgets, actualListOfWidgets,"Widget text not matching");
		}catch(IOException e) {
			System.out.println("ERROR- File not loaded");
			Assert.fail();
		}
		System.out.println("Step- Click on User profile arrow");
		dashboard.clickOnUserProfile();
		
		System.out.println("VERIFY- user menu is displayed");
		Assert.assertTrue(dashboard.isUserMenuDisplayed());
		
		System.out.println("Step- Click on About");
		dashboard.clickOnUserMenu("About");
		
		System.out.println("VERIFY- Employee is more than 0");
		Assert.assertTrue(dashboard.isEmployeeCountMoreThan0());	
		
		System.out.println("VERIFY- All collapsible Sections are clickable");
		Assert.assertEquals(dashboard.areAllElementsCollapsible().size(), 14, "Some of the sections are not clickable");;
	}
	
	@AfterMethod
	public void closeBrowser() {
		PreRequisiteSteps.tearDown();
	}
}
	
