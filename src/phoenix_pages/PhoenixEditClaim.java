package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixEditClaim {
	public WebDriver driver;
	public WebDriverWait wait;

	//@FindBy(xpath="//button[text()=' Edit Claim ']") WebElement editclaimbutton;
	@FindBy(xpath="//div[@id = 'tabClaim']//span[@title = 'Edit Claim']/ancestor::button[1]") WebElement editclaimbutton;
	@FindBy(xpath="//div[@id = 'tabClaim']//span[@title = 'Save Changes']/ancestor::button[1]/i") WebElement savechangesbutton;
	@FindBy(xpath="//label[contains(text(),'Address Line 1')]/..//input") WebElement addressline1;
	@FindBy(xpath="//label[contains(text(),'Country/Area')]/following::input[1]") WebElement country;
	@FindBy(xpath="//label[contains(text(),'Description Of Loss')]/following::textarea[1]") WebElement DescriptionOfLoss;
	@FindBy(xpath="//label[contains(text(),'Diary Frequency')]/following::input[1]") WebElement DiaryFrequency; 
	@FindBy(xpath="//label[contains(text(),'Diary Date')]/following::input[1]") WebElement DiaryDate;
	

	public PhoenixEditClaim(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

//	public void clickonEditClaim() {
//		try {
//			PhoenixPageUtility.waitUntilPageActive(driver);
//			PhoenixPageUtility.clickOnElement(driver, editclaimbutton); 
//		}
//		catch(Exception ex) {
//			clickonEditClaim();
//
//		}
//	}


	public void clickonEdit() {

		try {
			wait.until(ExpectedConditions.visibilityOf(editclaimbutton));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(editclaimbutton).build().perform();
	        editclaimbutton.click();
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	            clickonEdit();
	        }

	}
	
	public void setAddressLine1(String addressvalue) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, addressline1);
		addressline1.clear();
		addressline1.sendKeys(addressvalue);
		
	}
	
	public void setCountry(String countryvalue) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, country);
		country.clear();
		country.sendKeys(countryvalue);
		driver.findElement(By.xpath("//body")).click();
		
	}
	public void setDescriptionOfLoss(String descloss) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, DescriptionOfLoss);
		DescriptionOfLoss.clear();
		DescriptionOfLoss.sendKeys(descloss);
	}
	
	public void clickonSaveChanges() {
		try {
			wait.until(ExpectedConditions.visibilityOf(savechangesbutton));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(savechangesbutton).build().perform();
	        Thread.sleep(3000);
	        savechangesbutton.click();
	        savechangesbutton.click();

	        
	        } catch(Exception ex) {
	            //ex.printStackTrace();
	            clickonSaveChanges();
	        }
	}
	
	public void SetDiaryDate(String DairyDtvalue ) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, DiaryDate);
		DiaryDate.clear();
		DiaryDate.sendKeys(DairyDtvalue);
		
	}

	public void SetDiaryFreq(String DairyFreqvalue ) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, DiaryFrequency);
		DiaryFrequency.clear();
		DiaryFrequency.sendKeys(DairyFreqvalue);
		
	}
}
