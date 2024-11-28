package nestorgonzalez.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nestorgonzalez.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory se debe iniciar con PageFactory.initElements(driver, this);
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".form-group input")
	WebElement srch;
	

	By productsBy = By.cssSelector(".mg-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {

		// waitForElementToAppear(productsBy);
		return products;

	}

	public WebElement getProductByName(String pr) {

		WebElement prd = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(pr)).findFirst()
				.orElse(null);
		return prd;
	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prd = getProductByName(productName);
		prd.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		

		
	}

	public List<WebElement> getProductInCart() {

		return cartProducts;
	}
	
	public void paymentSection() {
		
		waitForElementToBeClickable(srch);
	}

}
