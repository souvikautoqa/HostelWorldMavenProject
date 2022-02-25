package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.HomePage;
import pages.HostelViewPage;
import pages.SearchResultPage;

public class HostelBookingTest extends TestBase {
	
	HomePage home = null;
	
	@BeforeMethod
	public void initApp() throws Exception {
		home = new HomePage(driver());
		home.launchApp();
	}
	

	@Test(dataProvider="getData")
	public void verifyCheckoutDetails(Map<String,String> data) throws Exception {
		home.searchHostel(data.get("city"));
		SearchResultPage result = new SearchResultPage(driver());
		result.viewHostel(1);
		HostelViewPage hostel = new HostelViewPage(driver());
		
		setTempData("roomPrice",hostel.getRoomPrice(0));
		hostel.chooseRoom(0);
		setTempData("pricPerList",hostel.getPricePerNight());
		hostel.multiplyNumberPerNightsToPricePerNightToGetTotalPrice();
		hostel.bookNow();
		CheckoutPage checkout = new CheckoutPage(driver());
		setTempData("checkPricPerList",hostel.getPricePerNight());
		Assert.assertEquals(getTempData("roomPrice").equals(getTempData("checkPricPerList")), true, "incorrect price");
		checkout.SendEmailAdresss(data.get("email"));
		Thread.sleep(2000);
		checkout.UnselectCheckboX();
		Thread.sleep(2000);
		checkout.SelectNext();
	}
	
	//@Test(dataProvider="getData")
	public void hostelSearch(Map<String,String> data) throws Exception {
		home.searchHostel(data.get("city"));
		SearchResultPage result = new SearchResultPage(driver());
		result.viewHostel(2);
		HostelViewPage hostel = new HostelViewPage(driver());
		
		setTempData("roomPrice",hostel.getRoomPrice(0));
		hostel.chooseRoom(0);
	}
	
	

}
