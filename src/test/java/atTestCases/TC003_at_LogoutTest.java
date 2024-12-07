package atTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import atPageObjectClasses.HomePage;
import atPageObjectClasses.LoginAndRegisterPage;
import atPageObjectClasses.MyAccountPage;
import atTestBase.BaseClass;

public class TC003_at_LogoutTest extends BaseClass {
	@Test
	public void logoutTest() {

		logger.info("******************************");
	    logger.info(getClass().toString() + "*****test case has Started...********");
	    
		try {
			HomePage hp = new HomePage(driver);
			hp.click_myAccount();
			logger.info("*****clicked on My Account Link******");

			LoginAndRegisterPage lar = new LoginAndRegisterPage(driver);
			logger.info("*******entering login details********");

			lar.set_login_username(p.getProperty("email"));
			// lar.set_login_password("incorrect_password"); // Use an incorrect password for testing
			lar.set_login_password(p.getProperty("password"));

			lar.click_chk_rememberMe();
			lar.click_loginButon();

			MyAccountPage myp = new MyAccountPage(driver);

			if (myp.getConfirmationMsg()) {
				System.out.println("Login successful...");
				logger.info("******login successful*******");

				myp.clickLogout();
				if(driver.getCurrentUrl().equals("https://practice.automationtesting.in/my-account/")) {
					logger.info("*******logout successfull...*********");
					Assert.assertTrue(true);
				}
			} 
			else if (lar.hasLoginError()) {
				String errorMessage = lar.displayLoginErrorMSG();

				System.out.println("Login failed: " + errorMessage);
				logger.info("******login failed: " + errorMessage + "********");

				// Assert that the error message is not empty
				Assert.assertFalse(errorMessage.isEmpty(), "Login should fail with an error message");
			} 
			else {
				logger.info("******Unexpected login state********");
				Assert.fail("Unexpected login state");
			}
		} catch (Exception e) {
			logger.error("Exception in login test", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		logger.info("******Login Test completed******");
	}


}



