package com.ms.dfm;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;
import com.ms.readFiles.readPropertiesFile;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import com.aventstack.extentreports.Status;
import com.ms.dfm.modules.setupAndTearDown;

//@SuppressWarnings("unused")
public class TC_01_Home_Login extends setupAndTearDown {

	@Given("User is on the login page")
	@When("User enters username and press enter")
	@Test(priority = 1, description = "Enter UserName")
	@Severity(SeverityLevel.NORMAL)
	@Description("Enter Username")
	@Story("Enter username validation")
	@Step("Enter Username")
	public void enterUsername() throws IOException {

		Reporter.log("Login Operation Strarted");

		//test = extent.createTest("enterUsername");

		//test.log(Status.INFO, "Test Started");

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * 
		 * wait.until(ExpectedConditions
		 * .elementToBeClickable(By.name(readPropertiesFile.readloginPageElements(
		 * "userName_box")))) .sendKeys(readPropertiesFile.readsetupdata("userName") +
		 * Keys.ENTER);
		 */

		//test.log(Status.INFO, "Entered the Username");

	}

	@Then("User enters Password and press enter")
	@Test(priority = 2, description = "Enter Password")
	@Severity(SeverityLevel.NORMAL)
	@Description("Enter Password")
	@Story("Enter password validation")
	@Step("Enter Password")
	public void enterPassword() throws IOException {

//		test = extent.createTest("enterPassword");
//
//		test.log(Status.INFO, "Test Started");

		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  
		
		  wait.until(ExpectedConditions
		  .elementToBeClickable(By.name(readPropertiesFile.readloginPageElements(
		  "password_box")))) .sendKeys(readPropertiesFile.readsetupdata("password") +
		  Keys.ENTER);
		 

//		test.log(Status.INFO, "Entered the Password");
	}

	@And("Finally click on SignIn button")
	@Test(priority = 3, description = "Sing in to DFM")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Click SignIn Button")
	@Story("SignIn click validation")
	@Step("Click SignIn Button")
	public void clickSignIn() throws IOException {

//		test = extent.createTest("clickSignIn");
//
//		test.log(Status.INFO, "Test Started");

		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		  
		  wait.until(presenceOfElementLocated(By.xpath(readPropertiesFile.
		  readloginPageElements("ApproveSignIn"))));
		  
		  wait.until(
		  ExpectedConditions.elementToBeClickable(By.xpath(readPropertiesFile.
		  readloginPageElements("checkBox")))) .click();
		  
		  wait.until(presenceOfElementLocated(By.id(readPropertiesFile.
		  readloginPageElements("login_button"))));
		  
		  wait.until(ExpectedConditions
		  .elementToBeClickable(By.id(readPropertiesFile.readloginPageElements(
		  "login_button")))).click();
		  
		 

//		test.log(Status.INFO, "Finished clickSignIn");
	}

	@Then("User should be able to see the homepage")
	@Test(priority = 4, description = "Verify HomePage")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify Home page")
	@Story("Home page validation")
	@Step("Verify Home page")
	public void verifyHomepage() throws IOException {

//		test = extent.createTest("verifyHomepage");
//
//		test.log(Status.INFO, "Test Started");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("homepage_icon"))));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath(readPropertiesFile.readloginPageElements("Timeline"))));

//		test.log(Status.INFO, "Verified verifyHomepage");

		Reporter.log("Login Operation Finished");
	}

}
