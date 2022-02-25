package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tests.TestBase;

public class CheckoutPage extends BasePage {

	@FindBy(xpath = "//*[@id=\"checkout-email\"]")
	private WebElement checkoutEmail;

	@FindBy(xpath = "//*[@id=\"checkout-steps\"]/div/div[2]/div[2]/span/input")
	private WebElement checkbox;

	@FindBy(xpath = ".//button[contains(@id,'increment-step')]")
	private WebElement nextButton;

	@FindBy(xpath = "..//div[@class='checkout-summary']//div[@class='info']//div[@class='room-price']//span[@class='price-per-night']")
	private WebElement checkoutpricePerNight;

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	public double getPricePerNight() {
		String roomPrice = checkoutpricePerNight.getText().replace("£", "");
		System.out.println("checout price" + " " + checkoutpricePerNight);
		return Double.parseDouble(roomPrice);
	}

	public void SendEmailAdresss(String email) {
		checkoutEmail.click();
		checkoutEmail.sendKeys(email);
	}

	public void SelectNext() {
		if (nextButton.isEnabled()) {
			nextButton.click();
		}
	}

	public void UnselectCheckboX() {
		if (checkbox.isSelected()) {
			checkbox.click();
		}
	}
}