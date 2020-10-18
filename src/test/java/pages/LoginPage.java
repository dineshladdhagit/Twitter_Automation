package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.TestBase;

public class LoginPage extends TestBase {

	public static Logger log = Logger.getLogger(LoginPage.class.getName());

	//Page Factory
	@FindBy(xpath="//span[text()='Log in']")
	WebElement loginBtnOnHome;

	@FindBy(xpath="//input[@name='session[username_or_email]' and @autocomplete='on']")
	WebElement username;

	@FindBy(xpath="//input[@name='session[password]' and @autocomplete='on']")
	WebElement password;

	@FindBy(xpath="(//span[text()='Log in'])[2]")
	WebElement loginBtn;

	@FindBy(xpath="//h2[@dir='auto' and @role='heading']")
	WebElement homePageheaderLbl;

	@FindBy(xpath="//span[text()='Home']")
	WebElement homeBtn;

	//@FindBy(xpath="(//span[text()='The username and password you entered did not match our records. Please double-check and try again.'])[1]")
	@FindBy(xpath="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[1]/span")
	WebElement LoginErrorMsgLbL;

	//initializing page object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Login method
	public void login(String userName, String pwd) {
		try{
			driver.navigate().refresh();
			utils.click(loginBtnOnHome, "Login Button on Home Page");
		}catch(StaleElementReferenceException E){
			utils.click(loginBtnOnHome, "Login Button on Home Page");
		}
		//username.clear();
		utils.clear(username, "Phone, email or username field");
		utils.sendkeys(username, "Phone, email or username field", userName);
		utils.sendkeys(password, "Password Field", pwd);
		password.sendKeys(Keys.TAB);
		password.sendKeys(Keys.ENTER);
	}

	public boolean validateLogin() {
		if(homeBtn.isDisplayed()) {
			log.info("Login Successfull");
			return true;
		}else {
			log.error("Login Failed! Home Page not displayed");
			return false;
		}
	}	
	public boolean validateLogin(String isValid) throws Exception {
		//Thread.sleep(5000);
		if(LoginErrorMsgLbL.isDisplayed() && isValid.equals("inValid")) {
			//Assert.assertTrue(true);
			log.info("Pass: Could NOT loging with Invalid Credentials");
			return true;
		}else {
			//Assert.assertFalse(false);
			log.error("Login Failed! Home Page not displayed");
			return false;			
		}
	}


	public boolean validateLoginButtonIsDisplayed() {
		driver.navigate().refresh();
		try{
			if(loginBtnOnHome.isDisplayed()) {
				log.info("Login Button on Home Page is Displayed");
				return true;
			}else {
				log.warn("Login Button on Home Page NOT Displayed");
				return false;
			}
		}catch(StaleElementReferenceException E){
			if(loginBtnOnHome.isDisplayed()) {
				log.info("Login Button on Home Page is Displayed");
				return true;
			}else {
				log.warn("Login Button on Home Page NOT Displayed");
				return false;
			}
		}
	}


}
