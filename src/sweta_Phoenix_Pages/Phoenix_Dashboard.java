package sweta_Phoenix_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Phoenix_Dashboard {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath=("//a[contains(text(),'Claim Search')]"))WebElement ClaimSearch;

	public Phoenix_Dashboard(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	
	}
	
	
	public void clickonClaimSearch ()
	
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(ClaimSearch));
		ClaimSearch.click();
		} catch(Exception e) {
			e.printStackTrace();
			clickonClaimSearch();
			}
	}
	
}








