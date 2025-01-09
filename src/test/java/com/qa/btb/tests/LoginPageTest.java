package com.qa.btb.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.btb.base.BaseTest;
import com.qa.btb.constants.AppConstants;

public class LoginPageTest extends BaseTest
{
	@Test (priority=1)
	public void loginPageTitleTest()
	{
		String actTitle= loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test (priority=2)
	public void loginPageURLTest()
	{
		String actURL= loginPage.getLoginPageUrl();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test  (priority=3)
	public void isForgotPWDLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPWDLinkExist());
	}
	
	@Test  (priority=4)
	public void loginTest() {
	accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	

}
