package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixClaimSummary {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//button[text()=' Create Claim ']") WebElement createclaimbutton;
	@FindBy(xpath="//table[@class='table table_sw']//tr[@class='tr_totals']/td[5]") WebElement indemnityremaining;
	@FindBy(xpath="//label[text()='Claim No']/..//p[1]") WebElement ClaimNumber;
	@FindBy(xpath="//label[text()='Diary Frequency']/..//p[1]") WebElement DiaryFrequency;
	@FindBy(xpath="//label[text()='Diary Date']/..//p[1]") WebElement DiaryDate;
	
	public PhoenixClaimSummary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void clickonCreateClaim() {
		PhoenixPageUtility.clickOnElement(driver, createclaimbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public String GetClaimNo()
	{
		String claimno= ClaimNumber.getText().trim();
		return claimno;		
	}
	public double getIndemnity()
	{
		String actualindemnity= indemnityremaining.getText().trim();
		actualindemnity = actualindemnity.replaceAll(",", "");
		double acindemnity = Double.parseDouble(actualindemnity);
		return acindemnity;		
	}
	
	public String GetDiaryFrequency()
	{
		String DiaryFrq= DiaryFrequency.getText().trim();
		return DiaryFrq;		
	}
	
	public String GetDiaryDate()
	{
		String DiaryDt= DiaryDate.getText().trim();
		return DiaryDt;		
	}
}
