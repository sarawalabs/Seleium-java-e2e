package com.ms.dfm;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ms.dfm.modules.applyWaits;
import com.ms.dfm.modules.scrollDownUntil;
import com.ms.dfm.modules.searchCases;
import com.ms.readFiles.readDFMExcel;
import com.ms.readFiles.readPropertiesFile;
import com.ms.readFiles.writeDFMExcel;

public class TC_07_GetUnAssignedCaseDetails extends TC_06_GetUnassignedCases{
	
	public static XSSFSheet CaseNumber;
	
	public static String Severity;
	public static String AreaPath;
	public static String cxVerbatim;
	public static String slaState;
	public static String isCritSit;
	public static String Region;
	
	public static List<String> CaseSeverity = new ArrayList<String>();
	public static List<String> SupportAreaPath = new ArrayList<String>();
	public static List<String> CustomerVerbatim = new ArrayList<String>();
	public static List<String> SLAState = new ArrayList<String>();
	public static List<String> CritSitState = new ArrayList<String>();
	public static List<String> TimeZone = new ArrayList<String>();
	public static List<String> AssignmentsUser = new ArrayList<String>();
	public static List<String> SR = new ArrayList<String>();
	public static String AssignmentHistory;
	
	@Test(priority = 10)
	public static void GettUnassignedCaseDetails() throws IOException, InterruptedException {
		
		Actions act = new Actions(driver);
		
		Reporter.log("======= Started capturing the latest case logs ====== ");
		
		test = extent.createTest("getLatestCaseNotes");

		test.log(Status.INFO, "Started Capturing the latest case Notes");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		readDFMExcel readData = new readDFMExcel();
		
		CaseNumber = readData.getSheetData("UnAssigned_Cases");
		
		int count = CaseNumber.getPhysicalNumberOfRows();
		
		for(int i=1; i<count;i++) {
		
			String SRNumber = CaseNumber.getRow(i).getCell(0).getStringCellValue();
			
			if(SRNumber.isEmpty()) {
				
				break;
			}
			
			else if(!SRNumber.contains("CAS-")) 
			
			{
				
				@SuppressWarnings("unused")
				searchCases search = new searchCases(SRNumber);				

				// collect the severity of the case.
				Severity = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("Severity")))).
						getAttribute("aria-label");
				
				System.out.println(Severity);
				
				//Collect Support Area path
				AreaPath = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("supportArea")))).getText();
				
				System.out.println(AreaPath);
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("supportArea")))).click();
				
				
				scrollDownUntil.scrollDown("cxVerbatim");
				
				// collect customer verbatim
				cxVerbatim = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("cxVerbatim")))).getText();
				
				System.out.println(cxVerbatim);
				
				//collect SLA state.
				slaState = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("SLA")))).
						getAttribute("title");
				
				System.out.println(slaState);
				
				//collect 24*7 status
				isCritSit = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("24*7")))).getAttribute("aria-label");
				
				isCritSit = isCritSit.substring(6);
				
				System.out.println(isCritSit);
			    
				//collect timezone.
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("detailsTab")))).click();
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("createdOn")))).click();
				
				do {

					act.sendKeys(Keys.PAGE_DOWN).perform();

					applyWaits.sleep(3000);

				}

				while (wait.until(presenceOfElementLocated(By.
								xpath(readPropertiesFile.readloginPageElements("timeZone")))).equals(null));
				
				Region = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("timeZone")))).getText();
				
				System.out.println(Region);
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("relatedTab")))).click();
				
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistory")))).click();
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistoryTab")))).click();
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistoryframe"))));
				
				driver.switchTo().frame(driver.findElement(By.
						xpath(readPropertiesFile.readloginPageElements("auditHistoryframe"))));
				
				Select selectItem =  new Select(wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistoryFilter")))));
				
				selectItem.selectByVisibleText("Previously Assigned To");
				
				WebElement auditHistoryTable = wait.until(presenceOfElementLocated(By.
						xpath(readPropertiesFile.readloginPageElements("previoyslyAssignedTo"))));
				
				List <WebElement> auditHistoryData = auditHistoryTable.findElements(By.
						xpath(readPropertiesFile.readloginPageElements("childItem")));
				
				for(WebElement caseAssignmentsHistory : auditHistoryData){
					
					AssignmentHistory = caseAssignmentsHistory.getText();
				}
					
				SR.add(SRNumber);
				CaseSeverity.add(Severity);
				SupportAreaPath.add(AreaPath);
				CustomerVerbatim.add(cxVerbatim);
				SLAState.add(slaState);
				CritSitState.add(isCritSit);
				TimeZone.add(Region);
				AssignmentsUser.add(AssignmentHistory);
				
				System.out.println("-----------------------rows completed " + i +" /n");
				
				driver.switchTo().defaultContent();
				
				act.moveToElement(wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("moveCaseTab"))))).build().perform();
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("closeCaseTab")))).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("confirmTabClosure")))).click();
				
				act.moveToElement(wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("searchTab"))))).build().perform();
				
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("closeCaseSearch")))).click();
				
				
			}
			
			else {
				
				@SuppressWarnings("unused")
				searchCases search = new searchCases(SRNumber);
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("parentCase")))).click();
				
				applyWaits.sleep(2000);
				
				// collect the severity of the case.
				Severity = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("Severity")))).
						getAttribute("aria-label");
				
				System.out.println(Severity);
				
				//Collect Support Area path
				AreaPath = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("supportArea")))).getText();
				
				System.out.println(AreaPath);
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("supportArea")))).click();
				
				
				
				do {

					act.sendKeys(Keys.PAGE_DOWN).perform();

					applyWaits.sleep(3000);

				}

				while (driver.findElements(By.
						xpath(readPropertiesFile.readloginPageElements("cxVerbatim"))).size()<0);
				
				// collect customer verbatim
				cxVerbatim = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("cxVerbatim")))).getText();
				
				System.out.println(cxVerbatim);
				
				//collect SLA state.
				slaState = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("SLA")))).
						getAttribute("title");
				
				System.out.println(slaState);
				
				//collect 24*7 status
				isCritSit = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("24*7")))).getAttribute("aria-label");
				
				isCritSit = isCritSit.substring(6);
				
				System.out.println(isCritSit);
			    
				//collect timezone.
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("detailsTab")))).click();
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("createdOn")))).click();
				
				do {

					act.sendKeys(Keys.PAGE_DOWN).perform();

					applyWaits.sleep(3000);

				}

				while (wait.until(presenceOfElementLocated(By.
								xpath(readPropertiesFile.readloginPageElements("timeZone")))).equals(null));
				
				Region = wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("timeZone")))).getText();
				
				System.out.println(Region);
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("relatedTab")))).click();
				
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistory")))).click();
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistoryTab")))).click();
				
				wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistoryframe"))));
				
				driver.switchTo().frame(driver.findElement(By.
						xpath(readPropertiesFile.readloginPageElements("auditHistoryframe"))));
				
				Select selectItem =  new Select(wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("auditHistoryFilter")))));
				
				selectItem.selectByVisibleText("Previously Assigned To");
				
				WebElement auditHistoryTable = wait.until(presenceOfElementLocated(By.
						xpath(readPropertiesFile.readloginPageElements("previoyslyAssignedTo"))));
				
				List <WebElement> auditHistoryData = auditHistoryTable.findElements(By.
						xpath(readPropertiesFile.readloginPageElements("childItem")));
				
				for(WebElement caseAssignmentsHistory : auditHistoryData){
					
					AssignmentHistory = caseAssignmentsHistory.getText();
				}
					
				SR.add(SRNumber);
				CaseSeverity.add(Severity);
				SupportAreaPath.add(AreaPath);
				CustomerVerbatim.add(cxVerbatim);
				SLAState.add(slaState);
				CritSitState.add(isCritSit);
				TimeZone.add(Region);
				AssignmentsUser.add(AssignmentHistory);
				
				
				driver.switchTo().defaultContent();
				
				act.moveToElement(wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("moveCaseTab"))))).build().perform();
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("closeCaseTab")))).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("confirmTabClosure")))).click();
				
				act.moveToElement(wait.until(presenceOfElementLocated(
						By.xpath(readPropertiesFile.readloginPageElements("searchTab"))))).build().perform();
				
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("closeCaseSearch")))).click();
				
			}
		}
		
		
		writeDFMExcel.writeCaseLevelData(CaseSeverity, SR, "UnAssigned_Cases", "Severity");
		writeDFMExcel.writeCaseLevelData(TimeZone, SR, "UnAssigned_Cases", "TimeZone");
		writeDFMExcel.writeCaseLevelData(SLAState, SR, "UnAssigned_Cases", "SLA");
		writeDFMExcel.writeCaseLevelData(CritSitState, SR, "UnAssigned_Cases", "24*7");
		writeDFMExcel.writeCaseLevelData(SupportAreaPath, SR, "UnAssigned_Cases", "Path");
		writeDFMExcel.writeCaseLevelData(AssignmentsUser, SR, "UnAssigned_Cases", "Case_Owner");
		writeDFMExcel.writeCaseLevelData(CustomerVerbatim, SR, "UnAssigned_Cases", "Verbatim");
	}

}
