package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail")
	WebElement email; 
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = ".ngx-toastr.toast-error")
	WebElement errMsg;
	
	public ProductCatalouge loginApplication(String userEmail , String pass)
	{
		email.sendKeys(userEmail);
		password.sendKeys(pass);
		submit.click();
		return new ProductCatalouge(driver);
	}
	
	public String getErrorMsg()
	{
		waitForElementToAppear(errMsg);
		return errMsg.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
