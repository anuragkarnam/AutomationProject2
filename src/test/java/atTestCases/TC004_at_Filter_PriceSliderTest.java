package atTestCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import atPageObjectClasses.HomePage;
import atPageObjectClasses.ShopPage;
import atTestBase.BaseClass;

public class TC004_at_Filter_PriceSliderTest extends BaseClass{

	@Test
	public void priceSliderTest() {
		logger.info("******************************");
		logger.info(getClass().toString() + "*****test case has Started...********");
		try {
			HomePage hp = new HomePage(driver);
			logger.info("*******homepage opened...**********");
			hp.click_shop();
			logger.info("*******shop link clicked...**********");

			ShopPage sp = new ShopPage(driver);
			if(sp.shopPageValidationTXT()) {
				logger.info("*******shop page validation successful...**********");
				sp.setPriceRangeByOffset(250, 400);//change to any value you want to set or you can use scanner.next() by invoking scanner object
				//do not choose values less than 150 and more than 500 since it will throw error. check in ShopPage.java class


				int start_price=Integer.parseInt(sp.txt_sliderFromPrice());
				int upto_price=Integer.parseInt(sp.txt_sliderToPrice());

				//this below if statement checks if + or - 1 value since slider does not work accurate during automation
				//always add -1 number and +1 number to the start_price and upto_price
				//if(sp.txt_sliderFromPrice().equals("250") && sp.txt_sliderToPrice().equals("400")) {//this works only when the slider is accurate

				if(start_price==249 || start_price==250 || start_price==251 && upto_price==399 || upto_price==400 || upto_price==401) {
					logger.info("*******user values matched with slider...**********");
					System.out.println("Slider is set according to the price ranges selected by the user...");
					logger.info("*******test passed********");
					Assert.assertTrue(true);
				}

				else {
					System.out.println("slider error...!");
					logger.info("*******user values not matched with slider...**********");
					logger.info("*******test failed**********");
					Assert.assertTrue(false);
				}
			}
		}catch(Exception e) {
			logger.error("Exception in login test", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

		logger.info("******Login Test completed******");

	}


}
