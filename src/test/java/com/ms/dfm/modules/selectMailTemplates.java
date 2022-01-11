package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readDFMExcel;
import com.ms.readFiles.readPropertiesFile;
import com.ms.readFiles.writeDFMExcel;

@SuppressWarnings("unused")
public class selectMailTemplates extends setupAndTearDown {

	public static XSSFSheet mailTemplates;
	public static XSSFSheet caseDetails;
	public static XSSFSheet UpdateCaseClosureSheet;

	public static String caseNumber;
	public static String customerName;
	public static String CaseStatus;
	public static String InternalTitle;
	public static String communicationType;
	public static String symptoms;
	public static String cause;
	public static String resolution;
	public static String moreInfo;
	public static String Mail_Template;
	public static String communication_Type;

	public static List<String> inputParameters;

	public static int caseCounts;
	public static int templateCounts;

	public static void selectMailTemplate() throws IOException, InterruptedException {

		readDFMExcel setmail = new readDFMExcel();

		mailTemplates = setmail.getSheetData("Mail_Templates");

		caseDetails = setmail.getSheetData("Case_Details");

		caseCounts = caseDetails.getPhysicalNumberOfRows();

		templateCounts = mailTemplates.getPhysicalNumberOfRows();

		for (int i = 1; i < caseCounts; i++) {

			// Check details from the "Case_Details" sheet
			caseNumber = caseDetails.getRow(i).getCell(0).getStringCellValue();

			customerName = caseDetails.getRow(i).getCell(1).getStringCellValue();

			CaseStatus = caseDetails.getRow(i).getCell(3).getStringCellValue();

			InternalTitle = caseDetails.getRow(i).getCell(4).getStringCellValue();

			communicationType = caseDetails.getRow(i).getCell(6).getStringCellValue();

			symptoms = caseDetails.getRow(i).getCell(7).getStringCellValue();
			cause = caseDetails.getRow(i).getCell(8).getStringCellValue();
			resolution = caseDetails.getRow(i).getCell(9).getStringCellValue();
			moreInfo = caseDetails.getRow(i).getCell(10).getStringCellValue();

			if (caseNumber != null) {

				if (CaseStatus.equalsIgnoreCase("Initial contact pending") 
						&& (!communicationType.isEmpty() || communicationType!=null)) {

					inputParameters = Arrays.asList(caseNumber, customerName);

					sendFirstContactMail(communicationType, inputParameters);
					
					addSignature.signature("Signature");
					
					ChangeCaseStatuses.changeStatus("Pending customer response");
					
					closeCaseSearchTab.closeSearchTab();
				}
				
				
				else if(communicationType.isEmpty() || communicationType ==null) {
					
					continue;
				}
				
				else if(communicationType.contains("Case_Closure_Mail")) {
						
					HashMap <String, String> inputParameters = new HashMap<String, String>();
					
					inputParameters.put("SR", caseNumber);
					inputParameters.put("Symptoms", symptoms);
					inputParameters.put("Cause", cause);
					inputParameters.put("Resolution", resolution);
					inputParameters.put("Status Reason", "Resolved");

					writeDFMExcel.updateExcel("Case_Closure", inputParameters);
					
					continue;
				}

				else if (!(CaseStatus.equalsIgnoreCase("Initial contact pending")) 
							&& (!communicationType.isEmpty() || communicationType!=null)){

					inputParameters = Arrays.asList(caseNumber, customerName, symptoms, cause, resolution, moreInfo);

					sendClosureAndOtherMails(communicationType, inputParameters);
					
//					addSignature.signature("Signature");
					
					closeCaseSearchTab.closeSearchTab();
					
					
				}
			}

			else if (caseNumber == null) {

				break;

			}

		}

	}

	public static void sendFirstContactMail(String communicationType, List<String> replacements)
			throws IOException, InterruptedException {

		for (int j = 1; j < templateCounts; j++) {

			{

				communication_Type = mailTemplates.getRow(j).getCell(0).getStringCellValue();

				if (communication_Type.equalsIgnoreCase(communicationType)) {

					Mail_Template = mailTemplates.getRow(j).getCell(1).getStringCellValue();

					Mail_Template = updateMailContent(Mail_Template, replacements);

					@SuppressWarnings("unused")
					searchCases search = new searchCases(caseNumber);

					// In order to Click on '+' button from the case main page to send mail or add
					plusButtonAction.buttonActions("FQR", Mail_Template);

				}

				else if(communicationType.isEmpty() || communicationType!=null) {

					continue;
				}

			}
		}
	}

	public static void sendClosureAndOtherMails(String communicationType, List<String> replacements)
			throws IOException, InterruptedException {

		for (int j = 1; j < templateCounts; j++) {

			communication_Type = mailTemplates.getRow(j).getCell(0).getStringCellValue();

			if (communication_Type.equalsIgnoreCase(communicationType)) {

				// Pick the mail template as "Case_Closure_Mail" from "Mail_Templates" sheet
				Mail_Template = mailTemplates.getRow(j).getCell(1).getStringCellValue();

				Mail_Template = updateMailContent(Mail_Template, replacements);

				@SuppressWarnings("unused")
				searchCases search = new searchCases(caseNumber);

				applyFilter.applyFilters("notes_checkbox", "uncheck");

				plusButtonAction.buttonActions("Other", Mail_Template);

			}

			else {

				continue;
			}
		}

	}

	public static String updateMailContent(String Mail_Template, List<String> inputparameters) {

		String updatedMail = Mail_Template;

		String[] splitcName = inputparameters.get(1).split(" ", 2);

		String splitcustomerName = splitcName[0].toString();

		if (inputparameters.size() == 2) {

			String[][] replacements = { 
										{ "xxcxName", splitcustomerName }, 
										{ "xxSRNumber", inputparameters.get(0) } 
									};

			for (String[] replacement : replacements) {
				updatedMail = updatedMail.replace(replacement[0], replacement[1]);
			}

		}

		else {

			String[][] replacements = { 
										{ "xxcxName", splitcustomerName },
										{ "xxSRNumber", inputparameters.get(0) }, 
										{ "XXSymptoms", inputparameters.get(2) },
										{ "XXCause", inputparameters.get(3) },
										{ "XXResolution", inputparameters.get(4) },
										{ "XXMoreInfo", inputparameters.get(5) } 
									};

			for (String[] replacement : replacements) {
				updatedMail = updatedMail.replace(replacement[0], replacement[1]);
			}

		}

		return updatedMail;

	}

}
