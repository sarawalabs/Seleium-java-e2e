package com.ms.dfm.modules;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import com.ms.readFiles.readPropertiesFile;

public class IsSLAMet extends setupAndTearDown{

	public static String checkSLAMet(String Case_Number) throws InterruptedException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		String status = wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.readloginPageElements("SLA")))).getText();
		
		return status;
	}

}
