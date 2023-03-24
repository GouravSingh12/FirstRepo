package com.wipro;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductCatalouge;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"errorHandling"},retryAnalyzer = Retry.class)	
	public void loginErrorValidation() throws IOException
	{
		ProductCatalouge cat = landingPage.loginApplication("gouravsgvfhgingh@gmail.com", "Gouravsingh@123");
		AssertJUnit.assertEquals("Incorrect email or password.",landingPage.getErrorMsg());
	}
	
	@Test
	public void cartProductValidation()
	{
		String productName = "ZARA COAT 3";		
		
		ProductCatalouge cat = landingPage.loginApplication("gouravsingh@gmail.com", "Gouravsingh@123");
		List<WebElement> products = cat.getDesiredProduct();
		cat.getProductByName(productName);
		CartPage cp = cat.addToCart(productName);
		
		// click on cart
		cat.goToCart();		
		
		// validate added product in cart
		boolean match = cp.matchTheProduct(productName);
		AssertJUnit.assertTrue(match); // Assertion for the validation
	}
	
}
