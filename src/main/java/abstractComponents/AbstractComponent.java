package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By cartLink = By.xpath("//button[@routerlink = '/dashboard/cart']");
	By orderLink = By.cssSelector("[routerlink=\"/dashboard/myorders\"]");
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void goToCart()
	{
		driver.findElement(cartLink).click();
	}
	
	public OrderPage goToOrderPage()
	{
		OrderPage orderPage = new OrderPage(driver);
		driver.findElement(orderLink).click();
		return orderPage;
	}
}
