package com.technocredits.ornghrm.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.technocredits.ornghrm.base.PreRequisiteSteps;

public class MyInfo_SalaryPage extends PreRequisiteSteps{
	
	public Map<String, Double> getSalaryCalculation() {
		Map<String, Double> salaryComponents = new LinkedHashMap<String, Double>();
		List<WebElement> list = getElements("xpath", "//div[@class='row']", true);
		int index=1;
		for(WebElement element : list) {
			String key = element.findElement(By.xpath("div[1]")).getText();
			String value = getElementInsideElement("xpath", "div[2]", true, element, "//div[@class='row']["+index+"]").getText().replace(",", "");
			salaryComponents.put(key, Double.parseDouble(value));
			index++;
		}
		return salaryComponents;
	}
	
	/*public double[] verifySalarybreakup() {
		double[] salaryValuesArr = new double[3];
		int count=0;
		Map<String, Double> salaryComponents = getSalaryCalculation();
		Set<String> keySet = salaryComponents.keySet();
		System.out.println(keySet);
		for(String salaryComponent: keySet) {
			salaryValuesArr[count] = salaryComponents.get(salaryComponent);
			count ++;
		}
		return salaryValuesArr;
	}*/
}
