
package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readPropertiesFile;

public class plusButtonAction extends setupAndTearDown {

	public static Actions act = new Actions(driver);

	public static JavascriptExecutor js = (JavascriptExecutor) driver;;

	public static void buttonActions(String button, String Mail_Template) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		if (button.equals("phoneCall") && Mail_Template == "") {

			scrollDownUntil.scrollDown("clickPlusSign");

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("clickPlusSign")))).click();

			wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements(button)))).click();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("callSubject")))).clear();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("callSubject"))))
					.sendKeys("met SLA");

			String key = wait.until(
					presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("contactNumber"))))
					.getAttribute("title");

			if (key.equalsIgnoreCase("")) {

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("contactNumber"))))
						.clear();

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("contactNumber"))))
						.sendKeys("123");

			}

			else {

				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("contactNumber"))));
			}

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("saveButton")))).click();

		}

		/* When you have to select the Mail button from the '+' menu option, FQR mail */
		else if (button.equals("FQR")) {

			scrollDownUntil.scrollDown("clickPlusSign");

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("clickPlusSign")))).click();

			wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("clickMail"))))
					.click();

			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("caseSubjectArea"))))
					.click();

			act.sendKeys(Keys.PAGE_DOWN).perform();

			applyWaits.sleep(5000);

			driver.switchTo()
					.frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("switchMailFrame1"))));

			driver.switchTo()
					.frame(driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("switchMailFrame2"))));

			driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("writeMailTextArea")))
					.sendKeys(Mail_Template);

			driver.switchTo().defaultContent();
		}

		/*
		 * If you have to reply on cusotmer's mail, send follow up, strike mail and LQR
		 */

		else if (button.equals("Other")) {
	
			// Apply filter just to view only closed mail states.
			applyFilter.applyFilters("mailUpdates", "check");

			/*
			 * 1. First check the 'Email from xx' if it's customer, MS Internal (collab,
			 * CSAM, etc) or Case owner (yourself or other). 2. Then check if the mail title
			 * is Automatic Reply or not. 3. Then finally check if the mail is Active
			 * (draft) or Closed (already Sent)
			 */

			if (driver.findElements(By.xpath(readPropertiesFile.readloginPageElements("expandMailList"))).size()!= 0) {

				WebElement expandMailList = driver
						.findElement(By.xpath(readPropertiesFile.readloginPageElements("expandMailList")));
				
				// Scrolling down the page till the element is found
				js.executeScript("arguments[0].scrollIntoView();", expandMailList);
				
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("expandMailList"))))
						.click();

			}

			else {
				
			}


			ArrayList<String> LabelsList = new ArrayList<String>();

			int mailCount = driver.findElements(By.xpath(readPropertiesFile.readloginPageElements("caseNotesList")))
					.size();

			for (int i = 1; i <= mailCount; i++) {

				LabelsList.clear();

				WebElement firstTimeline = driver.findElement(
						By.xpath(readPropertiesFile.readloginPageElements("timeline_record") + "[" + i + "]"));

				WebElement mailStatus = driver
						.findElement(By.xpath(readPropertiesFile.readloginPageElements("mailStatuspre") + i
								+ readPropertiesFile.readloginPageElements("mailStatuspost")));

				mailStatus.getText();

				List<WebElement> mailLabels = firstTimeline.findElements(By.tagName("Label"));

				for (WebElement Labels : mailLabels) {

					String LabelString = Labels.getText();

					LabelsList.add(LabelString);

				}

				String emailFrom = LabelsList.get(0) + " " + LabelsList.get(1);
				String automaticReply = LabelsList.get(2);

				/* If the email ia an Automatic Reply then just move to the next value */
				if (automaticReply.contains("Automatic reply")) {

					// scrolling to the next email row and then going for the next loop

					// Locating element by link text and store in variable "Element"
					WebElement nextElement = driver.findElement(By
							.xpath(readPropertiesFile.readloginPageElements("timeline_record") + "[" + (i) + "]"));

					// Scrolling down the page till the element is found
					js.executeScript("arguments[0].scrollIntoView();", nextElement);

					continue;

				}

				else {

					if (mailStatus.getText().equalsIgnoreCase("Active")) {

						WebElement nextElement = driver.findElement(By.xpath(
								readPropertiesFile.readloginPageElements("timeline_record") + "[" + (i + 1) + "]"));

						js.executeScript("arguments[0].scrollIntoView();", nextElement);

						continue;
					}

					else if (!(automaticReply.contains("Automatic reply"))
							&& mailStatus.getText().equalsIgnoreCase("Closed")) {

						WebElement replyAllRow = driver.findElement(
								By.xpath(readPropertiesFile.readloginPageElements("receivedEmail") + "[" + i + "]"));

						Boolean isPresent = driver
								.findElements(
										By.xpath(readPropertiesFile.readloginPageElements("replyAll") + "[" + i + "]"))
								.size() > 0;

						if (isPresent.equals(true)) {

							act.moveToElement(replyAllRow).perform();

							wait.until(ExpectedConditions.elementToBeClickable(
									By.xpath(readPropertiesFile.readloginPageElements("replyAll") + "[" + i + "]")))
									.click();

							wait.until(presenceOfElementLocated(
									By.xpath(readPropertiesFile.readloginPageElements("caseSubjectArea")))).click();

							act.sendKeys(Keys.PAGE_DOWN).perform();

							applyWaits.sleep(5000);

							driver.switchTo().frame(driver.findElement(
									By.xpath(readPropertiesFile.readloginPageElements("switchMailFrame1"))));

							applyWaits.sleep(3000);

							driver.switchTo().frame(driver.findElement(
									By.xpath(readPropertiesFile.readloginPageElements("switchMailFrame2"))));

							driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("writeMailTextArea")))
									.sendKeys(Mail_Template);

							driver.switchTo().defaultContent();

						}

						break;

					}

				}

			}

		}

	}
}
