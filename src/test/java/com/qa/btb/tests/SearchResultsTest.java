package com.qa.btb.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.btb.base.BaseTest;

public class SearchResultsTest extends BaseTest {

	@BeforeClass
	public void searchResultsSetup() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@DataProvider
	public  Object [] [] getProductCountData()
	{
		return new Object [] []
				{
			{"shoes",40},
			{"cloths",40}
			
				};
	}
	@Test( dataProvider="getProductCountData")
	public void searchResultsTest(String searchKey, int  productCount )
	{  searchResultsPage=accPage.doSearch(searchKey);
		int count =searchResultsPage.getSearchProductCount();
	Assert.assertEquals(count ,	productCount);
	}

	@DataProvider
	public  Object [] [] getSearchHeaderData()
	{
		return new Object [] []
				{
			{"shoes"},
			{"cloths"}
			
				};
	}
	@Test ( dataProvider="getSearchHeaderData" , priority = 1)
	public void isSearchPageHeadingTest(String searchKey)
	{
		searchResultsPage=accPage.doSearch(searchKey);
		
		Assert.assertTrue(searchResultsPage.isSearchPageHeadingDisplayed());
	}
	
	
}