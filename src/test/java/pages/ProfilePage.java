package pages;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.TestBase;

public class ProfilePage extends TestBase {
	
	public static Logger log = Logger.getLogger(ProfilePage.class.getName());
	
	@FindBy(xpath="//span[text()='Profile']")
	WebElement profileBtn;
	
	@FindBy(xpath="//span[text()='Edit profile']")
	WebElement editProfileBtn;
	
	//@FindBy(xpath="(//span[text()='Name']/ancestor::label/../preceding-sibling::div/div/div/div[3]/div/div/*[name()=\"svg\"])[2]")
	@FindBy(xpath="//div[@aria-label='Add avatar photo' and @role='button']")
	WebElement profilePhoto;
	
	@FindBy(xpath="//span[text()='Apply']")
	WebElement applyBtn;
	
	@FindBy(xpath="//span[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@aria-label='Search query']")
	WebElement searchTweets;
	
	@FindBy(xpath="//span[text()='The Times Of India']")
	WebElement searchedTweetHandle;
	
	LinkedHashSet<String> setOfTweets = new LinkedHashSet<>();
	
	//initializing page object
	public ProfilePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void updateProfile() throws Exception{
		utils.click(profileBtn, "Profile Button");
		utils.click(editProfileBtn, "Edit Profile Button");
		utils.click(profilePhoto, "Profile photo Button");
		
		String imagePath = System.getProperty("user.dir")+"\\src\\test\\resources\\data\\Photo.png";
		String exePath = "src\\test\\resources\\scripts\\AutoIt.exe";
		
		ProcessBuilder proc = new ProcessBuilder(exePath, imagePath);
		proc.redirectErrorStream(true);
		proc.start();
		
		utils.click(applyBtn, "Apply Button");
		utils.click(saveBtn, "Save Button");
	}
	
	public void retrieveTweets(String searchTweetHandle) throws Exception{
		utils.sendKeys(searchTweets, "Search Tweets", searchTweetHandle);
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(searchedTweetHandle).perform();
		utils.click(searchedTweetHandle,searchTweetHandle);
		/*captureTweetsForFirstTwoHours();*/
	}
	
	public void captureTweetsForFirstTwoHours() throws Exception{
		String time = "";
		int tweetCounter = 0;
		while(!time.equals("3h")){
			List<WebElement> list = driver.findElements(By.xpath("//div[contains(@aria-label,'Timeline: The Times Of India')]/div/div//time"));
			for(int iCount=1;iCount<list.size();iCount++){
				if(!list.get(iCount).getText().equals("3h")){
					try{
						WebElement we = driver.findElement(By.xpath("(//div[contains(@aria-label,'Timeline: The Times Of India')]/div/div//time)["+iCount+"]/"
								+ "following::div[@class='css-1dbjc4n'][1]/div[1]/div"));
						setOfTweets.add(we.getText().trim());
						}catch(NoSuchElementException E){
						break;
					}
				}
			}
			
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",list.get(list.size()-1));
			Thread.sleep(2000);
			WebElement ele = driver.findElement(By.xpath("//div[contains(@aria-label,'Timeline: The Times Of India')]/div/div//time"));
			if(ele.getText().equals("3h")){
				time = "3h";
			}
		}
		log.info("Total tweets are "+setOfTweets.size());
		
		
		for(String string : setOfTweets){
			tweetCounter++;
			splitString(string,tweetCounter);
		}
	}
	
	void splitString(String str,int count){
		int beginIndex = 0;
		int endIndex = 0;
		float length = str.length();
		int counter = (int) Math.ceil(length/50);
		log.info("*******************Tweet Number#"+count+"********************");
		if(str.length()>50){
			for (int iCount = 1; iCount <= counter; iCount++) {
				String newString = "";
				endIndex += 49;
				if(iCount==counter){
					newString = str.substring(beginIndex);
				}else{
					newString = str.substring(beginIndex, endIndex);
				}
				log.info(iCount+"\\"+counter + " "+newString);
				beginIndex = endIndex+1;
			}
		}else{
			log.info("1\1 "+str);
		}
	}
	
	
}
