package com.ms.readFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.ms.dfm.modules.GetWebElementTextValue;
import com.ms.dfm.modules.setupAndTearDown;

public class writeDFMExcel extends setupAndTearDown {

	public static String Communication_Type_Required;

	public String Mail_Template;

	public static int count;

	static XSSFWorkbook wb;

	static XSSFSheet mailTemplatesSheet;

	static XSSFSheet requiredSheet;

	public static String latestCNotes;

	public static String latestData;

	public static int writeableDataCount;

	public static int i;

	public static int filledRows;

	public static boolean isPresent;

	// ---- Write the Excel with the First page of the Open Cases view ------ //

	public static void openCasesFirstPage(List<WebElement> openCases1, String SheetName) throws IOException {

		File file = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);

		mailTemplatesSheet = wb.getSheet(SheetName);

		count = openCases1.size();

		ArrayList<String> caseNumbers = new ArrayList<String>();

		ArrayList<String> Severity = new ArrayList<String>();

		ArrayList<String> caseStatuses = new ArrayList<String>();

		ArrayList<String> InternalTitles = new ArrayList<String>();

		ArrayList<String> customerNames = new ArrayList<String>();

		String caseSeverity;

		String SR;

		String caseStatus;

		String InternalTitle;

		String customerName;

		for (WebElement SR1 : openCases1) {

//			System.out.println(SR1.getText() + "/n");
//			
//			System.out.println("----------------------------------------------------------------------------------/n");

			SR = GetWebElementTextValue.extractSR(SR1.getText());

//			System.out.println("The case number is: " +SR);

//			System.out.println("----------------------------------------------------------------------------------/n");

			caseNumbers.add(SR);

			caseSeverity = GetWebElementTextValue.getSeverity(SR1.getText());

			Severity.add(caseSeverity);

			caseStatus = GetWebElementTextValue.getCaseStatus(SR1.getText());

			caseStatuses.add(caseStatus);

			customerName = GetWebElementTextValue.getCustomerName(SR1.getText());

			customerNames.add(customerName);

			String pre = "(//span[contains(text(), '";
			String mid = SR;
			String post_ITitle = "')])//ancestor::div[4]//div[contains(@id, 'internaltitle')]//span[contains(@class, ' ')]";

			if (isPresent = driver.findElements(By.xpath(pre + mid + post_ITitle)).size() > 0) {

				InternalTitle = SR1.findElement(By.xpath(pre + mid + post_ITitle)).getText();

				// System.out.println(InternalTitle);

				InternalTitles.add(InternalTitle);
			}

			else if (isPresent = driver.findElements(By.xpath(pre + mid + post_ITitle)).size() == 0) {

//				String post_Title = "')])//ancestor::div[4]//div[contains(@id, 'title')]//span[contains(@class, ' ')]";
				String post_Title = "')])//ancestor::div[4]//div[contains(@id, 'title')]//a";
				InternalTitle = SR1.findElement(By.xpath(pre + mid + post_Title)).getText();

				// System.out.println(InternalTitle);

				InternalTitles.add(InternalTitle);
			}

		}

		for (i = 1; i <= count; i++) {

			String openSR = caseNumbers.get(i - 1);

			String openSeverity = Severity.get(i - 1);

			String openCaseStatus = caseStatuses.get(i - 1);

			String openInternalTitle = InternalTitles.get(i - 1);

			String cxName = customerNames.get(i - 1);

			if (openSR.length() < 12) {

				mailTemplatesSheet.getRow(i).getCell(0).setCellValue(openSR);

				mailTemplatesSheet.getRow(i).getCell(1).setCellValue(cxName);

				mailTemplatesSheet.getRow(i).getCell(2).setCellValue(openSeverity);

				mailTemplatesSheet.getRow(i).getCell(3).setCellValue("Collab Task");

				mailTemplatesSheet.getRow(i).getCell(4).setCellValue(openInternalTitle);
			}

			else {

				mailTemplatesSheet.getRow(i).getCell(0).setCellValue(openSR);

				mailTemplatesSheet.getRow(i).getCell(1).setCellValue(cxName);

				mailTemplatesSheet.getRow(i).getCell(2).setCellValue(openSeverity);

				mailTemplatesSheet.getRow(i).getCell(3).setCellValue(openCaseStatus);

				mailTemplatesSheet.getRow(i).getCell(4).setCellValue(openInternalTitle);

			}

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

		}

		wb.close();

	}

	// ---- Write the Excel with the next page of the Open Cases view ------- //

	public static void openCasesNextPage(List<WebElement> openCases2, String SheetName) throws IOException {

		File file = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);

		mailTemplatesSheet = wb.getSheet(SheetName);

		ArrayList<String> caseNumbersNew = new ArrayList<String>();

		ArrayList<String> SeverityNew = new ArrayList<String>();

		ArrayList<String> caseStatusesNew = new ArrayList<String>();

		ArrayList<String> internalTitlesNew = new ArrayList<String>();

		ArrayList<String> customerNamesNew = new ArrayList<String>();

		String SR2;

		String caseSeverityNew;

		String caseStatusNew;

		String internalTitleNew;

		String customerNameNew;

		for (WebElement SRNew : openCases2) {

			SR2 = GetWebElementTextValue.extractSR(SRNew.getText());

			caseNumbersNew.add(SR2);

			// System.out.println("The case number is: " +SR2);

			caseSeverityNew = GetWebElementTextValue.getSeverity(SRNew.getText());

			SeverityNew.add(caseSeverityNew);

			caseStatusNew = GetWebElementTextValue.getCaseStatus(SRNew.getText());

			caseStatusesNew.add(caseStatusNew);

			customerNameNew = GetWebElementTextValue.getCustomerName(SRNew.getText());

			customerNamesNew.add(customerNameNew);

			String pre = "(//span[contains(text(), '";
			String mid = SR2;
			String post_ITitle = "')])//ancestor::div[4]//div[contains(@id, 'internaltitle')]//span[contains(@class, ' ')]";

			if (isPresent = driver.findElements(By.xpath(pre + mid + post_ITitle)).size() > 0) {

				internalTitleNew = SRNew.findElement(By.xpath(pre + mid + post_ITitle)).getText();

				// System.out.println(internalTitleNew);

				internalTitlesNew.add(internalTitleNew);
			}

			else if (isPresent = driver.findElements(By.xpath(pre + mid + post_ITitle)).size() == 0) {

//				String post_Title = "')])//ancestor::div[4]//div[contains(@id, 'title')]//span[contains(@class, ' ')]";
				String post_Title = "')])//ancestor::div[4]//div[contains(@id, 'title')]//a";
				internalTitleNew = SRNew.findElement(By.xpath(pre + mid + post_Title)).getText();

				// System.out.println(internalTitleNew);

				internalTitlesNew.add(internalTitleNew);
			}

		}

		for (int i = count; i < count + openCases2.size(); i++) {

			// This is commented for further code aspect.
			// mailTemplatesSheet.getRow(i+1).getCell(3).setCellValue(Communication_Type_Required);

			String openSRNew = caseNumbersNew.get(i - count);

			String openSeverityNew = SeverityNew.get(i - count);

			String openCaseStatusNew = caseStatusesNew.get(i - count);

			String intrnalTitleNew = internalTitlesNew.get(i - count);

			String cxNameNew = customerNamesNew.get(i - count);

			if (openSRNew.length() < 12) {

				mailTemplatesSheet.getRow(i + 1).getCell(0).setCellValue(openSRNew);

				mailTemplatesSheet.getRow(i + 1).getCell(1).setCellValue(cxNameNew);

				mailTemplatesSheet.getRow(i + 1).getCell(2).setCellValue(openSeverityNew);

				mailTemplatesSheet.getRow(i + 1).getCell(3).setCellValue("Collab Task");

				mailTemplatesSheet.getRow(i + 1).getCell(4).setCellValue(intrnalTitleNew);

			}

			else {

				mailTemplatesSheet.getRow(i + 1).getCell(0).setCellValue(openSRNew);

				mailTemplatesSheet.getRow(i + 1).getCell(1).setCellValue(cxNameNew);

				mailTemplatesSheet.getRow(i + 1).getCell(2).setCellValue(openSeverityNew);

				mailTemplatesSheet.getRow(i + 1).getCell(3).setCellValue(openCaseStatusNew);

				mailTemplatesSheet.getRow(i + 1).getCell(4).setCellValue(intrnalTitleNew);
			}

			FileOutputStream fos = new FileOutputStream(file);

			wb.write(fos);

		}

		wb.close();

		count = count + openCases2.size();

//		System.out.println("The Updated case Count is: " +count);

	}

	// ----- Write the Excel with "Latest_Case_Notes" the Open Cases view ------//

	public static void writeCaseNotes(List<String> caseNotes, String SheetName, int i, String ColumnName)
			throws IOException {

		File file = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);

		requiredSheet = wb.getSheet(SheetName);

		int ColumnCount = requiredSheet.getRow(0).getPhysicalNumberOfCells();

		ArrayList<String> CaseNote = new ArrayList<String>();

		String CNotes;

		for (String CN : caseNotes) {

			CNotes = GetWebElementTextValue.getCaseNotes(CN);

			// This is an array list and needs to be converted to the string.
			CaseNote.add(CNotes);

			StringBuffer sb = new StringBuffer();

			for (String s : CaseNote) {
				sb.append(s);
			}

			latestCNotes = sb.toString();

		}

		for (int column = 0; column < ColumnCount; column++) {

			String columnname = requiredSheet.getRow(0).getCell(column).getStringCellValue();

			if (columnname.equalsIgnoreCase(ColumnName)) {

				requiredSheet.getRow(i).getCell(column).setCellValue(latestCNotes);

				FileOutputStream fos = new FileOutputStream(file);

				wb.write(fos);
			}

			else {
				continue;
			}

			wb.close();
		}

	}

	// Use this code when you just have to write a single column in excel.

	// ----- Write the Excel with any sort of Data ------//

	public static void writeExcelData(List<WebElement> webElements, String sheetName, String ColumnToBeUpdated,
			boolean nextPage) throws IOException {

		File file = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);

		requiredSheet = wb.getSheet(sheetName);

		count = webElements.size(); // This count is going to be no. of rows to be updated

		int columnCount = requiredSheet.getRow(0).getPhysicalNumberOfCells();

		int rowCount = requiredSheet.getPhysicalNumberOfRows();

		ArrayList<String> DataToWrite = new ArrayList<String>();

		String toUpdateData;

		for (WebElement WE : webElements) {

			toUpdateData = GetWebElementTextValue.getCaseNumber(WE.getText());

			// This is an array list of all the case Number.
			DataToWrite.add(toUpdateData);
		}

		if (DataToWrite.isEmpty()) {

		}

		else {

			writeableDataCount = DataToWrite.size();

			if (nextPage == true) {

				for (int i = 1; i < rowCount; i++) {

					String dataValue = requiredSheet.getRow(i).getCell(0).getStringCellValue();

					if (dataValue.isEmpty()) {

						break;
					}

					else {

						writeDFMExcel.i = i;
					}

					filledRows = i;

				}

				for (int rows = (filledRows + 1); rows <= (filledRows + writeableDataCount); rows++) {

					for (int columns = 0; columns < columnCount; columns++) {

						String requiredColumn = requiredSheet.getRow(0).getCell(columns).getStringCellValue();

						if (requiredColumn.equalsIgnoreCase(ColumnToBeUpdated)) {

							String valueToUpdate = DataToWrite.get(rows - (filledRows + 1));

							if (valueToUpdate.isEmpty()) {

								break;
							}

							else {

								requiredSheet.getRow(rows - 1).getCell(columns).setCellValue(valueToUpdate);

								FileOutputStream fos = new FileOutputStream(file);

								wb.write(fos);

							}

						}

						else {

							break;
						}

					}

				}

			}

			else {

				for (int rows = 1; rows <= writeableDataCount; rows++) {

					for (int columns = 0; columns < columnCount; columns++) {

						String requiredColumn = requiredSheet.getRow(0).getCell(columns).getStringCellValue();

						if (requiredColumn.equalsIgnoreCase(ColumnToBeUpdated)) {

							String valueToUpdate = DataToWrite.get(rows - 1);

							if (valueToUpdate.isEmpty()) {

								continue;
							}

							else {

								requiredSheet.getRow(rows - 1).getCell(columns).setCellValue(valueToUpdate);

								FileOutputStream fos = new FileOutputStream(file);

								wb.write(fos);

							}

						}

						else {

							break;
						}

					}

				}

				wb.close();
			}
		}
	}

	public static void writeCaseLevelData(List<String> casseData, List<String> caseNumberList, String SheetName,
			String ColumnToBeUpdated) throws IOException {

		File file = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);

		requiredSheet = wb.getSheet(SheetName);

		count = casseData.size();

		int rowCount = caseNumberList.size();

		int columnCount = requiredSheet.getRow(0).getPhysicalNumberOfCells();

		for (int rows = 0; rows < rowCount; rows++) {

			for (int columns = 0; columns < columnCount; columns++) {

				String requiredColumn = requiredSheet.getRow(0).getCell(columns).getStringCellValue();

				if (requiredColumn.equalsIgnoreCase(ColumnToBeUpdated)) {

					String SR = caseNumberList.get(rows);

					String CaseNumber = requiredSheet.getRow(rows + 1).getCell(0).getStringCellValue();

					if (SR.equals(CaseNumber)) {

						String valueToUpdate = casseData.get(rows);

						if (valueToUpdate.isEmpty()) {

							continue;
						}

						else {

							requiredSheet.getRow(rows + 1).getCell(columns).setCellValue(valueToUpdate);

							FileOutputStream fos = new FileOutputStream(file);

							wb.write(fos);

						}

					}

					else {

						continue;
					}

				}

			}
		}

	}

	public static void updateExcel(String SheetName, HashMap<String, String> columnsName) throws IOException {

		File file = new File("C:\\DFM\\DFM\\configuration\\DFM_Data.xlsx");

		FileInputStream fis = new FileInputStream(file);

		wb = new XSSFWorkbook(fis);

		requiredSheet = wb.getSheet(SheetName);

		int activeRowCount = requiredSheet.getPhysicalNumberOfRows();

		int columnCount = requiredSheet.getRow(0).getPhysicalNumberOfCells();

		for (int column = 0; column < columnCount; column++) {

			String requiredColumn = requiredSheet.getRow(0).getCell(column).getStringCellValue();

			String colvalue = (String) columnsName.get(requiredColumn);

			for (int i = 1; i < activeRowCount; i++) {

				String rowValue = requiredSheet.getRow(i).getCell(column).getStringCellValue();

				if (rowValue.isEmpty()) {

					requiredSheet.getRow(column).getCell(column)
							.setCellValue(columnsName.get(columnsName.keySet().toArray()[column]));
					
					break;
				}
				
				FileOutputStream fos = new FileOutputStream(file);

				wb.write(fos);

			}

		}
	}

}
