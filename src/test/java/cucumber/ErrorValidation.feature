@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Checking email and password
  	Given I landed on E-commerce page
    And Login in with username <username> and password <password>
    Then I verify the message "Incorrect email or password."

     Examples: 
      | username                  | password         |
      | gouravsingh@gmail.com     |     singh@123 	 |
