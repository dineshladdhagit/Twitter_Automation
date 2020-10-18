Feature: Feature to Test Login functionality

 @TC01
  Scenario: Validate Login is scuccessfll with valid credentials Functionality
    Given user is on Twitter Login page
    When user enter username and password
    And click on login button
    Then user is navigated  home page