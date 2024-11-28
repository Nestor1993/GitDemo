package nestorgonzalez.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.*;
import nestorgonzalez.TestComponents.BaseTest;
import nestorgonzalez.pageobjects.Cart;
import nestorgonzalez.pageobjects.LandingPage;
import nestorgonzalez.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public Cart cart;
		
		@Given("I landed on Ecommerce page")
		public void I_landed_on_Ecommerce_page() throws IOException{
			
			landingPage = launchApplication();
			
		}
		
		@Given("^Logged in with username (.+)and password (.+)$")
		public void Logged_in_username_and_password(String username, String password){
		
			productCatalogue = landingPage.loginApplication(username, password);
		}
		
		@When ("^I add product (.+) to Cart$")
		public void I_add_product_to_Cart(String productName) throws InterruptedException 
		{
			
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
		}
		
		
		@And("^Checkout (.+) and submit the order$")
		public void Checkout_submit_order(String productName)
		{
			cart = productCatalogue.goToCartPage();
			Boolean match = cart.verifyProtuctsDisplay(productName);
			Assert.assertTrue(match);
			cart.clickCheckoutButton();
			cart.clickOnCountry();
			
		}
		
		@Then("{string} message is displayed on ConfirmationPage")
		public void message_displayed_ConfirmationPage(String string)
		{
			String thk = cart.getConfirmationText();
			Assert.assertTrue(thk.equalsIgnoreCase(string));
		}
		
		@Then("{string} message is displayed")
		public void message_is_displayed(String string1)
		{
			Assert.assertEquals(string1, landingPage.getErrorMessage());
		}
	
}
