package atTestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import atPageObjectClasses.HomePage;
import atPageObjectClasses.ShopPage;
import atTestBase.BaseClass;

public class TC005_at_SliderFilterProductPriceMatchTest extends BaseClass {

	@Test
	public void productFilteration_UsingSliderTest() {

		try {

			logger.info("*********Executing testCase4...**************");
			HomePage hp = new HomePage(driver);
			logger.info("*******homepage opened...**********");
			hp.click_shop();
			logger.info("*******shop link clicked...**********");

			ShopPage sp = new ShopPage(driver);
			if(sp.shopPageValidationTXT()) {
				logger.info("*******shop page validation successful...**********");
				sp.setPriceRangeByOffset(300, 400);//change to any value you want to set or you can use scanner.next() by invoking scanner object
				//do not choose values less than 150 and more than 500 since it will throw error. check in ShopPage.java class

				int start_price=Integer.parseInt(sp.txt_sliderFromPrice());
				int upto_price=Integer.parseInt(sp.txt_sliderToPrice());

				//if(sp.txt_sliderFromPrice().equals("250") && sp.txt_sliderToPrice().equals("400")) {
				if(start_price==299 || start_price==300 || start_price==301 && upto_price==399 || upto_price==400 || upto_price==401) {
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


			if(sp.checkProductCatalogAsPerSliderRange()) {
				Thread.sleep(3000);
				logger.info("*********products are within range...**********");
				logger.info("*******test passed...!*********");
				Assert.assertTrue(true);
			}else {
				logger.info("*********products are out of range...!**********");
				Assert.assertTrue(false);
			}

		}
		catch(Exception e) {
			logger.error("Exception in login test", e);
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}

	}

}
