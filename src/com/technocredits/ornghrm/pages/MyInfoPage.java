package com.technocredits.ornghrm.pages;

import com.technocredits.ornghrm.base.PreRequisiteSteps;

public class MyInfoPage extends PreRequisiteSteps{
	
	public void clickOnMyInfo() {
		clickOnElement("xpath", "//a[@id='menu_pim_viewMyDetails']", false);
	}
	
	public MyInfo_SalaryPage clickOnSalary() {
		clickOnElement("xpath", "//a[text()='Salary']", true);
		return new MyInfo_SalaryPage();
	}

}
