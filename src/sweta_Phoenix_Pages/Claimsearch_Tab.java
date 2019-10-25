package sweta_Phoenix_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Claimsearch_Tab {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath=("//a[contains(text(),' New Claim ')]"))WebElement newclaimbutton;

	public Claimsearch_Tab(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	
	}
	
	public void Clickonnewclaimbutton () {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(newclaimbutton));
			newclaimbutton.click();
			
		}catch(WebDriverException e) {
			
			Clickonnewclaimbutton ();
		}
		
	}
}
