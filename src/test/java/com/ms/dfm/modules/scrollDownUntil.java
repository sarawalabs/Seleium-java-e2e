package com.ms.dfm.modules;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.ms.readFiles.readPropertiesFile;

public class scrollDownUntil extends setupAndTearDown {

	public static void scrollDown(String searchedElement) throws InterruptedException, IOException {

		Actions act = new Actions(driver);

		do {

			act.sendKeys(Keys.PAGE_DOWN).perform();

			applyWaits.sleep(3000);
			
			act.sendKeys(Keys.PAGE_DOWN).perform();
			
			applyWaits.sleep(3000);
			
			act.sendKeys(Keys.PAGE_DOWN).perform();
			
			applyWaits.sleep(3000);

		}

		while (driver.findElements(By.xpath(readPropertiesFile.readloginPageElements(searchedElement))).size()<0);

	}

}
