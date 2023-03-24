@tag
Feature: Purchase the order from E-commerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on E-commerce page

  @Regression
  Scenario Outline: Positive test case for Submit Order
    Given Login in with username <username> and password <password>
    When I added the Product <productName> to cart
    And Checkout <productName> and submit the order
    Then I verify "THANKYOU FOR THE ORDER." message

    Examples: 
      | username                  | password               | productName |
      | gouravsingh@gmail.com     |     Gouravsingh@123 	 | ZARA COAT 3 |
      
