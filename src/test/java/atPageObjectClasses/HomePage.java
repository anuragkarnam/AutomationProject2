package atPageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//locators


	@FindBy(xpath="//a[normalize-space()='My Account']") WebElement link_myAccount;
	@FindBy(xpath="//a[normalize-space()='AT Site']") WebElement link_aTSite;
	@FindBy(xpath="//a[normalize-space()='Demo Site']") WebElement link_demoSite;
	@FindBy(xpath="//li[@id='wpmenucartli']//a") WebElement link_cart;
	@FindBy(xpath="//a[normalize-space()='Shop']") WebElement link_shop;
	
	
	//actionMethods

	
	public void click_shop() {
		link_shop.click();
	}
	
	public void click_myAccount() {
		link_myAccount.click();
	}
	
	public void click_cart() {
		link_cart.click();
	}
	
	public void click_demosite() {
		link_demoSite.click();
	}
}
