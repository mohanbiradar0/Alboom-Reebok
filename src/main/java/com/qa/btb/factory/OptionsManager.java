package com.qa.btb.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}
	public ChromeOptions getChromeOptions()
	{
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
			System.out.println("Running chrome in headless mode");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){
			co.addArguments("-incognito");
		}
		
		return co;
	}
	public FirefoxOptions getFirefoxOptions()
	{
		fo=new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
			System.out.println("Running firefox in headless mode");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){
			fo.addArguments("--inprivate");
		}
		
		return fo;
	}
	public EdgeOptions getEdgeOptions()
	{
		eo=new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())){
			System.out.println("Running edge in headless mode");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())){
			eo.addArguments("--inprivate");
		}
		
		return eo;
	}
	
	
	
	
	
	
	
	
	

}
