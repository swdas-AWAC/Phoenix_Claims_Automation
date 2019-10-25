package phoenix_pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixLogin {

	public WebDriver driver;
	public WebDriverWait wait;

	@FindBy(xpath=("//input[contains(@name,'username')]")) WebElement username;
	@FindBy(xpath=("//input[contains(@name,'password')]")) WebElement password;
	@FindBy(xpath=("//input[contains(@value,'Sign In')]")) WebElement signinbutton;
	@FindBy(xpath=("//span[@class='fas fa-fw fa-sign-out-alt']")) WebElement logout;
	
	public PhoenixLogin(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		//PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void clickonLogOut () 
	{
		PhoenixPageUtility.clickOnElement(driver, logout);
	}
	public void setusername(String userName)
	{
		username.sendKeys(userName);
	}
	public void setManagerusername(String userName)
	{
		username.sendKeys(userName);
	}
	public void setPassword(String PassWord)
	{
		password.sendKeys(PassWord);
	}
	
	public void setManagerPassword(String PassWord)
	{
		password.sendKeys(PassWord);
	}
	public void clickonsignin () 
	{
		PhoenixPageUtility.clickOnElement(driver, signinbutton);
	}
}
