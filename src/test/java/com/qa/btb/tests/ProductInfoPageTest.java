package com.qa.btb.tests;

import com.qa.btb.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoPageSetup() {
    	accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

    }

    @DataProvider
	public  Object [] [] getProductHeadereData()
	{
		return new Object [] []
				{
			{"shoes","COURT PEAK UNISEX SHOES"},
			{"cloths","ESSENTIALS VECTOR GRAPHIC WOMEN'S TRAINING T-SHIRT"}
			
				};
	}
    	
   
    @Test(priority = 1, dataProvider = "getProductHeadereData")
    public void productHeaderTest(String searchKey ,String productName ) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct();
        String accProductHeaderName = productInfoPage.getProductHeader();
        Assert.assertEquals(accProductHeaderName, productName);

    }

    @DataProvider
	public  Object [] [] getProductImagesCountData()
	{
		return new Object [] []
				{
			{"shoes",8},
			{"cloths",6}
			
				};
	}
    @Test(priority = 2, dataProvider = "getProductImagesCountData")
    public void getProductImagesCount(String searchKey ,int productCount) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct();
        Assert.assertEquals(productInfoPage.getProductImagesCount(), productCount);
    }

    @DataProvider
   	public  Object [] [] getProductPriceData()
   	{
   		return new Object [] []
   				{
   			{"shoes","AED 150.00"},
   			{"cloths","AED 28.00"}
   			
   				};
   	}
    @Test(priority = 3,dataProvider = "getProductPriceData")
    public void getPriceData(String searchKey,String prodPrice) { 
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct();
        String ActPriceValue = productInfoPage.getPriceData();
        Assert.assertEquals(ActPriceValue, prodPrice);
    }
    @DataProvider
   	public  Object [] [] getProductSizeData()
   	{
   		return new Object [] []
   				{
   			{"shoes"},
   			{"cloths"}
   			
   				};
   	}
@Test(priority=4, dataProvider = "getProductSizeData")
    public void getSizeDataTest(String searchKey) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct();
    List<String> sizeListOfFirstProduct= productInfoPage.getSizeDetails();
    System.out.println(sizeListOfFirstProduct);
    }

@DataProvider
	public  Object [] [] getProductSelectData()
	{
		return new Object [] []
				{
			{"shoes",4,"M12/W13.5"},
			};
	}


    @Test(priority=5, dataProvider = "getProductSelectData")
    public void selectSizeTest(String searchKey ,int indexValue, String  SelectedSize) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct();
       String accSelectedText= productInfoPage.selectSize(indexValue);
      Assert.assertEquals(accSelectedText,SelectedSize);
    }
    
    @DataProvider
   	public  Object [] [] addToCartData()
   	{
   		return new Object [] []
   				{
   			{"shoes",4,"shopping cart"}
   			
   			
   				};
   	}
    @Test(priority=6,dataProvider = "addToCartData")
    public void addTocartTheProductTest(String searchKey ,int indexValue, String  addToCartSuccessMessg) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct();
        productInfoPage.selectSize(indexValue);
        String  accSuccessMsg= productInfoPage.addProductToCart();
        Assert.assertEquals(accSuccessMsg, addToCartSuccessMessg);
       
       
      
    }
   
/*
	@Test
	public void ProductDetailsTest(){

		searchResultsPage=accPage.doSearch("shoes");
		productInfoPage= searchResultsPage.selectProduct();
		Map<String, String>productsAccDetailsMap= productInfoPage.getProductDetailsMap();
		Assert.assertEquals(productsAccDetailsMap.get("Productprice"), "AED 99.00");
		Assert.assertEquals(productsAccDetailsMap.get("orgprice"), "AED 125.00");
	}
*/
}
		