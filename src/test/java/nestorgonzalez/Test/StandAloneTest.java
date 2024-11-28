package nestorgonzalez.Test;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import nestorgonzalez.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String usr = "nestor@gonzalez.com";
		String pwd = "Admin123";
		String pr = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		System.out.println("abriendo el navegador");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys(usr);
		driver.findElement(By.id("userPassword")).sendKeys(pwd);
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prd = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(pr)).findFirst()
				.orElse(null);
		prd.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#toast-container"))));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(pr));
		Assert.assertTrue(match);
		System.out.println(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".form-group input"))));
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("Co");
		Thread.sleep(3000);
		List <WebElement> con = driver.findElements(By.cssSelector(".form-group button"));
		
		for (WebElement con2 : con) {
			
			
			if(con2.getText().contentEquals("Colombia")) {
				con2.click();
				break;
			}
		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		String thk = driver.findElement(By.cssSelector("[class='hero-primary']")).getText();
		Assert.assertTrue(thk.equalsIgnoreCase("thankyou for the order."));
		
		

	}

}
