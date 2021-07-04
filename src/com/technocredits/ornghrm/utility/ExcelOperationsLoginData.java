package com.technocredits.ornghrm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.technocredits.ornghrm.constants.ConstantPath;

public class ExcelOperationsLoginData {
	
	@DataProvider(name="loginCredData")
	public static Object[][] readFormExcel(String filepath, String sheetName, boolean isHeader) throws IOException {
		int count =0;
		if(isHeader)
			count=1;
		
		File file = new File(ConstantPath.TESTDATALOC+filepath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		
		Sheet sheet = wb.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum()+1;
		if(isHeader)
			totalRow = sheet.getLastRowNum();
		System.out.println("Total Rows - "+totalRow);
		
		int totalCol = sheet.getRow(0).getLastCellNum();
		System.out.println("Total columns - "+totalCol);
		
		Object[][] data = new Object[totalRow][totalCol];
		
		for(int rowIndex =0+count; rowIndex<=data.length;rowIndex++) {
			for(int colIndex=0;colIndex<totalCol;colIndex++) {
				try {
					data[rowIndex-count][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
				}catch(NullPointerException npe) {
					data[rowIndex-count][colIndex]="";
				}
				
				System.out.print(data[rowIndex-count][colIndex]+" ");
			}
			System.out.println();
		}
		wb.close();
		return data;
	}
	
	public static List<String> getAllRowsAtColumn(String filepath, String sheetName,int columnIndex, boolean isHeader) throws IOException{		
		List<String> excelDatalist = new ArrayList<String>();
		File file = new File(ConstantPath.TESTDATALOC+filepath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet(sheetName);
		
		int startIndex= isHeader ?1 :0;
		
		int totalRows = sheet.getLastRowNum();
		for(int index=startIndex;index<=totalRows;index++) {
			String data = sheet.getRow(index).getCell(columnIndex).getStringCellValue();
			excelDatalist.add(data);
		}
		wb.close();
		return excelDatalist;
	}
	
	public static List<String> getAllColumnsAtaRow(String filepath, String sheetName,int rowIndex, boolean isHeader) throws IOException{
		List<String> excelDatalist = new ArrayList<String>();
		File file = new File(ConstantPath.TESTDATALOC+filepath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet(sheetName);
		
		int startIndex= isHeader ? 1 :0;
		int totalColumns = sheet.getRow(0).getLastCellNum();
		for(int index=startIndex;index<=totalColumns;index++) {
			String data = sheet.getRow(0).getCell(index).getStringCellValue();
			excelDatalist.add(data);
		}
		wb.close();
		return excelDatalist;
	}
}
