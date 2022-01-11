package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readPropertiesFile;
import com.ms.readFiles.writeDFMExcel;

public class collectCaseData extends setupAndTearDown {

	public static void collectCaseInfo() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("opencaseDashboard"))));

		WebElement listOpenCases1 = driver
				.findElement(By.xpath(readPropertiesFile.readloginPageElements("opencaseList")));

		List<WebElement> openCases1 = listOpenCases1
				.findElements(By.xpath(readPropertiesFile.readloginPageElements("childItem")));

		writeDFMExcel.openCasesFirstPage(openCases1, "Case_Details");

		applyWaits.sleep(3000);

		WebElement nextButton = driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("nextButton")));

		while (nextButton.isEnabled()) {

			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("nextArrow"))))).click();

			wait.until(
					presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("opencaseDashboard"))));

			Actions act = new Actions(driver);

			act.sendKeys(Keys.PAGE_UP).perform();

			applyWaits.sleep(3000);

			wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("opencaseList"))));

			WebElement listOpenCases2 = driver
					.findElement(By.xpath(readPropertiesFile.readloginPageElements("opencaseList")));

			List<WebElement> openCases2 = listOpenCases2
					.findElements(By.xpath(readPropertiesFile.readloginPageElements("childItem")));

			writeDFMExcel.openCasesNextPage(openCases2, "Case_Details");

		}

	}
}
