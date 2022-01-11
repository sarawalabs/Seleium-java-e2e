package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readPropertiesFile;

public class closeCaseSearchTab extends setupAndTearDown{
	
	public static void closeSearchTab() throws IOException, InterruptedException {
		
		Actions act = new Actions(driver);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
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
