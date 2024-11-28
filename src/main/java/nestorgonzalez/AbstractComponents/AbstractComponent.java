package nestorgonzalez.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nestorgonzalez.pageobjects.Cart;
import nestorgonzalez.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
	WebElement cart;
	
	@FindBy(xpath="(//button[@class='btn btn-custom']) [2]")
	WebElement orers;
	
	
	public void waitForElementToAppear(By findBy) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOf(findBy));

	}

	public Cart goToCartPage() {

		cart.click();
		Cart cart = new Cart(driver);
		return cart;
	}
	
	public OrderPage goToOrdersPage() {

		orers.click();
		OrderPage ordersPage = new OrderPage(driver);
		return ordersPage;
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {

		Thread.sleep(2000);

	}

	public void waitForElementToBeClickable(WebElement clk) {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(clk));

	}
	
	

}
