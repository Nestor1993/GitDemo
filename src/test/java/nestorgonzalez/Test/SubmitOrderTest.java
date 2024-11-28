package nestorgonzalez.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import nestorgonzalez.AbstractComponents.AbstractComponent;
import nestorgonzalez.TestComponents.BaseTest;
import nestorgonzalez.pageobjects.Cart;
import nestorgonzalez.pageobjects.LandingPage;
import nestorgonzalez.pageobjects.OrderPage;
import nestorgonzalez.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	public String usr = "nestor@gonzalez.com";
	public String pwd = "Admin123";
	public String pr = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("usr1"), input.get("pwd1"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("pr1"));
		Cart cart = productCatalogue.goToCartPage();
		Boolean match = cart.verifyProtuctsDisplay(input.get("pr1"));
		Assert.assertTrue(match);
		cart.clickCheckoutButton();
		cart.clickOnCountry();
		String thk = cart.getConfirmationText();
		Assert.assertTrue(thk.equalsIgnoreCase("thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalogue productCatalogue = landingPage.loginApplication(usr, pwd);
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrdersDisplay(pr));

	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJasonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\nestorgonzalez\\Data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
//	return new Object[][] { { "nestor@gonzalez.com", "Admin123", "ZARA COAT 3" }, { "nestor@andres.com", "Admin123", "ADIDAS ORIGINAL"  } };
//	HashMap <String, String> map1 = new HashMap <String, String>();
//	map1.put("usr1", "nestor@gonzalez.com");
//	map1.put("pwd1", "Admin123");
//	map1.put("pr1", "ZARA COAT 3");
//	
//	HashMap <String, String> map2 = new HashMap <String, String>();
//	map2.put("usr1", "nestor@andres.com");
//	map2.put("pwd1", "Admin123");
//	map2.put("pr1", "ADIDAS ORIGINAL");
}
