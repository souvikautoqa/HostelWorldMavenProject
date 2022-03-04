package tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import core.Config;
import core.DriverFactory;
import core.ExcelDataProvider;
import core.ITestData;
import core.JSONDataProvider;

public class TestBase {
	
	private WebDriver driver;
	private DriverFactory df;
	private Map<String, Object> tempData;
	
	@BeforeSuite
	public void initSuite() {
		tempData = new HashMap<String, Object>();
		System.out.println(System.getenv("env"));
		Config.initProperties(System.getenv("env"));
	}
	
	public void ridaSuite() {
		System.out.println("Hello");
	}
	
	public void ridatest() {
		System.out.println("Hello");
	}
	
	
	//@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void initDriver() {
		String browser =  System.getenv("browser");
		df = new DriverFactory();
		driver = df.getDriver(browser);
	}
	
	@AfterClass(alwaysRun=true)
	public void quitDriver() {
		if(driver!=null) {
			df.quitDriver();
		}		
	}
	
	@DataProvider()
	public Object[][] getData(Method method){
		try {
			String tcName =  method.getName();
			String testDataFilename = System.getProperty("user.dir")+"//src//test//resources//testdata//HostelWorldDataSet.xlsx";
			String env = System.getenv("env").trim().toUpperCase();
			String ds = System.getenv("dataSource").trim().toLowerCase();
			ITestData data = null;
			
			if(ds.equals("excel")) {
				data = new ExcelDataProvider(testDataFilename,env);
			}else if(ds.equals("json")) {
				data = new JSONDataProvider(System.getProperty("user.dir")+"//src//test//resources//testdata//"+env.toLowerCase()+".data.json");
			}
			List<Map<String,String>> extractedData = data.getAllData(tcName);
			
			Object[][] dataObject = new Object[extractedData.size()][1];
			int count =0;
			
			for(Map<String,String> map :  extractedData) {
				dataObject[count][0] = map;
				count++;
			}
 			
			
			return dataObject;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	public WebDriver driver() {
		return driver;
	}
	
	public Object getTempData(String key) {
		if(tempData.containsKey(key)) {
			return tempData.get(key);
		}
		return null;
	}
	
	public void setTempData(String key, Object value) {
		if(!tempData.containsKey(key)) {
			tempData.put(key, value);
		}
	}
	
	
	
	
}
