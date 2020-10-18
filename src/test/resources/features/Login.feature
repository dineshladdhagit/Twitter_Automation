@Login
Feature: Feature to Test Login functionality


#  Scenario: Validate Login is successfll with valid credentials
#    Given user is on Twitter Login page
#    When user enter username password and clicks on login button
#    Then user is navigated home page

Scenario: Validate Login for invalid credentials
  	Given user is on Twitter Login page
  	And user enters credentials to Login 
	|userName		|	pwd			|
	|testacc18694292|	Test@12 	|
	|testacc1869492 | 	abdec@12	|
	|testacc18694292|	Test@123	|
	
	