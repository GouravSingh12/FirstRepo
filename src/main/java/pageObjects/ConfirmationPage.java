package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h1")
	WebElement message;
	
	public String getConfirmationMessage()
	{
		String text = message.getText();
		return text;
	}
}
