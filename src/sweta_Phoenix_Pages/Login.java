package sweta_Phoenix_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath=("//label[contains(text(),'Username')]/following::input[1]")) WebElement username;
	@FindBy(xpath=("//label[contains(text(),'Password')]/following::input[1]")) WebElement password;
	@FindBy(xpath=("//input[@value='Sign In']")) WebElement signin;

	public Login(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	
	}
	
	public void setUsername(String uname)
	{
		username.sendKeys(uname);	
	}
	
	public void setpassword(String Password)
	{
		password.sendKeys(Password);	
	}
	
	public void ClickonSignIn()
	{
		signin.click();
	}
}
