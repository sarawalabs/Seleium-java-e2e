package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readPropertiesFile;

public class searchCases extends setupAndTearDown{
	
	public searchCases(String Case_Number) throws InterruptedException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		
		applyWaits.sleep(2000);

		wait.until(presenceOfElementLocated(
				By.cssSelector(readPropertiesFile.readloginPageElements("searchBox"))));

		wait.until(presenceOfElementLocated(
				By.cssSelector(readPropertiesFile.readloginPageElements("searchBox"))))
				.sendKeys(Case_Number);

		wait.until(presenceOfElementLocated(
				By.cssSelector(readPropertiesFile.readloginPageElements("searchBox"))))
				.sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(readPropertiesFile.readloginPageElements("searchResultItem")))).click();
		
		/*
		 * wait.until(ExpectedConditions.elementToBeClickable(
		 * By.xpath(readPropertiesFile.readloginPageElements("knowledgeAssistCollapse"))
		 * )).click();
		 */
		

		applyWaits.sleep(3000);
		
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("emailCC"))))
				.click();
	}	

}
