package com.ms.dfm.modules;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readPropertiesFile;

public class addSignature extends setupAndTearDown {
	
	public static Actions act = new Actions(driver);
	
	public static JavascriptExecutor js = (JavascriptExecutor)driver;
	
	public static void signature(String signature) throws IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
//		js.executeScript("document.body.style.zoom = '90%';");
		
		wait.until(ExpectedConditions.elementToBeClickable
				(By.xpath(readPropertiesFile.readloginPageElements("signatureButton")))).click();
		
		
		act.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath(readPropertiesFile.readloginPageElements("searchArea")))));
		

		wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath(readPropertiesFile.readloginPageElements("searchSignatureField")))).click();
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated
				(By.xpath(readPropertiesFile.readloginPageElements("searchArea")))).sendKeys(signature + Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable
				(By.xpath(readPropertiesFile.readloginPageElements("searchItem")))).click();
		
		
		wait.until(ExpectedConditions.elementToBeClickable
				(By.xpath(readPropertiesFile.readloginPageElements("selectButton")))).click();
		
		
	}

}
