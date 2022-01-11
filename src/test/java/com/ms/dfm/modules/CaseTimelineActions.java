package com.ms.dfm.modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ms.readFiles.readPropertiesFile;

public class CaseTimelineActions extends setupAndTearDown {

	public static ArrayList<String> mailTimeline( String cxName) throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		Actions act = new Actions(driver);

		ArrayList<String> latestMail = new ArrayList<String>();

		ArrayList<String> CaseNotes = new ArrayList<String>();

		scrollDownUntil.scrollDown("caseNotesList");
		
		applyFilter.applyFilters("notes_checkbox", "uncheck");
		
		wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("caseNotesList"))));

		// Start---- reusable code used in the plusButtonAction class
		int listCount = driver.findElements(By.xpath(readPropertiesFile.readloginPageElements("caseNotesList"))).size();

		System.out.println("the list count is " + listCount);

		for (int i = 1; i <= listCount; i++) {

			latestMail.clear();

			WebElement firstTimeline = driver
					.findElement(By.xpath(readPropertiesFile.readloginPageElements("timeline_record") + "[" + i + "]"));

			List<WebElement> FirstMail = firstTimeline
					.findElements(By.xpath(readPropertiesFile.readloginPageElements("childItem")));

			for (WebElement mail : FirstMail) {

				String mailText = mail.getText();

				latestMail.add(mailText);

			}
			
			String [] emailFrom =  (latestMail.get(0)).split("\n", 2);
			
			String [] checkMailType = (latestMail.get(1)).split(":",0);
			
		// End ---- reusable code used in the plusButtonAction class
			

			if ( checkMailType[0] != "Automatic reply" && emailFrom[1].contains("Email from Sandeep Rawat")) {
				
				latestMail.clear();
				
				System.out.println("The Latest Email is from Sandeep, the case owner, No response from the customer so far, "
						+ "check the latest case notes now");
				
				applyFilter.applyFilters("notes_checkbox", "check");
				
				applyFilter.applyFilters("activites_checkbox", "uncheck");
				
				wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath(readPropertiesFile.readloginPageElements("latest_case_note_click")))).click();

				applyWaits.sleep(3000);

				act.sendKeys(Keys.PAGE_DOWN).perform();

				driver.switchTo().frame(
						driver.findElement(By.xpath(readPropertiesFile.readloginPageElements("caseNotesFrame"))));

				wait.until(
						presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("caseNotes_div"))));

				WebElement caseNotes_div = driver
						.findElement(By.xpath(readPropertiesFile.readloginPageElements("caseNotes_div")));

				List<WebElement> caseNotes = caseNotes_div
						.findElements(By.xpath(readPropertiesFile.readloginPageElements("childItem")));

				for (WebElement CN : caseNotes) {

					String latest_notes = CN.getText();

					CaseNotes.add(latest_notes);
					
				}

				break;

			}
			
			else if(checkMailType[0] != "Automatic reply" && emailFrom[1].contains("Email from "+ cxName)) {
				// sometimes we see first name 
					System.out.println("Latest email is from the customer with new updates" + checkMailType[0]);
					
					String mailContent = latestMail.get(2);
					
					String [] newString = mailContent.split(cxName, 2);
	
					String updated = newString[0];
					
					CaseNotes.add(updated);
					
					break;
			}
			
			
			else if( (checkMailType[0]!= "Automatic reply" && !(emailFrom[1].contains("Email from "+ cxName)))
					|| (checkMailType[0]!= "Automatic reply" && !(emailFrom[1].contains("Email from Sandeep Rawat"))))  
			
			{
				// sometimes we see first name 
					System.out.println("Latest email is from the customer with new updates" + checkMailType[0]);
					
					String mailContent = latestMail.get(2);
					
					String [] newString = mailContent.split(cxName, 2);
	
					String updated = newString[0];
					
					CaseNotes.add(updated);
					
					break;
			}

		}

		return CaseNotes;
	}

}
