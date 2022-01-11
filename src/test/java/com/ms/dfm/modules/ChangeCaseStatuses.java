package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.ms.readFiles.readPropertiesFile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ChangeCaseStatuses extends setupAndTearDown{

	public static Actions act = new Actions(driver);
	
	public static void changeStatus(String Status) throws IOException, InterruptedException {
		
		WebDriverWait wait  =  new WebDriverWait(driver, Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(readPropertiesFile.readloginPageElements("expandPanel")))).click();
		
		act.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath(readPropertiesFile.readloginPageElements("picklistCombobox")))));
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(readPropertiesFile.readloginPageElements("StatusDropDown")))).click();
		
		WebElement statusesDropDown = wait.until(presenceOfElementLocated(
				By.xpath(readPropertiesFile.readloginPageElements("statusList"))));
		
		List<WebElement> statusLists = statusesDropDown.findElements(By.tagName("li"));
		
		for(WebElement li : statusLists) {
			
			String newstatus = li.getText();
			
			if(newstatus.equalsIgnoreCase(Status)) {
				
				li.click();
				act.sendKeys(Keys.ESCAPE).perform();
				break;
			}
			else {
				
				continue;
			}
		}
		
		
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(readPropertiesFile.readloginPageElements("saveCase")))).click();
		
		
	}



}
