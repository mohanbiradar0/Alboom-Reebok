package com.qa.btb.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.btb.base.BaseTest;
import com.qa.btb.pages.cartPage;

public class cartPageTest extends BaseTest{

    @BeforeClass
    public void productInfoPageSetup() {
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

    }
   
	
    @DataProvider
    public  Object [] [] getCartPageHeaderData()
	{
		return new Object [] []
				{
			{"shoes",3,"YOUR BAG"},
			
			};
	}
    @Test (dataProvider = "getCartPageHeaderData")
    public void cartPageheaderTest(String searchKey, int indexValue, String cartHeading)
    {
    	 
    	  searchResultsPage = accPage.doSearch(searchKey);
          productInfoPage = searchResultsPage.selectProduct();
          productInfoPage.selectSize(indexValue);
          productInfoPage.addProductToCart();
          cartPage cartpage=productInfoPage.goToCartPage();
          String accCartpageHeading= cartpage.getProductHeader();
           Assert.assertEquals(accCartpageHeading, cartHeading);
   
       }
    
    }

