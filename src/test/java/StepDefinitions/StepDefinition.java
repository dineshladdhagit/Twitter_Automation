package StepDefinitions;


import org.apache.log4j.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProfilePage;
import util.TestBase;

public class StepDefinition extends LoginPage {
	
	public static Logger log = Logger.getLogger(StepDefinition.class.getName());
	LoginPage login;
	ProfilePage profilePage;
	String scenarioName = "";
	
	@Before
	public void setUp(Scenario scenario){
		scenarioName = scenario.getName();
		TestBase.loadConfiguration();
		log.info("**********************Starting execution for scenario : "+scenarioName+"**********************");
		TestBase.initializeDriver();
		login = new LoginPage();
		profilePage = new ProfilePage();
	}
	
	@After
	public void tearDown(){
		TestBase.driver.close();
		log.info("**********************Completed execution for scenario : "+scenarioName+"**********************");
	}
	
	@Given("user is on Twitter Login page")
	public void user_is_on_Twitter_Login_page() {
		System.out.println("USER IS ON TWITTER LOGIN PAGE");
		//Pending - Login page validation
		
	}

	@When("user enter username and password")
	public void user_enter_username_and_password() {
		System.out.println("USER ENTERED USERNAME AND PASSWORD");
		login.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
	}

	@And("click on login button")
	public void click_on_login_button() {
		System.out.println("USER CLICK ON LOGIN BUTTON");
	}

	@Then("user is navigated  home page")
	public void user_is_navigated_home_page() {
		System.out.println("USER IS NAVIGATED TO HOME PAGE");
	}
	
	//Profile Page methods
	@Given("user is logged in on twitter")
	public void user_is_logged_in_on_twitter() throws Exception {
		login.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
		//Update Profile
		profilePage.updateProfile();
		
		//Tweet scenario
		/*profilePage.retrieveTweets("The Times Of India");
		profilePage.captureTweetsForFirstTwoHours();*/
		
	}
		
	
	
	

}
