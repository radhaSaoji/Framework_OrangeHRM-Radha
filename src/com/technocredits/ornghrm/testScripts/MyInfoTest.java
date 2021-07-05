package com.technocredits.ornghrm.testScripts;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.technocredits.ornghrm.pages.MyInfoPage;
import com.technocredits.ornghrm.pages.MyInfo_SalaryPage;

public class MyInfoTest extends BaseTest{

	@Test
	public void checkPayableSalaryTest() {
		Map<String, Double> expectedSalaryBreakup = new LinkedHashMap<>();
		expectedSalaryBreakup.put("Cost to the Company", 168500.0);
		expectedSalaryBreakup.put("Total Deductions", 4932.0);
		expectedSalaryBreakup.put("Total Payable", 115068.0);
		
		System.out.println("Step- Click on My Info");
		MyInfoPage myinfoPage = goto_MyInfo();
		System.out.println("Step- Click on Salary");
		MyInfo_SalaryPage salaryPage = myinfoPage.clickOnSalary();
		Map<String, Double> actualSalarybreakup = salaryPage.getSalaryCalculation();
		Assert.assertEquals(expectedSalaryBreakup, actualSalarybreakup, "Salary breakup is not correct");
	}
}
