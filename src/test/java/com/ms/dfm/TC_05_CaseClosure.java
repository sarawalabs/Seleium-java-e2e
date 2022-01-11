package com.ms.dfm;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ms.dfm.modules.applyWaits;
import com.ms.readFiles.readDFMExcel;
import com.ms.readFiles.readPropertiesFile;


/*Step 1: Sent closure mail to the customer.
Step 2: update the Case Closure tab for the same.
Step 3: Update internal title with statement 'CLOSE'
Step 5: Close the cases with Internal title as 'CLOSE' or 'close'.*/

public class TC_05_CaseClosure extends TC_04_SendMail {

	@Test(priority = 8)
	public static void closeCase() throws IOException, InterruptedException {
		
		Reporter.log("Started case closure activities");
		
		test = extent.createTest("closeCase");
		
		test.log(Status.INFO, "Started closing the opened cases");

		Actions act = new Actions(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

		readDFMExcel close_case = new readDFMExcel();

		XSSFSheet case_closure = close_case.getSheetData("");

		int cell_count = case_closure.getPhysicalNumberOfRows();

		for (int i = 1; i < cell_count; i++) {

			String CaseNumber = case_closure.getRow(i).getCell(0).getStringCellValue();

			String Symptoms = case_closure.getRow(i).getCell(1).getStringCellValue();

			String Cause = case_closure.getRow(i).getCell(2).getStringCellValue();

			String Resolution = case_closure.getRow(i).getCell(3).getStringCellValue();

			String resolveStaus = case_closure.getRow(i).getCell(4).getStringCellValue();
			
			if (CaseNumber.isEmpty()) {
				
				break;
			}

			else {
				
				wait.until(presenceOfElementLocated(By.cssSelector(readPropertiesFile.readloginPageElements("searchBox"))));

				wait.until(presenceOfElementLocated(By.cssSelector(readPropertiesFile.readloginPageElements("searchBox"))))
						.sendKeys(CaseNumber + Keys.ENTER);

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("searchResultItem"))))
						.click();

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("resolve_case_btn"))))
						.click();

				// Resolve case page is opened.

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("popup_screen"))));

				// Update Symptoms actions

				wait.until(
						presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("iframe1_symptoms"))));
				driver.switchTo()
						.frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("iframe1_symptoms"))));

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("iframe2"))));
				driver.switchTo().frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("iframe2"))));

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("textArea")))).click();

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("textArea"))))
						.sendKeys(Symptoms + Keys.ENTER);
				
				applyWaits.sleep(1000);

				driver.switchTo().defaultContent();

				// Update Cause actions
				act.sendKeys(Keys.PAGE_DOWN).perform();

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("iframe1_cause"))));
				driver.switchTo()
						.frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("iframe1_cause"))));

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("iframe2"))));
				driver.switchTo().frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("iframe2"))));

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("textArea")))).click();

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("textArea"))))
						.sendKeys(Cause + Keys.ENTER);
				
				applyWaits.sleep(1000);

				driver.switchTo().defaultContent();

				// Update Resolution actions
				act.sendKeys(Keys.PAGE_DOWN).perform();

				wait.until(
						presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("iframe1_resolution"))));
				driver.switchTo().frame(
						driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("iframe1_resolution"))));

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("iframe2"))));
				driver.switchTo().frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("iframe2"))));

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("textArea")))).click();

				wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("textArea"))))
						.sendKeys(Resolution + Keys.ENTER);
				
				applyWaits.sleep(1000);

				driver.switchTo().defaultContent();

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("resolve_Dropdown")))).click();

				applyWaits.sleep(2000);
				wait.until(presenceOfElementLocated
						(By.xpath("//li[contains(text(), '"+resolveStaus+"')]"))).click();
				
				//wait.until(ExpectedConditions
				//		.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("close_resolveScreen")))).click();

			}
			
		}
		
		test.log(Status.INFO, "Finished closing the opened cases");
		
		Reporter.log("Finished case closure activities");

	}
}
