package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jdk.internal.jline.internal.Log;
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
	
	//initializing page object
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Login method
		public void login(String userName, String pwd) {
			try{
				utils.click(loginBtnOnHome, "Login Button on Home Page");
			}catch(StaleElementReferenceException E){
				utils.click(loginBtnOnHome, "Login Button on Home Page");
			}
			utils.sendkeys(username, "Phone, email or username field", userName);
			utils.sendkeys(password, "Password Field", pwd);
			//utils.sendkeys(username, userName, "Phone, email or username field");
			//utils.sendkeys(password, pwd, "Password Field");		
			//username.sendKeys(userName); 
			//password.sendKeys(pwd);
			password.sendKeys(Keys.TAB);
			password.sendKeys(Keys.ENTER);
			//Javascript to be handled

		}

		public boolean validateLogin() {
			if(homeBtn.isDisplayed()) {
				return true;
			}else {
				Log.error("Login Failed! Home Page not displayed");
				return false;
			}
				
/*			String homePageHeaderLblText = utils.fetchValueFromWebElement(homePageheaderLbl, "Home Page Header Label");
			log.info("Home Page header value is :"+ homePageHeaderLblText.trim());
			if(homePageHeaderLblText.trim()=="Home") {
				log.info("Login is successful!");
			}
			else {
				log.warn("Login Failed: Please check username and password");
				System.out.println("Oops! login failed. Please check username and password");
			}*/
		
			}
		
		public boolean validateLoginButtonIsDisplayed() {
			if(loginBtnOnHome.isDisplayed()) {
				return true;
			}else {
				log.warn("Login Button on Home Page NOT Displayed");
				return false;
			}
		}
	

}
