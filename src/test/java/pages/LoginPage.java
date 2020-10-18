package pages;

import org.apache.log4j.Logger;
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
			driver.navigate().refresh();			
			utils.click(loginBtnOnHome, "Login Button on Home Page");
		}
		utils.sendKeys(username, "Username", userName);
		utils.sendKeys(password, "Password", pwd);
		password.sendKeys(Keys.TAB);
		password.sendKeys(Keys.ENTER);
		//Javascript to be handled
		//utils.click(loginBtn, "Login Button on Home Page");
		
	}
	
	

}
