package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {

	@FindBy(xpath=".//p[contains(text(),'All properties')]/following-sibling::div[@class='property-card']//button[text()='View']")
	private List<WebElement> viewBtnList;
	
	
	public SearchResultPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void viewHostel(int index) {
		if(waitForElement(viewBtnList.get(index),5)) {
			viewBtnList.get(index).click();
		}
	}
}
