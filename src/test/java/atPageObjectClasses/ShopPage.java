package atPageObjectClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ShopPage extends BasePage {

	//constructor
	public ShopPage(WebDriver driver) {
		super(driver); 
	}

	//locators

	@FindBy(xpath="//nav[@class='woocommerce-breadcrumb']") WebElement txt_shopPageValidation;

	@FindBy(xpath="//button[normalize-space()='Filter']") WebElement btn_filter;
	@FindBy(xpath="//span[@class='from']") WebElement txt_fromPrice;
	@FindBy(xpath="//span[@class='to']") WebElement txt_toPrice;

	@FindBy(xpath="//div[contains(@class,'price_slider')]/span[1]") WebElement slider_fromPosition;
	@FindBy(xpath="//div[contains(@class,'price_slider')]/span[2]") WebElement slider_toPosition;


	@FindBy(xpath="//ul[@class='products masonry-done']//li") List<WebElement> product_catalog;
	@FindBy(xpath="//a//span[contains(@class,'price')]/span | //span[contains(@class,'price')]//ins//span[contains(@class,'woocommerce-Price-amount amount')]")
	List<WebElement> dynamic_Allprod_prices;
	//actionMethods.....	
	//it will check for the page validation message and returns true if found else false
	public boolean shopPageValidationTXT() {
		try {
			return(txt_shopPageValidation.isDisplayed());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


	//it will set the slider according to the prices gives by the user
	public void setPriceRangeByOffset(int minPrice, int maxPrice) {
		// Validate input prices are within the allowed range
		if (minPrice < 150 || minPrice > 500 || maxPrice < 150 || maxPrice > 500 || minPrice >= maxPrice) {
			throw new IllegalArgumentException("Price range must be between 150 and 500, and minPrice must be less than maxPrice");
		}

		try {
			// Wait for sliders to be present
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(slider_fromPosition));
			wait.until(ExpectedConditions.elementToBeClickable(slider_toPosition));

			// Create Actions instance
			Actions actions = new Actions(driver);

			int totalPriceRange = 500 - 150;

			// Calculate price to offset conversion factor
			double priceToOffsetRatio = 200.0 / totalPriceRange;

			// Calculate offsets for min and max prices
			int minPriceOffset = (int)(Math.ceil((minPrice - 150) * priceToOffsetRatio));
			int maxPriceOffset = (int)(Math.ceil((500 - maxPrice) * priceToOffsetRatio));//Math.ceil(int)--it will round of the value to next number i.e, 289.55 to 290

			// Move from slider (left slider)
			actions.dragAndDropBy(slider_fromPosition, minPriceOffset, 0).perform();

			// Move to slider (right slider)
			actions.dragAndDropBy(slider_toPosition, -maxPriceOffset, 0).perform();

			// Click filter button to apply the range
			btn_filter.click();

		} catch (Exception e) {
			System.out.println("Error in setting price range: " + e.getMessage());
			Assert.fail("Failed to set price range: " + e.getMessage());
		}
	}

	//it will return the slider "from price" as a string
	public String txt_sliderFromPrice() {
		String final_fromPrice=txt_fromPrice.getText().replace("₹", "").replaceAll(" ", "");//trimming the rupee symbol and empty space if any present
		return final_fromPrice;
	}

	//it will return the slider "to price" as a string
	public String txt_sliderToPrice() {
		String final_toPrice=txt_toPrice.getText().replace("₹", "").replaceAll(" ", "");//trimming the rupee symbol and empty space if any present
		return final_toPrice;
	}

	//it will check product catalog whether procucts are displaying within price range...
	public boolean checkProductCatalogAsPerSliderRange() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='products masonry-done']")));

			// Get the from and to prices from the slider
			int fromPrice = Integer.parseInt(txt_sliderFromPrice());
			int toPrice = Integer.parseInt(txt_sliderToPrice());

			// Check if any products are displayed
			if (product_catalog.isEmpty()) {
				System.out.println("No products found in the catalog.");
			}

			//boolean allProductsInRange = true;

			// Iterate through each product
			for (int i =0; i<=product_catalog.size();i++) {

				String priceText=dynamic_Allprod_prices.get(i).getText();

				//String priceText = priceElement.getText();
				String finalPrice = priceText.replace("₹", "").replace(".00", "").trim();
				int productPrice = Integer.parseInt(finalPrice);

				// Check if the product price is within the specified range
				if (productPrice >= fromPrice && productPrice <= toPrice) {
					//allProductsInRange = true;
					System.out.println("Products within price range " + productPrice + "is between " + fromPrice + " to " + toPrice);
					return true;
				}else {
					//allProductsInRange = false;
					System.out.println("products displaying out of range...");
					return false;
				}
			}

		} catch (Exception e) {
			System.out.println("Error in checking product catalog: " + e.getMessage());	        
		}
		return false;
	}
}
