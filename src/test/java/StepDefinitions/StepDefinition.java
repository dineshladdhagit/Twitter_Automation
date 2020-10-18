package StepDefinitions;

import java.util.Map;

import org.apache.log4j.Logger;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProfilePage;
import pages.TwitterHandlePage;
import util.TestBase;

public class StepDefinition extends LoginPage {

	public static Logger log = Logger.getLogger(StepDefinition.class.getName());
	LoginPage login;
	ProfilePage profilePage;
	TwitterHandlePage twitterHandlePage;
	String scenarioName = "";

	@Before
	public void setUp(Scenario scenario) {
		scenarioName = scenario.getName();
		TestBase.loadConfiguration();
		log.info("**********************Starting execution for scenario : " + scenarioName + "**********************");
		TestBase.initializeDriver();
		login = new LoginPage();
		profilePage = new ProfilePage();
		twitterHandlePage = new TwitterHandlePage();
	}

	@After
	public void tearDown() {
		TestBase.driver.close();
		log.info("**********************Completed execution for scenario : " + scenarioName + "**********************");
	}

	// Login Feature Glue code
	@Given("user is on Twitter Login page")
	public void user_is_on_Twitter_Login_page() {
		if(login.validateLoginButtonIsDisplayed()!=true) {
			log.error("Login Button not displayed");
		}
	}

	@When("user enter username password and clicks on login button")
	public void user_enter_username_password_and_clicks_on_login_button() {
		login.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
	}

	@Then("user is navigated home page")
	public void user_is_navigated_home_page() {
		login.validateLogin();
	}

	@When("user enters credentials to Login")
	public void user_enters_credentials(DataTable usercredentials) throws Throwable {

		// To handle Data Table
		for (Map<String, String> data : usercredentials.asMaps()) {
			login(data.get("userName"), data.get("pwd"));
			// driver.findElement(By.id("log")).sendKeys(data.get("Username"));
			// driver.findElement(By.id("pwd")).sendKeys(data.get("Password"));
			// driver.findElement(By.id("login")).click();
			login.validateLogin();
		}

	}

	// Profile Page methods
	// Profile Feature Glue code
	@Given("user is logged in on twitter")
	public void user_is_logged_in_on_twitter() {
		login.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
	}

	@When("user navigates to profile page and updates photo location bio and website")
	public void user_navigates_to_profile_page_and_updates_photo_location_bio_and_website() throws Exception {
		profilePage.updateProfile();
	}

	@Then("validate profile updates are persisted")
	public void validate_profile_updates_are_persisted() {
		profilePage.validateProfileUpdates();
	}

	// TweetHandle Methods
	@When("User Searches Twitter Handle to Retrive Tweets")
	public void User_Searches_Twitter_Handle_to_Retrive_Tweets() throws Exception {
		twitterHandlePage.retrieveTweets("The Times Of India");
		//twitterHandlePage.retrieveTweets("The Times Of India");
	}

	@Then("Split each Tweets from last two hours")
	public void Split_each_Tweets_from_last_two_hours() throws Exception {
		twitterHandlePage.captureTweetsForFirstTwoHours();
	}

}
