package com.qa.btb.factory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.btb.exceptions.BrowserException;
import com.qa.btb.exceptions.FrameworkException;



public class DriverFactory {
	static WebDriver driver;
	 private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
	WebDriverWait wait ;
	Properties prop;
	public static String highlight;
	OptionsManager optionsmanager;
	 public WebDriver getDriver() {
	        return driverThread.get();
	    }
	
	/*
	 * initializing the drivers 
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName=prop.getProperty("browser");
		System.out.println("browser Name is : "+browserName);
		
		highlight=prop.getProperty("highlight");
		
		optionsmanager = new OptionsManager(prop);
		
		switch (browserName.trim().toLowerCase()) {
        case "chrome":
            driverThread.set(new ChromeDriver(optionsmanager.getChromeOptions()));
            break;
        case "firefox":
            driverThread.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
            break;
        case "edge":
            driverThread.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
            break;
        case "safari":
            driverThread.set(new SafariDriver());
            break;
        default:
            System.out.println("Please pass the right browser: " + browserName);
            throw new BrowserException("NO BROWSER FOUND: " + browserName);
    }
		WebDriver driver = getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		// Adjust the timeout as needed
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the element with id "btn-cookie-allow" to be clickable
        try {
            // Wait for the element with id "btn-cookie-allow" to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-cookie-allow"))).click();
        } catch (Exception e) {
            System.out.println("Cookie banner not found or interactable. Proceeding without accepting cookies.");
        }
        return driver;
	}
	
	public Properties initProp()
	{
		//fetch env name stage prod UAT dev
		//mvn clean install -Denv="live" (it is used for running on diffent browsers )
		FileInputStream ip=null;
		prop= new Properties();
		
		String envName=System.getProperty("env");
		System.out.println("Running tests on Env: "+ envName);
		try {
		if(envName==null) {
			System.out.println("No env is given ,,,..... hence runninhg it on normal env ,,,,");
			
				ip= new FileInputStream(".\\src\\test\\resourcess\\config\\config.properties");
			
			}
		
		else {
		switch (envName.toLowerCase().trim()) {
		case "Live":
			
			ip= new FileInputStream(".\\src\\test\\resourcess\\config\\config.live.properties");
			break;
			
		case "dev":
			ip= new FileInputStream(".\\src\\test\\resourcess\\config\\config.live.properties");
			break;
			
		
	
		default:
			System.out.println("please pass the right env name....." +envName);
			throw new FrameworkException("========WRONG ENV NAME======+");
			
		}
		}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		prop= new Properties();
//		try {
//			FileInputStream ip2= new FileInputStream("./src/test/resourcess/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;
//		
//		
		return prop;
	}

   
	
	
	
	

}
