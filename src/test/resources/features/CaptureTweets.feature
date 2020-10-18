@CaptureTweets
Feature: Feature to Capture and Split Tweets
	
  Scenario: Caputre tweets from a Twitter Handle and split the tweets
    Given user is logged in on twitter
    When User Searches Twitter Handle to Retrive Tweets
    Then Split each Tweets from last two hours