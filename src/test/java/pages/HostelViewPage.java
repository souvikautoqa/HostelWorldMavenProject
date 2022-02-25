package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HostelViewPage extends BasePage {

	@FindBy(xpath = "(.//div[@class='availability-table'])[1]//div[@data-room-id]//div[@class='price']")
	private List<WebElement> priceList;

	@FindBy(xpath = "(.//div[@class='availability-table'])[1]//div[@data-room-id]//div[@class='choose-room']")
	private List<WebElement> roomList; //

	@FindBy(xpath = ".//div[@class='room-selection']//div[@class='rate-selected']//div[@class='room-price']/span[@class='price-per-night']")
	private WebElement pricePerNight;

	@FindBy(xpath = ".//div[@class='room-selection']//div[@class='rate-selected']//div[@class='room-price']/span[@class='number-nights']")
	private WebElement numberPerNights;

	@FindBy(xpath = ".//div[@class='room-selection']//div[@class='rate-selected']//div[@class='room-price']/span[@class='total-price']")
	private WebElement totalPrice;
	
	@FindBy (xpath=".//div[@class='room-selection']//div[@class='room-payable-now']/span[@class='room-payable-price']")
	public WebElement payableNow;
	
	@FindBy (xpath =".//button[contains(@id,'book-now-button')]")
	private WebElement bookNowButton;

	public HostelViewPage(WebDriver driver) {
		super(driver);
	}

	public double getRoomPrice(int index) {
		String roomPrice = priceList.get(index).getText().substring(1);
		System.out.println(roomPrice);
		return Double.parseDouble(roomPrice);
	}

	public void chooseRoom(int index) {
		roomList.get(index).click();
		roomList.get(index).findElement(By.xpath("//div[@class='menu']//li[1]")).click();
	}

	public double getPricePerNight() {
		String roomPrice = pricePerNight.getText().substring(1);
		return Double.parseDouble(roomPrice);
	}
	
	public double getroomPayablePrice() {
		String roomPrice = payableNow.getText().substring(1);
		System.out.println(roomPrice);
		return Double.parseDouble(roomPrice);
	}
	
	public void multiplyNumberPerNightsToPricePerNightToGetTotalPrice() {
		String multiple = numberPerNights.getText().split(" ")[0];
		String num = numberPerNights.getText().split(" ")[1];
		String night = numberPerNights.getText().split(" ")[2];
		
		int number = Integer.parseInt(num);
		double res = getPricePerNight() * number;
		System.out.println(res);
		double percentage = getroomPayablePrice() / res;
		System.out.println(percentage);
		double payNow = percentage * res + 0;
		System.out.println(payNow);
		
		}
	
	public void bookNow() {
		if (waitForElement(bookNowButton, 5)) {
			bookNowButton.click();
		}
		
	}
}