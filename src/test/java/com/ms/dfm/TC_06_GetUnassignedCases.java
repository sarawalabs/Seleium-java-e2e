package com.ms.dfm;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ms.dfm.modules.applyWaits;
import com.ms.readFiles.readPropertiesFile;
import com.ms.readFiles.writeDFMExcel;

public class TC_06_GetUnassignedCases extends TC_05_CaseClosure {
	
	@Test(priority=9)
	public static void collectUnassignedCases() throws IOException, InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		wait.until(presenceOfElementLocated(By.
				xpath(readPropertiesFile.readloginPageElements("Dasboard_Dropdown"))));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.
				xpath(readPropertiesFile.readloginPageElements("Dasboard_Dropdown")))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.
				xpath(readPropertiesFile.readloginPageElements("Select_DasboardType")))).click();
		
		wait.until(presenceOfElementLocated(By.
				xpath(readPropertiesFile.readloginPageElements("UnAssigned_Cases"))));
		
		WebElement listCases = driver.findElement(By.
				xpath(readPropertiesFile.readloginPageElements("UnAssigned_Cases")));
		
		List <WebElement> allData = listCases.findElements(By.
				xpath(readPropertiesFile.readloginPageElements("childItem")));
		
		writeDFMExcel.writeExcelData(allData, "UnAssigned_Cases", "CaseNumber", false);
		
		WebElement nextButton = driver.findElement(By.
				xpath(readPropertiesFile.readloginPageElements("nextButton")));
		
		while (nextButton.isEnabled()) {
			
			wait.until(ExpectedConditions.elementToBeClickable(By.
					xpath(readPropertiesFile.readloginPageElements("nextArrow")))).click();
			
			wait.until(presenceOfElementLocated(By.
					xpath(readPropertiesFile.readloginPageElements("UnAssigned_Cases"))));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.
					xpath(readPropertiesFile.readloginPageElements("refreshButton"))));
			
			applyWaits.sleep(10000);
			
			WebElement listNextPageCases = driver.findElement(By.
					xpath(readPropertiesFile.readloginPageElements("UnAssigned_Cases")));
			
			List <WebElement> allNextPageData = listNextPageCases.findElements(By.
					xpath(readPropertiesFile.readloginPageElements("childItem")));
			
			writeDFMExcel.writeExcelData(allNextPageData, "UnAssigned_Cases", "CaseNumber", true);
			
		}

	}

}
