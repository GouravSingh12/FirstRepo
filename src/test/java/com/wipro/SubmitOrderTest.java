package com.wipro;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatalouge;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	//product name
	String productName = "ZARA COAT 3";		
	
	@Test(dataProvider = "getData" , groups = "Purchase")	
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		ProductCatalouge cat = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = cat.getDesiredProduct();
		//cat.addToCart(input.get("product"));
		CartPage cp = cat.addToCart(input.get("product"));
		
		// click on cart
		cat.goToCart();		
		
		// validate added product in cart
		boolean match = cp.matchTheProduct(input.get("product"));
		Assert.assertTrue(match); // Assertion for the validation
		
		// click to checkout
		CheckOutPage cout = cp.checkOut();
		
		// information for shipping and payment
		cout.selectTheCountry("india");
		ConfirmationPage conf = cout.submitOrder();
		
		
		// grab the message and validate
		String message = conf.getConfirmationMessage();
		System.out.println(message);
		Assert.assertTrue(message.equalsIgnoreCase("thankyou for the order."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory()
	{
		ProductCatalouge cat = landingPage.loginApplication("gouravsingh@gmail.com", "Gouravsingh@123");
		OrderPage orderPage = cat.goToOrderPage();
		AssertJUnit.assertTrue(orderPage.matchTheOrderProduct(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{	
		List<HashMap<String,String>> data = getDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	/*HashMap<String,String> map1 = new HashMap<>();
	map1.put("email", "gouravsingh@gmail.com");
	map1.put("password", "Gouravsingh@123");
	map1.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map2 = new HashMap<>();
	map2.put("email", "gourav@gmail.com");
	map2.put("password", "Gourav@123");
	map2.put("product", "ADIDAS ORIGINAL"); */
}
