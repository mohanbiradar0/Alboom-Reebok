package com.qa.btb.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.qa.btb.utils.ElementUtil;
import com.qa.btb.utils.JavaScriptUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	private WebDriverWait wait;
	//1.PRIVATE BY LOCATORS
	
	private By searchProducts= By.cssSelector("img.product-image-photo");
	private By searchPageHeader= By.xpath("//h1/span[@class='base']");
	private By productNameClickForProduct = By.cssSelector("li.item.product.product-item");
	private By productNameClickForProduct2 = By.cssSelector("li.item.product.product-item");



			
	//public page class constructors 
	public SearchResultsPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
		wait = new WebDriverWait(driver, Duration.ofMillis(15));
			
		}
	
	public int  getSearchProductCount() {
		
		 return eleUtil.waitForElementsVisible(searchProducts, 15).size();	


	}
	
	public boolean isSearchPageHeadingDisplayed()
	{
		System.out.println("landed on search page ");
		
		return eleUtil.waitForElementVisible(searchPageHeader, 10).isDisplayed();
	}
	
	public ProductInfoPage selectProduct()
	{
		
//		eleUtil.waitForElementVisible(By.linkText("Reebok Rush Runner 4.0 Td"),10).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(productNameClickForProduct)).click();
		wait.pollingEvery(Duration.ofMillis(500)).ignoring(StaleElementReferenceException.class)
				.withMessage("ELEMENT NOT FOUND>>>>......");

		
//		eleUtil.waitForElementVisible(productNameClickForProduct,15).click();

		return new ProductInfoPage(driver);
	}
	



	
	}
	


