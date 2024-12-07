package atTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import atPageObjectClasses.HomePage;
import atPageObjectClasses.LoginAndRegisterPage;
import atPageObjectClasses.MyAccountPage;
import atTestBase.BaseClass;

public class TC002_at_AccountLoginTest extends BaseClass {
	
	/*public void accountLoginVerification() {
		logger.info("******************************");
		logger.info(getClass().toString()+"*****test case has Started...********");

		try {
			HomePage hp = new HomePage(driver);
			hp.click_myAccount();
			logger.info("*****clicked on My Account Link******");

			LoginAndRegisterPage lar = new LoginAndRegisterPage(driver);
			logger.info("*******entering login details********");
			lar.set_login_username(p.getProperty("email"));
			lar.set_login_password(p.getProperty("password"));
			//lar.set_login_password("iyxsic.īy.csilyc");

			lar.click_chk_rememberMe();
			lar.click_loginButon();
//----
			MyAccountPage myp = new MyAccountPage(driver);
			if(myp.getConfirmationMsg()==true) {
				System.out.println("Login successful...");
				logger.info("******login successful*******");
				Assert.assertTrue(true);
			}
			else if(lar.loginvalidation()) {
				String errorMessage = lar.displayLoginErrorMSG();

				// Assert specific error message or just assert that an error occurred
				Assert.assertFalse(errorMessage.isEmpty(), "Login should fail with an error message");

				logger.info("******login failed: " + errorMessage + "********");
				System.out.println("login failed..."+errorMessage);
			}
			else {
				logger.info("******login failed...********");
				Assert.fail("Login failed without a clear error message");
			}
		} catch(Exception e) {
			logger.error("Exception in login test", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		logger.info("******Login Test completed******");
	}
}*/
//---
/*MyAccountPage myp = new MyAccountPage(driver);
		if(myp.getConfirmationMsg()==true) {
			System.out.println("login successfull...");
			logger.info("******login succesful*******");
			Assert.assertTrue(true);
		}

		else if(lar.loginvalidation()==true) {
			System.out.println("login failed...!");
			lar.displayLoginErrorMSG();
			logger.info("******login failed...********");
			Assert.assertTrue(false);
		}
		else {
			//System.out.println("login failed...!");
			logger.info("******login failed...********");
			Assert.assertTrue(false);
		}


		}catch(Exception e) {
			e.getMessage();
			Assert.fail();
		}
		logger.info("******Login Test completed******");
	}
}
 */
	@Test
	public void accountLoginVerification() {
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
	            Assert.assertTrue(true);
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
	    } 
	    catch (Exception e) {
	        logger.error("Exception in login test", e);
	        Assert.fail("Test failed due to exception: " + e.getMessage());
	    }
	    
	    logger.info("******Login Test completed******");
	}
	}