package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ms.readFiles.readPropertiesFile;

public class applyFilter extends setupAndTearDown {

	public static Actions act = new Actions(driver);

	public static JavascriptExecutor executor = (JavascriptExecutor) driver;

	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

	public static void applyFilters(String FilterType, String Operation) throws IOException, InterruptedException {

		do {

			act.sendKeys(Keys.PAGE_DOWN).perform();

			applyWaits.sleep(3000);

		}

		while (wait
				.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("filter"))))
				.equals(null));

		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("filter"))))
				.click();

		wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("filter_view"))));

		if (!FilterType.equalsIgnoreCase("mailUpdates")) {

			wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements(FilterType))));

			WebElement checkBox = driver.findElement(By.xpath(readPropertiesFile.readloginPageElements(FilterType)));

			if (!checkBox.isSelected() && Operation == "uncheck") {

				Assert.assertEquals("true", checkBox.getAttribute("unchecked"));

				Assert.assertFalse(checkBox.isSelected());

			}

			else if (!checkBox.isSelected() && Operation == "check") {

				executor.executeScript("arguments[0].click();", checkBox);

				applyWaits.sleep(2000);
			}

			else if (checkBox.isSelected() && Operation == "check") {

				Assert.assertEquals("true", checkBox.getAttribute("checked"));

				Assert.assertTrue(checkBox.isSelected());
			}

			else if (checkBox.isSelected() && Operation == "uncheck") {

				executor.executeScript("arguments[0].click();", checkBox);

				applyWaits.sleep(2000);
			}

		}

		else if (FilterType.equalsIgnoreCase("mailUpdates") && Operation == "check") {
			
			wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath(readPropertiesFile.readloginPageElements("activityType")))).click();
			
			applyWaits.sleep(2000);

			WebElement EmailcheckBox = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(readPropertiesFile.readloginPageElements("emailCheckbox"))));

			// Scrolling down the page till the element is found
			executor.executeScript("arguments[0].scrollIntoView();", EmailcheckBox);

			executor.executeScript("arguments[0].click();", EmailcheckBox);
			
			wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath(readPropertiesFile.readloginPageElements("activityStatus")))).click();
			
			applyWaits.sleep(2000);
			
			WebElement closeCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(readPropertiesFile.readloginPageElements("closeCheckbox"))));

			// Scrolling down the page till the element is found
			executor.executeScript("arguments[0].scrollIntoView();", closeCheckbox);

			executor.executeScript("arguments[0].click();", closeCheckbox);

			applyWaits.sleep(2000);

		}

		WebElement closeFilter = driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("close_filter")));

		JavascriptExecutor executor1 = (JavascriptExecutor) driver;

		executor1.executeScript("arguments[0].click();", closeFilter);

		applyWaits.sleep(2000);

	}
}
