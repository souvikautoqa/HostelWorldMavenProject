package pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Config;


public class HomePage extends BasePage{
	
    // Locators	
	@FindBy(id="truste-consent-button")
	private WebElement btnCookies;
	
	@FindBy(id="location-text-input-field")
	private WebElement txtLocationInput;
	
	@FindBy(id="search-input-field")
	private WebElement txtSearchInput;
	
	@FindBy(id="predicted-search-results")
	private WebElement predictedSearchResult;
	
	@FindBy(xpath=".//div[@class='label']/..")
	private List<WebElement> listSuggesstions;
	
	@FindBy(id="search-button")
	private WebElement btnSearchHostel;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	// Business Logic
	public void launchApp() throws Exception {
		driver.navigate().to(Config.getProperty("url"));
		if(waitForAlert(2)) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		if(waitForElement(btnCookies, 1)) {
			btnCookies.click();
		}
		
	}
	
	public void searchHostel(String location) throws Exception {
		txtLocationInput.click();
		for(int i=0; i<location.length(); i++) {
			txtSearchInput.sendKeys(String.valueOf(location.charAt(i)));
			Thread.sleep(100);
		}
		
		if(predictedSearchResult.isDisplayed()) {
			listSuggesstions.get(0).click();
			if(waitForElement(btnSearchHostel, 5 )) {
				btnSearchHostel.click() ;
			}	
		}
	}
		
	
	
	
	
	
	
	
}


