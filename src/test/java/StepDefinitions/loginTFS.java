package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Modules.loginActions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.time.Duration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;


public class loginTFS {
	
	static WebDriver driver=null;
	
	static loginActions actions = new loginActions(driver);


	@Given("The user Launches the Browser")
	@Test(priority = 1, description = "Launch the Edge Browser")
	public void launchBrowser() throws IOException {
		
		WebDriverManager.edgedriver().setup();
		
		driver = new EdgeDriver();
		
		driver.manage().window().maximize();

	}


	@When("User enters the url in the browser")
	@Test(priority = 2, description = "Enter the TFS URL in the browser")
	public void enterUrl() throws IOException {

		driver.get("https://minint-dvts2ir/samrawlab");

	}
	
	
	@And("^A homepage must be displayed with the name (.*)$")
	@Test(priority = 3, description = "Verify text Azure DevOps is displaed on the homepage")
	public void verifyHomepage(String HomePage) throws IOException {
		
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		String HomePageValue = actions.homePageValidation();
		
		Assert.assertEquals(HomePageValue,HomePage);
	}
	

	@And("^The collection name (.*) is also visible$")
	@Test(priority = 4, description = "Verify collection name 'samrawlab' is also visible")
	@Step("Verify the collection name is dispayed")
	public void verifyTheCollection(String CollectionName) throws IOException {
		

		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		String collectionNameValue = actions.collectionValidation();

		Assert.assertEquals(collectionNameValue,CollectionName);
	}
	
	@Then("Close the browser")
	@Test(priority=5, description="Finally close the browser")
	@Step("Verify that the browser is closed")
	public void closeBrowser() throws IOException{
		
		driver.close();
		driver.quit();
	}

}
