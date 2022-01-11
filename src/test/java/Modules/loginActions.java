package Modules;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginActions {

	static WebDriver driver;

	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	static By home = By.xpath("//span[contains(text(), 'Azure')]");

	static By collection = By.xpath("//span[contains(text(), 'samrawlab')]");

	public loginActions(WebDriver driver) {

		this.driver = driver;
	}

	public String homePageValidation() {

		String homepage = wait.until(presenceOfElementLocated(home)).getText();
		return homepage;
	}


	public String  collectionValidation() {
		
		String collectionName = wait.until(presenceOfElementLocated(collection)).getText();	
		return collectionName;
	}	

}
