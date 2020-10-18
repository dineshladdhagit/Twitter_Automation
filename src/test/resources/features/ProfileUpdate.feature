@ProfileUpdate
Feature: Feature to Test updates to profile
	
  Scenario: Update profile and validate the updates
    Given user is logged in on twitter
    When user navigates to profile page and updates photo location bio and website
    Then validate profile updates are persisted