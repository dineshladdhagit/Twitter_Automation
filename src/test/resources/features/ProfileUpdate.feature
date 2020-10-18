Feature: Feature to validate profile update

	@TC02 @Profile
  Scenario: Updates profile photo
    Given user is logged in on twitter
#    Then user navigates to Profile page
#    When user click on edit profile
#    Then user Click on photo
#    Then user Browses a photo
#    Then click on apply

   Scenario: Updates BIO field
   	Given user is logged in on twitter
    Then user navigates to Profile page
    When user click on edit profile
    Then user updates Bio field
    Then click on save
   
   Scenario: Updates Location field
   	Given user is logged in on twitter
    Then user navigates to Profile page
    When user click on edit profile
    Then user updates location field
    Then click on save
    
   Scenario: Updates website field is updated
   	Given user is logged in on twitter
    Then user navigates to Profile page
    When user click on edit profile
    Then user updates website field
    Then click on save
   
   Scenario: validate the profile page is updated correctly
   	Given user is logged in on twitter
    When user navigates to Profile page
    Then user captures bio informaiton and validates with expected
    Then user captures Location information and validates with expected
    Then user captrues website information and validates with expected
 
   