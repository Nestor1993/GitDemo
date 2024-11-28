package nestorgonzalez.Test;

import java.io.IOException;
import java.net.Authenticator;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import nestorgonzalez.AbstractComponents.AbstractComponent;
import nestorgonzalez.TestComponents.BaseTest;
import nestorgonzalez.TestComponents.Retry;
import nestorgonzalez.pageobjects.Cart;
import nestorgonzalez.pageobjects.LandingPage;
import nestorgonzalez.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
	
		
		@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
		public void loginErrorValidations() throws InterruptedException, IOException {
			
		String usr = "nestor@gonzalez.com";
		String pwd = "nestor";

		
		landingPage.loginApplication(usr, pwd);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); 

	}
		
		@Test
		public void productErrorValidation() throws InterruptedException, IOException {
			
		String usr = "nestor@gonzalez.com";
		String pwd = "Admin123";
		String pr = "ZARA COAT 3";

		
		ProductCatalogue productCatalogue = landingPage.loginApplication(usr, pwd);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(pr);
		Cart cart =productCatalogue.goToCartPage();
		Boolean match = cart.verifyProtuctsDisplay("ZARA COAT 33");
		Assert.assertFalse(match);		
		

	}

}
