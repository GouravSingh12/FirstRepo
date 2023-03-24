package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button")
	WebElement checkOut;
	
	public boolean matchTheProduct(String productName)
	{
		boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage checkOut()
	{
		checkOut.click();
		return new CheckOutPage(driver);
	}
}
