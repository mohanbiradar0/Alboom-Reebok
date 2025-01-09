package com.qa.btb.pages;

import javax.security.auth.login.AppConfigurationEntry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.btb.constants.AppConstants;
import com.qa.btb.utils.ElementUtil;

public class RegisterationPage {


	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.PRIVATE BY LOCATORS
	private By firstName= By.cssSelector("#firstname");
	private By lastName= By.cssSelector("#lastname");
	private By email= By.xpath("//input[@type='email']");
	private By password= By.xpath("//input[@id='password']");
	private By confrmPassword= By.xpath("//input[@id='password-confirmation']");
	private By privacyCheckBox= By.xpath("(//input[@type='checkbox'])[3]");
	private By registerButton=By.cssSelector(".action.submit.primary");
	private By registerbuttonforsignupform=By.xpath("//div[@id=\"login_with_otp\"]");
	private By succsMessge= By.xpath("//div[contains(text(),'Thank you for registering with Reebok store.')]");
	private By logoutLink=By.linkText("Log Out");
	private By registerLink= By.linkText("Register");
	private By loginLink= By.linkText("log in");
	private By signinbyemailLink= By.className("loginemail_btn");

	//public page class constructors 
		public RegisterationPage(WebDriver driver) {
			this.driver= driver;
			eleUtil= new ElementUtil(driver);
				
			}
		//3.public Page action/method 
	public boolean userRegister(String firstName, String lastName, String email,String password ) {
		eleUtil.doClick(signinbyemailLink);
		eleUtil.doClick(registerbuttonforsignupform);
		eleUtil.waitForElementVisible(this.firstName, 10).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confrmPassword, password);
		
		eleUtil.doClick(privacyCheckBox);
		eleUtil.doClick(registerButton);
		
	String regSuccessMesg=	eleUtil.waitForElementVisible(succsMessge, 10).getText();
	System.out.println(regSuccessMesg);
	if(regSuccessMesg.equals(AppConstants.USER_REG_SUCCESS_MESSG))
	{
		eleUtil.doClick(logoutLink);
		eleUtil.waitForElementVisible(loginLink, 5).click();;
		eleUtil.doClick(registerLink);
		return true;
	}
	return false;
	}
	
}
