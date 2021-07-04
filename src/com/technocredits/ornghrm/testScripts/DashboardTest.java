package com.technocredits.ornghrm.testScripts;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.technocredits.ornghrm.base.exceptionsClass.ElementNotEnabledException;
import com.technocredits.ornghrm.pages.DashboardPage;
import com.technocredits.ornghrm.utility.ExcelOperationsLoginData;

public class DashboardTest extends BaseTest{
	
	@Test
	public void UserprofileValidation() throws ElementNotEnabledException {
		
		DashboardPage homepage = new DashboardPage();
		System.out.println("VERIFY- Dashboard is displayed");
		Assert.assertTrue(homepage.isDashboardDisplayed(),"Dashboard is not displayed");
		
		System.out.println("VERIFY- Dashboard is selected as default menu after login");
		Assert.assertTrue( homepage.isDashboardSelectedAsDefaultMenu(),"DashBoard is not the default menu");
		
		System.out.println("VERIFY- Widgets are displayed on Dashboard page");
		List<String> actualListOfWidgets = homepage.getWidgetsTitle();
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
		DashboardPage dashboard = new DashboardPage();
		System.out.println("VERIFY- User profile is displayed");
		Assert.assertTrue(dashboard.isUserProfileDisplayed(), "User profile is not present");
		
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
	
	
}
	
