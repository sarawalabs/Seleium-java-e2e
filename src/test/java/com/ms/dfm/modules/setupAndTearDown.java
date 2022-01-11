package com.ms.dfm.modules;

import com.ms.readFiles.CleanupExcel;
import com.ms.readFiles.readPropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class setupAndTearDown extends CleanupExcel{

	public static WebDriver driver;

	@BeforeTest
	public void choseBrowser() throws IOException {

		Reporter.log("===== Browser Configuration started ======", true);

		if (readPropertiesFile.readsetupdata("browserName").equalsIgnoreCase("Edge")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			cap.setBrowserName(BrowserType.EDGE);
			
			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver();
			
			/*To run the tests on Docker		
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), cap); */

			driver.manage().window().maximize();

			driver.get(readPropertiesFile.readsetupdata("url"));

		}

		Reporter.log("===== Browser Configuration Ended ======", true);
	}

	@AfterTest(alwaysRun = true)
	public void closeBrowser() throws IOException {
		
//		driver.quit();

		Reporter.log("===== App Closed ======", true);
	}
}
