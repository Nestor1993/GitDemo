package nestorgonzalez.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nestorgonzalez.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errormessage;
	
	

	public ProductCatalogue loginApplication(String usr, String pwd) {

		userEmail.sendKeys(usr);
		userPassword.sendKeys(pwd);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");

	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errormessage);
		String errorMessage = errormessage.getText();
		System.out.println(errorMessage);
		return errorMessage;
		

	}

}
