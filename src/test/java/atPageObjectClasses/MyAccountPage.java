package atPageObjectClasses;
//link for MyAccountPage--> https://practice.automationtesting.in/my-account/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);//assigning this constructor driver instance to super constructor driver
	}

	//locators
	@FindBy(xpath="//p[contains(text(),'Hello')]") WebElement validationMSG;
	@FindBy(xpath="//a[normalize-space()='Dashboard']") WebElement link_dashboard;
	@FindBy(xpath="//a[normalize-space()='Orders']") WebElement link_orders;
	@FindBy(xpath="//a[normalize-space()='Downloads']") WebElement link_downloads;
	@FindBy(xpath="//a[normalize-space()='Addresses']") WebElement link_addresses;
	@FindBy(xpath="//a[normalize-space()='Account Details']") WebElement link_accountDetails;
	@FindBy(xpath="//a[normalize-space()='Logout']") WebElement link_logout;
	
	@FindBy(xpath="//a[contains(text(),'Automation Practice Site')]") WebElement copyright;
	@FindBy(xpath="//img[@alt='Site Logo']") WebElement siteLogo;
	
	@FindBy(xpath="//i[@class='fa fa-twitter']") WebElement link_twitter;
	@FindBy(xpath="//i[@class='fa fa-facebook']") WebElement link_facebook;
	@FindBy(xpath="//i[@class='fa fa-google-plus']") WebElement link_googleplus;
	@FindBy(xpath="//i[@class='fa fa-youtube']") WebElement link_youTube;
	@FindBy(xpath="//i[@class='fa fa-linkedin']") WebElement link_linkedIn;

	@FindBy(xpath="//a[normalize-space()='recent orders']") WebElement link_recentOrders;
	@FindBy(xpath="//a[normalize-space()='shipping and billing addresses']") WebElement link_shippingAndBillingAddresses;
	@FindBy(xpath="//a[normalize-space()='edit your password and account details']") WebElement link_editYourPasswordAndAccount;

	//actionMethods
	public boolean getConfirmationMsg() {
		try{
			return (validationMSG.isDisplayed());//since you might login or not put this validationMSG in try catch block.
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		link_logout.click();
		System.out.println("Logout Successful...!");
	}
}
