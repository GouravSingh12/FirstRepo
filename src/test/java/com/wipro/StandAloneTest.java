package com.wipro;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Website url
		driver.get("https://rahulshettyacademy.com/client/");
		
		// product name
		String productName = "ZARA COAT 3";
		
		// Login
		driver.findElement(By.id("userEmail")).sendKeys("gouravsingh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Gouravsingh@123");
		driver.findElement(By.id("login")).click();
		
		// wait for products to load
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body h5 b")));
		
		//list of products 
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body h5 b"));
		WebElement prod = products.stream().filter(x->x.getText().contains(productName)).findFirst().orElse(null);
		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		
		// wait for to appear added to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		// wait for loading to disappear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		// click on cart
		driver.findElement(By.xpath("//button[@routerlink = '/dashboard/cart']")).click();
		
		// validate added product in cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match); // Assertion for the validation
		
		// click to checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		// information for shipping and payment
		driver.findElement(By.cssSelector("[placeholder = 'Select Country']")).sendKeys("india");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		driver.findElement(By.xpath("//section/button[2]")).click();
		driver.findElement(By.cssSelector(".actions a")).click();
		
		// grab the message and validate
		String message = driver.findElement(By.cssSelector("h1")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("thankyou for the order."));
		
		Thread.sleep(3000);
		driver.close();
		
	}
}
