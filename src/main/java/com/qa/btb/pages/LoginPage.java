package com.qa.btb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.btb.exceptions.ElementException;
import com.qa.btb.utils.ElementUtil;

public class LoginPage
{
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.PRIVATE BY LOCATORS
	private By emailId= By.name("login[username]");
	private By password= By.xpath("(//input[@type='password'])[1]");
	private By loginButton = By.cssSelector("button.action.primary.login-btn");
	private By forgotPWDLink= By.cssSelector("div span.link");
	private By registerLink= By.linkText("Register");
	private By signinbyemailLink= By.className("loginemail_btn");
	//private By signinbyemailLink=By.xpath("//button[@id=\"login_with_email\"]");
	

	//public page class constructors 
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
			
		}
	//3.public Page action/method 
	public String getLoginPageTitle() {
		String title= eleUtil.waitForTitleIs("LOG IN",5);
		System.out.println("login page title:"+ title);
		return title;
	}
	public String getLoginPageUrl()
	{
		String url= eleUtil.waitForURLContains("customer/account/login", 5);
		System.out.println("login page url:"+ url);
		return url; 
	}
	
	public boolean isForgotPWDLinkExist()
	{
		return eleUtil.isElementDisplayed(forgotPWDLink);
	}
	public AccountPage  doLogin(String username, String pwd)
	{
		//for redirecting to the login form 
		eleUtil.doClick(signinbyemailLink, 5);	
		eleUtil.waitForElementVisible(emailId, 10).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		System.out.println("User name:"+ username+ " Password: "+pwd);
		
		return new AccountPage(driver);
	}
	
	public RegisterationPage navigateToRegisterPage()
	{
		eleUtil.waitForElementVisible(registerLink, 10).click();
		return new RegisterationPage(driver);
	}
}
 	