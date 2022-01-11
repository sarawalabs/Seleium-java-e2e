package com.ms.readFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readDFMExcel {
	
	public XSSFSheet sheetData;
	
	public String Mail_Template;
	
	public String caseNumber;
	
	public String CaseStatus;
	
	public String InternalTitle;

	public int cell_count;
	
	public XSSFSheet caseDetails;
	
	public XSSFSheet Mail_Templates;
	
	public XSSFSheet mailTemplates;
	
	public XSSFSheet resolve_case;

	public String SR;

	XSSFWorkbook wb;


	public readDFMExcel() {

		try {
			

			File src = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

			FileInputStream fis = new FileInputStream(src);

			wb = new XSSFWorkbook(fis);

			caseDetails = wb.getSheet("Case_Details");
			
			mailTemplates = wb.getSheet("Mail_Templates");

			cell_count = caseDetails.getPhysicalNumberOfRows();

			for (int i = 1; i<cell_count; i++) {
				
				// Check details from the "Case_Details" sheet
				caseNumber = caseDetails.getRow(i).getCell(0).getStringCellValue();

				CaseStatus = caseDetails.getRow(i).getCell(2).getStringCellValue();
				
				InternalTitle = caseDetails.getRow(i).getCell(4).getStringCellValue();

				if (CaseStatus.equalsIgnoreCase("Initial contact pending") 
						&& InternalTitle.equalsIgnoreCase("Quota Request")) {
					
					// Pick the mail template as "Quota_First_Contact_Mail" from "Mail_Templates" sheet
					Mail_Template = mailTemplates.getRow(5).getCell(1).getStringCellValue();

				}

				else if (CaseStatus.equalsIgnoreCase("Initial contact pending") 
						&& InternalTitle !=  "Quota Request" ) {

					// Pick the mail template as "First_Contact_Mail" from "Mail_Templates" sheet
					Mail_Template = mailTemplates.getRow(1).getCell(1).getStringCellValue();

				}
				
				else if (CaseStatus.equalsIgnoreCase("Waiting for customer confirmation")
						&& CaseStatus.equalsIgnoreCase("customer confirmed on case closure")) {
					
					// Pick the mail template as "Case_Closure_Mail" from "Mail_Templates" sheet
					Mail_Template = mailTemplates.getRow(2).getCell(1).getStringCellValue();
				}

			}

		}

		catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String caseNumber() {

		return caseNumber;

	}
	
	public String CaseStatus() {

		return CaseStatus;

	}
	
	public String InternalTitle() {

		return InternalTitle;

	}
	
	public String Mail_Template() {

		return Mail_Template;

	}

	// This returns the SR number for the search and pull latest case notes
	public XSSFSheet getSheetData(String SheetName) throws IOException {

		File src = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(src);

		wb = new XSSFWorkbook(fis);

		sheetData = wb.getSheet(SheetName);

		return sheetData;

	}

}