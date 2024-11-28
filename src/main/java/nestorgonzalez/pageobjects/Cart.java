package nestorgonzalez.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nestorgonzalez.AbstractComponents.AbstractComponent;

public class Cart extends AbstractComponent {

	WebDriver driver;

	public Cart(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".totalRow button")
	WebElement chkclk;

	@FindBy(css = ".form-group input")
	WebElement ctry;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".form-group button")
	List<WebElement> con;

	@FindBy(css = ".action__submit")
	WebElement sbmttbutton;

	@FindBy(css="[class='hero-primary']")
	WebElement confirmationtext;

	public Boolean verifyProtuctsDisplay(String pr) {

		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(pr));
		System.out.println(match);
		return match;

	}

	public void clickCheckoutButton() {

		chkclk.click();
		ctry.sendKeys("Co");
		

	}

	public void clickOnCountry() {

		for (WebElement con2 : con) {

			if (con2.getText().contentEquals("Colombia")) {
				con2.click();
				break;
			}
		}

		sbmttbutton.click();

	}

	public String getConfirmationText() {

		
		String thk = confirmationtext.getText();
		return thk;

	}

}
