
package com.ms.dfm.modules;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readDFMExcel;
import com.ms.readFiles.writeDFMExcel;

public class getCaseNotes extends setupAndTearDown {

	public static List<String> caseNotes;

	@SuppressWarnings("unused")
	public static void caseNotes(String SheetName) throws IOException, InterruptedException {
		Actions act = new Actions(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		readDFMExcel readCaseNumber = new readDFMExcel();

		XSSFSheet caseDetails = readCaseNumber.getSheetData(SheetName);

		int activeRowsCount = caseDetails.getPhysicalNumberOfRows();

		for (int i = 1; i < activeRowsCount; i++) {

			String Case_Number = caseDetails.getRow(i).getCell(0).getStringCellValue();
			String cxName = caseDetails.getRow(i).getCell(1).getStringCellValue();
			String Case_Status = caseDetails.getRow(i).getCell(3).getStringCellValue();

			if (!(Case_Number.isEmpty())) {

				searchCases search = new searchCases(Case_Number);

				if (Case_Status.equalsIgnoreCase("Initial contact pending")) {

					// If the state is Initial contact pending, but IR is already met, check for the case notes, if the mail is sent or not.
					if (IsSLAMet.checkSLAMet(Case_Number).equalsIgnoreCase("Succeeded")) {

						applyFilter.applyFilters("notes_checkbox", "checked");

						caseNotes = GatLatestCaseNote.copyLatestCaseNotes(cxName);

						writeDFMExcel.writeCaseNotes(caseNotes, "Case_Details", i, "Latest_Case_Notes");
						
						ChangeCaseStatuses.changeStatus("Pending customer response");

						driver.switchTo().defaultContent();

						closeCaseSearchTab.closeSearchTab();

					}

					// In IR missed scenario, do not meet IR from call, else get case notes and proceed with send mail section.
					else if (IsSLAMet.checkSLAMet(Case_Number).equalsIgnoreCase("Expired")
							&& IsSLAMet.checkSLAMet(Case_Number) != "Succeeded") {
						
						caseNotes = GatLatestCaseNote.copyLatestCaseNotes(cxName);

						writeDFMExcel.writeCaseNotes(caseNotes, "Case_Details", i, "Latest_Case_Notes");
						
						driver.switchTo().defaultContent();

						closeCaseSearchTab.closeSearchTab();

					}
					
					// state is Initial contact pending. SLA is not expired yet and SLA is not in succeeded state, meet the IR using call option.
					else if (IsSLAMet.checkSLAMet(Case_Number) != "Expired"
							&& IsSLAMet.checkSLAMet(Case_Number) != "Succeeded") {

						System.out.println("Meeting the SLA for the Case " +Case_Number);
						
						meetSLA.meetSLA_Call(Case_Number);

						closeCaseSearchTab.closeSearchTab();

					}

				}

				else {
					
					caseNotes = GatLatestCaseNote.copyLatestCaseNotes(cxName);

					writeDFMExcel.writeCaseNotes(caseNotes, "Case_Details", i, "Latest_Case_Notes");

					driver.switchTo().defaultContent();

					closeCaseSearchTab.closeSearchTab();

				}

			}

			else {

				continue;
			}

		}

	}
}
