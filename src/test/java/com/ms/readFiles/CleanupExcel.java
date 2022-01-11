package com.ms.readFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ms.dfm.modules.CleanFolders;
import com.ms.dfm.utils.listeners.GenerateReport;
import com.ms.dfm.utils.listeners.Log4J;

public class CleanupExcel extends GenerateReport{

	public XSSFWorkbook workbook;

	@BeforeSuite(alwaysRun=true)
	public void Cleanup() throws IOException {
		
		Log4J.log.info("Logging Started");
		
		DOMConfigurator.configure("log4j.xml");

		Reporter.log("Excel Sheet cleanup started");
		
		// Cleaning up the Allure Reports folder before running the test
		CleanFolders.cleanFolders("C:\\DFM\\DFM\\allure-results");
		
		File copiedFile = new File(readPropertiesFile.readsetupdata("copiedExcel"));

		File file = new File(readPropertiesFile.readsetupdata("mainExcel"));

		FileInputStream fis = new FileInputStream(file);
		
		// Copy the mail file before modifying the contents
		if(copiedFile.exists() && !copiedFile.isDirectory()) {
			
			copiedFile.delete();
			
			FileUtils.copyFile(file, copiedFile);
		}
		
		else {
			
			FileUtils.copyFile(file, copiedFile);
		}

		workbook = new XSSFWorkbook(fis);

		XSSFSheet cleanupData = workbook.getSheet("Cleanup_Data");

		int cleanuprow = cleanupData.getPhysicalNumberOfRows();

		for (int i = 1; i < cleanuprow; i++) {

			String sheetName = cleanupData.getRow(i).getCell(1).getStringCellValue();
			
			String cleanFlag = cleanupData.getRow(i).getCell(2).getStringCellValue();

			if (sheetName == "" || cleanFlag.equalsIgnoreCase("No")) {

				break;
			}

			else {

				XSSFSheet sheet = workbook.getSheet(sheetName);

				int rowCount = sheet.getPhysicalNumberOfRows();

				int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

				for (int j = 1; j < rowCount; j++) {

					for (int k = 0; k < columnCount; k++) {

						sheet.getRow(j).getCell(k).setCellValue("");
					}

					FileOutputStream fos = new FileOutputStream(file);

					workbook.write(fos);

				}

			}

		}

		workbook.close();

		Reporter.log("Excel Cleanup is done");
	}
	
	@AfterSuite(alwaysRun=true)
	public void Finish() throws IOException {
		
		Log4J.log.info("Logging Finished");
		
	}
	
}
