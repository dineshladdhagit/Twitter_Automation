@Login
Feature: Feature to Test Login functionality

	=
  Scenario: Validate Login is successfll with valid credentials
    Given user is on Twitter Login page
    When user enter username password and clicks on login button
    Then user is navigated home page
