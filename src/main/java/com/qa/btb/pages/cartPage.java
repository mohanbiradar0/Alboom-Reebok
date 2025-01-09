package com.qa.btb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.btb.utils.ElementUtil;

public class cartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private WebDriverWait wait;
	private By cartPageHeader= By.xpath("//span[@class='base']");
	
	
	  //public page class constructors
    public cartPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
        

    }

    public String getProductHeader() {
        String headerProductName = eleUtil.waitForElementVisible(cartPageHeader,5).getText();
        System.out.println(headerProductName);
        return headerProductName;
    }
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
