package com.qa.btb.base;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.btb.factory.DriverFactory;
import com.qa.btb.pages.AccountPage;
import com.qa.btb.pages.LoginPage;
import com.qa.btb.pages.ProductInfoPage;
import com.qa.btb.pages.RegisterationPage;
import com.qa.btb.pages.SearchResultsPage;
import com.qa.btb.pages.cartPage;



public class BaseTest {
	
	WebDriver driver;
	protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
	protected Properties prop;
	DriverFactory df;
	

	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterationPage registrationPage;
	protected cartPage cartPage;
	protected SoftAssert softAssert;
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName )   {
		df= new DriverFactory();
		prop=df.initProp();
		
		
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		
		driver=df.initDriver(prop);
		loginPage= new LoginPage(driver);
		softAssert= new SoftAssert();
		
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		
		WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
	}

       
	
	
	
	
	
	}
}
