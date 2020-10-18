package pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
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

	@FindBy(xpath = "//span[text()='Profile']")
	WebElement profileBtn;

	@FindBy(xpath = "//span[text()='Edit profile']")
	WebElement editProfileBtn;

	@FindBy(xpath = "//div[@aria-label='Add avatar photo' and @role='button']")
	WebElement profilePhoto;

	@FindBy(xpath = "//span[text()='Apply']")
	WebElement applyBtn;

	@FindBy(xpath = "//textarea[@name='description' and @placeholder='Add your bio']")
	WebElement profileBio;

	@FindBy(xpath = "//input[@name='location' and @placeholder='Add your location']")
	WebElement profileLocation;

	@FindBy(xpath = "//input[@name='url' and @placeholder='Add your website']")
	WebElement profileWebsite;

	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//div[@data-testid='UserDescription']")
	WebElement profileBioLbl;

	@FindBy(xpath = "//div[@data-testid='UserProfileHeader_Items']/span[1]")
	WebElement profileLocationLbl;

	@FindBy(xpath = "//a[@target='_blank' and @role='link']")
	WebElement profileWebsiteLbl;

	// initializing page object
	public ProfilePage() {
		PageFactory.initElements(driver, this);
	}

	public void updateProfile() throws Exception {
		utils.click(profileBtn, "Profile Button");
		utils.click(editProfileBtn, "Edit Profile Button");

		// To upload the photo
		/*
		 * utils.click(profilePhoto, "Profile photo Button");
		 * 
		 * String imagePath =
		 * System.getProperty("user.dir")+"src/test/resources/data/Photo.png"; String
		 * exePath = "src/test/resources/scripts/AutoIt.exe";
		 * 
		 * ProcessBuilder proc = new ProcessBuilder(exePath, imagePath);
		 * proc.redirectErrorStream(true); proc.start();
		 * 
		 * utils.click(applyBtn, "Apply Button");
		 */

		utils.clear(profileBio, "Profile Bio Field");
		utils.sendkeys(profileBio, "Profile Bio Field", prop.getProperty("BIODESCRIPTION"));

		utils.clear(profileLocation, "Profile Location Field");
		utils.sendkeys(profileLocation, "Profile Location Field", prop.getProperty("LOCATION"));

		utils.clear(profileWebsite, "Profile Website Field");
		utils.sendkeys(profileWebsite, "Profile Website Field", prop.getProperty("WEBSITE"));

		utils.click(saveBtn, "Profile Save Button");
	}

	public void validateProfileUpdates() {
		// click on profile link
		utils.click(profileBtn, "Profile Link");

		// Fetch and validate values from labels
		String profileBioLblText = utils.fetchValueFromWebElement(profileBioLbl, "Profile Bio Label");
		Assert.assertEquals(prop.getProperty("BIODESCRIPTION"), profileBioLblText);

		String profileLocationLblText = utils.fetchValueFromWebElement(profileLocationLbl, "Profile Location Label");
		Assert.assertEquals(prop.getProperty("LOCATION"), profileLocationLblText);

		String profileWebsiteLblText = utils.fetchValueFromWebElement(profileWebsiteLbl, "Profile Website Label");
		Assert.assertEquals(prop.getProperty("WEBSITE"), profileWebsiteLblText);

	}
}
