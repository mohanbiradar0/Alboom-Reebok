package com.qa.btb.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.btb.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private WebDriverWait wait;
	private Map<String, String> productMap = new HashMap<String, String>();
	// 1.PRIVATE BY LOCATORS

	private By productHeader = By.tagName("h1");
	private By images = By.xpath("//img[@class='magnifier-img']");
	private By showmore = By.xpath("//button[@id='show-more']");
	private By actPrice = By.xpath("(//span[@class='price'])[2]");

	private By sizeData = By.cssSelector("#us_content");
	private By sizeSelected = By.xpath("//div[@id='us_content']/div[@class='size-button']");
	private By sizeSlectedorNot = By.cssSelector("div.size-button.selected");
	private By addToCartButton = By.xpath("//button//*[local-name()='svg']");
	private By successMsgAddToCart = By.linkText("shopping cart");
	private By productNameFrmMinicart = By.xpath("//strong//a");
	private By miniCartIcon = By.cssSelector(".action.showcart");
	private By viewCartTextLink = By.xpath("//span[text()='View and Edit Bag']");

	private By productMetaData = By.xpath("//div[@class='product-info-stock-sku']");
	private By productPriceData = By.xpath("(//div[@'class='price-box price-final_price'])[1]");
	private By productMiniCartCount = By.cssSelector(".counter-number");
	int productCount;

	// public page class constructors
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeader() {
		String headerProductName = eleUtil.doGetElementText(productHeader);
		System.out.println(headerProductName);
		return headerProductName;
	}

	public int getProductImagesCount() {
		// clicking on the show more button for the next set of images to be visible
		eleUtil.waitForElementVisible(showmore, 5).click();
		int totalImages = eleUtil.waitForElementsVisible(images, 10).size();
		System.out.println("Images count for " + getProductHeader() + " : " + totalImages);
		return totalImages;
	}

	// AVAILABILITY: IN STOCK
//	SKU O01FOF100136
//    private void getProductMetaData() {
//        List<WebElement> metaList = eleUtil.getElements(productMetaData);
//        for (WebElement e : metaList) {
//            String text = e.getText();
//            String metaKey = text.split(":")[0].trim();
//            String metaValue = text.split(":")[1].trim();
//            productMap.put(metaKey, metaValue);
//        }
//    }
//
//    public String getPriceData() {
//        return eleUtil.doGetElementText(actPrice);
//
//    }
	public String getPriceData() {
		int attempts = 0;
		while (attempts < 3) { // Retry a few times before failing
			try {
				WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(actPrice));
				return priceElement.getText();
			} catch (StaleElementReferenceException e) {
				attempts++;
				System.out.println("Stale element reference exception caught. Retrying... " + attempts);
			} catch (NoSuchElementException e) {
				throw new RuntimeException("Element not found: " + actPrice, e);
			}
		}
		throw new RuntimeException("Failed to retrieve element text after retries: " + actPrice);
	}

	private void getProductpriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String org_price = priceList.get(1).getText();
		productMap.put("Productprice", price);
		productMap.put("orgprice", org_price);
	}

	public List<String> getSizeDetails() {
		List<WebElement> sizeList = eleUtil.getElements(sizeData);
		List<String> sizeArrayList = new ArrayList<>();
		for (WebElement e : sizeList) {
			String text = e.getText();
			sizeArrayList.add(text);
			System.out.println(sizeArrayList);
		}
		return sizeArrayList;
	}

	public String selectSize(int index) {
//		eleUtil.refreshPage();
		List<WebElement> sizeList = eleUtil.getElements(sizeSelected);

		// if the index is out of bound then this if condition will throw exception
		// error
		if (index < 1 || index > sizeList.size()) {
			throw new IllegalArgumentException("Invalid index specified");
		}

		// Adjusting index to start from 0
		sizeList.get(index - 1).click();
		String text = eleUtil.waitForElementVisible(sizeSlectedorNot, 5).getText();
		System.out.println(" The selected Size for the  " + getProductHeader() + "is :" + text);
		return text;
	}

	public String addProductToCart() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		eleUtil.waitForElementVisible(addToCartButton, 5).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		String successMessage = wait.until(ExpectedConditions.elementToBeClickable(successMsgAddToCart)).getText();
		//    	String successMessage=eleUtil.waitForElementVisible(successMsgAddToCart,15).getText();
		System.out.println(successMessage);
		return successMessage;
	}

	public cartPage goToCartPage() {
		eleUtil.waitForElementVisible(miniCartIcon, 10).click();

		eleUtil.waitForElementVisible(viewCartTextLink, 5).click();
		return new cartPage(driver);
	}

//    public Map<String, String> getProductDetailsMap() {
////		productMap.put("headerProductName", getProductHeader());
////		productMap.put("productImages", String.valueOf(getProductImagesCount()));
////		getProductMetaData();
//        getProductpriceData();
//        return productMap;
//    }

}
