package pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver = null;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	protected boolean waitForElement(WebElement ele, long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofMillis(500))
					.until(ExpectedConditions.elementToBeClickable(ele));
			return true;
		}catch(Exception e) {}
		return false;
	}
	
	protected boolean waitForAlert(long timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofMillis(500))
					.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e) {}
		return false;
	}
	
	
	

}
