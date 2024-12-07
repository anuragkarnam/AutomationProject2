package atPageObjectClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAndRegisterPage extends BasePage{
	//constructor
	public LoginAndRegisterPage(WebDriver driver) {
		super(driver);
		/*
		 * The constructor public LoginAndRegisterPage(WebDriver driver) is crucial. When we create an instance of this LoginAndRegisterPage, we'll need to pass a WebDriver.
	The super(driver) is calling the parent class (BasePage) constructor. It's basically saying "Hey, take this driver and do all the initialization stuff we set up in the BasePage constructor". This ensures that:

	1)The driver gets passed up to the parent class
	2)PageFactory gets initialized for this page
	3)We set up all the foundational stuff from our BasePage
		 */
	}

	//locators
	//login-locators
	@FindBy(xpath="//input[@id='username']") WebElement txt_login_emailAddress;
	@FindBy(xpath="//input[@id='password']") WebElement txt_login_password;
	//@FindBy(xpath="//input[@id='login']") WebElement btn_login;
	@FindBy(xpath="//input[contains(@class,'woocommerce-Button') and @name='login']") WebElement btn_login;
	@FindBy(xpath="//input[@id='rememberme']") WebElement chk_rememberme;
	@FindBy(xpath="//a[normalize-space()='Lost your password?']") WebElement link_lostYourPassword;

	@FindBy(xpath="//ul[@class='woocommerce-error']") WebElement errorMSGDisplay;
	@FindBy(xpath="//li[contains(text(),'The username')]") WebElement errorUserNameMSG;
	@FindBy(xpath="//li[contains(text(),'The password you entered')]") WebElement errorPasswordMSG;

	//action methods
	//login action methods
	public void set_login_username(String login_emailID) {
		txt_login_emailAddress.sendKeys(login_emailID);
	}

	public void set_login_password(String login_pwd) {
		txt_login_password.sendKeys(login_pwd);
	}

	public void click_loginButon() {
		btn_login.click();
	}

	public void click_chk_rememberMe() {
		chk_rememberme.click();
	}

	public void click_forgetPassword() {
		link_lostYourPassword.click();
	}
	//registration-locators
	@FindBy(xpath="//input[@id='reg_email']") WebElement txt_reg_emailAddress;
	@FindBy(xpath="//input[@id='reg_password']") WebElement txt_reg_password;
	@FindBy(xpath="//input[@name='register']") WebElement btn_register;

	//registration_actionMethods
	public void set_reg_emialAddress(String reg_emailAddress) {
		txt_reg_emailAddress.sendKeys(reg_emailAddress);
	}
	public void set_reg_password(String reg_pwd) {
		txt_reg_password.sendKeys(reg_pwd);
	}
	
	public void click_registerButon() {
		btn_register.click();
	}

	/*public boolean loginvalidation() {	
		try{
			return (errorMSGDisplay.isDisplayed());
		}		
		catch(Exception e) {
			return false;
		}	
	}*/
	
	public boolean userNameError() {
		try {
			return (errorUserNameMSG.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/*public void displayLoginErrorMSG() {
		if(errorUserNameMSG.isDisplayed()==true) {
			System.out.println(errorUserNameMSG.getText());
		}
		else if(errorPasswordMSG.isDisplayed()==true) {
			System.out.println(errorPasswordMSG.getText());
		}
		else
			System.out.println("Unexpected error please try again...!");
	}
	*/
	
	//works fine...
	public String displayLoginErrorMSG() {
        try {
            // Wait for error message to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Check for username error
            try {
                WebElement usernameErrorElement = wait.until(ExpectedConditions.visibilityOf(errorUserNameMSG));
                String errorMsg = usernameErrorElement.getText();
                return errorMsg;
            } catch (Exception usernameError) {
               
            	// If username error not found, check password error
                try {
                    WebElement passwordErrorElement = wait.until(ExpectedConditions.visibilityOf(errorPasswordMSG));
                    String errorMsg = passwordErrorElement.getText();
                    return errorMsg;
                } catch (Exception passwordError) {
                    return "Unexpected error. Please try again.";
                }
            }
        } catch (Exception e) {
            return "Error retrieving error message";
        }
    }
	
	
	public boolean hasLoginError() {
	    try {
	        return errorMSGDisplay.isDisplayed() || 
	               errorUserNameMSG.isDisplayed() || 
	               errorPasswordMSG.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
}
