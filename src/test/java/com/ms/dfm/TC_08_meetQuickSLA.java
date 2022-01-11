package com.ms.dfm;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ms.dfm.modules.GetWebElementTextValue;
import com.ms.readFiles.readPropertiesFile;

public class TC_08_meetQuickSLA extends TC_07_GetUnAssignedCaseDetails {
	
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	
	@Test(priority=11)
	public static void checkSLAPendingCasesBin() throws IOException, InterruptedException {
				
		wait.until(presenceOfElementLocated(By.
				xpath(readPropertiesFile.readloginPageElements("pendingCasePane"))));
		
		WebElement pendingCasePane =  driver.findElement(By.
				xpath(readPropertiesFile.readloginPageElements("pendingCasePane")));
		
		List<WebElement> pendingSLACases = pendingCasePane.findElements(By.
				xpath(readPropertiesFile.readloginPageElements("childItem")));
		
		for(WebElement WE : pendingSLACases) {
			
			String SR = GetWebElementTextValue.extractSR(WE.getText());

			com.ms.dfm.modules.meetSLA.meetSLA_Call(SR);
			
		}
		
	}

	@Test(priority=12)
	public static void checkSLAPendingCasesQueue() throws IOException, InterruptedException {
		
		wait.until(presenceOfElementLocated(By.
				xpath(readPropertiesFile.readloginPageElements("pendingCasePane"))));
		
		WebElement pendingCasePane =  driver.findElement(By.
				xpath(readPropertiesFile.readloginPageElements("pendingCasePane")));
		
		List<WebElement> pendingSLACases = pendingCasePane.findElements(By.
				xpath(readPropertiesFile.readloginPageElements("childItem")));
		
		for(WebElement WE : pendingSLACases) {
			
			String SR = GetWebElementTextValue.extractSR(WE.getText());

			com.ms.dfm.modules.meetSLA.meetSLA_Call(SR);
		
		}

	}

}