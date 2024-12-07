package atPageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	public BasePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);//The PageFactory.initElements(driver, this) is a key part. It's essentially a way to initialize all the WebElements we'll define in our page classes using @FindBy annotations. So instead of manually finding elements with driver.findElement() everywhere, PageFactory does the setup for us automatically.
	
	}
	
	
}
