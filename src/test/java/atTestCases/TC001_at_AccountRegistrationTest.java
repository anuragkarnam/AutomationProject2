package atTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import atPageObjectClasses.HomePage;
import atPageObjectClasses.LoginAndRegisterPage;
import atPageObjectClasses.MyAccountPage;
import atTestBase.BaseClass;

public class TC001_at_AccountRegistrationTest extends BaseClass{
	@Test
	public void accountRegistration() {
		logger.info("******************************");
		logger.info(getClass().toString()+"*****test case has Started...********");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.click_myAccount();
		logger.info("*****clicked on My Account Link******");
		
		LoginAndRegisterPage lar = new LoginAndRegisterPage(driver);
		logger.info("*****entering random email and pwd******");
		String randomEmail = randomString()+"@gmail.com";
		lar.set_reg_emialAddress(randomEmail);
		System.out.println(randomEmail);
		String randomPassword = randomAlphaNumeric();
		lar.set_reg_password(randomPassword);
		System.out.println(randomPassword);
		lar.click_registerButon();
		
		MyAccountPage myp = new MyAccountPage(driver);
		logger.info("*****validating with hello msg....******");
		if(myp.getConfirmationMsg()==true) {
			System.out.println("Registration Successfull....");
			Assert.assertTrue(true);
			logger.info("*****validation succesful....******");			
		}else {
			logger.info("*****validation failed....******");			
			Assert.fail();
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info(getClass().toString()+"******Test case execution Finished...******");

	}
	
}
