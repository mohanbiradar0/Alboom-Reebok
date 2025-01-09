package com.qa.btb.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.btb.base.BaseTest;
import com.qa.btb.constants.AppConstants;


public class AccountPageTest extends BaseTest 
{
@BeforeClass
public void accountSetup()
{
	accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
}

@Test
public void accPageTitleTest() {
	String actTitle= accPage.getAccountPageTitle();
	Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
}
@Test
public void accPageURLTest() {
	String actURL= accPage.getAccountPageUrl();
	Assert.assertTrue(actURL.contains(AppConstants.ACC_PAGE_URL_FRACTION));
}

@Test(priority = 1)
public void isLogoutLinkExist()
{
	Assert.assertTrue(accPage.isLogoutLinkExist());
}

@Test
public void isMyAccountLinkExist()
{
	Assert.assertTrue(accPage.isMyAccountLinkExist());
}

@Test 
public void accPageLeftSectionsTest()
{
	List<String>accMyAccLeftSecList=accPage.getAccountPageLeftSectionList();
	System.out.println(accMyAccLeftSecList);
}
@Test(priority = 2)
public void searchTest()
{
	accPage.doSearch("shoes");
}	

	
	
	
	
}
