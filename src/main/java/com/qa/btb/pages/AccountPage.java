package com.qa.btb.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.btb.constants.AppConstants;

import com.qa.btb.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.PRIVATE BY LOCATORS

	private By logoutLink= By.linkText("Log Out"); 
	
	
	private By myAccountLink= By.cssSelector("li.nav.item.current");
	private By myAccountOptions=By.cssSelector("ul.nav.items");
	private By search=By.xpath("//input[@id='search']");
	private By searchButtonclick=By.xpath("//button[@class='action search']");
	/* the above is used for firefox testing if not working please revert the below locator 
	 * 
	 */
//	private By searchButtonclick = By.cssSelector("button.action.search");
			
	
	//public page class constructors 
	public AccountPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
			
		}
	//3.public Page action/method 
		public String getAccountPageTitle() {
			String title= eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE,5);
			System.out.println("Account page title:"+ title);
			return title;
		}
		public String getAccountPageUrl()
		{
			String url= eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);
			System.out.println("account page url:"+ url);
			return url; 
		}
		public boolean isLogoutLinkExist()
		{
			return eleUtil.waitForElementVisible(logoutLink, 10).isDisplayed();
		}
		public boolean isMyAccountLinkExist()
		{
			return eleUtil.waitForElementVisible(myAccountLink, 10).isDisplayed();
		}
		
		public List<String> getAccountPageLeftSectionList() {
		List<WebElement> myAccountLeftEleSectionList=	eleUtil.getElements(myAccountOptions);
		List<String>myAccountLeftSectionList= new ArrayList<String>();
		for(WebElement e: myAccountLeftEleSectionList)
		{
			String leftSection=e.getText();
			myAccountLeftSectionList.add(leftSection);
		}
		return myAccountLeftSectionList;
		
		}
		
		public SearchResultsPage doSearch(String searchKey)
		{
			System.out.println("Searching for: "+ searchKey);
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.waitForElementPresence(searchButtonclick, 5).click();
		

			System.out.println("clicked on search icon after entering search ");
			return new SearchResultsPage(driver);
			
			
		}
		
}
  