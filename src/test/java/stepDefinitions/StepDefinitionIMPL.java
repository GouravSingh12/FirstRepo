package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalouge;
import testComponents.BaseTest;

public class StepDefinitionIMPL extends BaseTest {
	
	public LandingPage launchApplication;
	public ProductCatalouge cat;
	public CartPage cp;
	public ConfirmationPage conf;

	@Given("I landed on E-commerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		launchApplication = launchApplication();
	}
	
	@Given("^Login in with username (.+) and password (.+)$")
	public void Login_with_username_and_password(String username, String password)
	{
		cat = landingPage.loginApplication(username,password);
	}
	
	@When("^I added the Product (.+) to cart$")
	public void I_added_the_Product_to_cart(String productName)
	{
		List<WebElement> products = cat.getDesiredProduct();
		cp = cat.addToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		cat.goToCart();		
		
		boolean match = cp.matchTheProduct(productName);
		Assert.assertTrue(match);
		CheckOutPage cout = cp.checkOut();
		
		cout.selectTheCountry("india");
		conf = cout.submitOrder();
	}
	
    @Then("I verify {string} message")
    public void I_verify_message(String msg)
    {
    	String message = conf.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(msg));
		driver.close();
    }
    
    @Then("I verify the message {string}")
    public void I_verify_the_message(String msg)
    {
    	Assert.assertEquals(msg,landingPage.getErrorMsg());
    	driver.close();
    }
}
