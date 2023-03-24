package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder = 'Select Country']")
	WebElement country;
	@FindBy(xpath = "//section/button[2]")
	WebElement india;
	@FindBy(css = ".actions a")
	WebElement submit;
	
	By countryBy = By.cssSelector(".ta-item");
	
	public void selectTheCountry(String countryName)
	{
		country.sendKeys(countryName);
		waitForElementToAppear(countryBy);
		india.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
}
