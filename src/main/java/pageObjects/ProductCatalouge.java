package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By animation = By.cssSelector(".ng-animating");
	
	public List<WebElement> getDesiredProduct()
	{
		waitForElementToAppear(By.cssSelector(".card-body h5 b"));
		List<WebElement> products = driver.findElements(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getDesiredProduct().stream().filter(x->x.getText().contains(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public CartPage addToCart(String productName)
	{
		getProductByName(productName).findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(animation);
		return new CartPage(driver);
	}
}
