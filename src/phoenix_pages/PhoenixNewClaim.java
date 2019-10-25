package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixNewClaim {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//ul[@class='nav nav-tabs']/li/following::a[text()=' Claim Search']") WebElement Claimsearch;
	@FindBy(xpath = "//a[text()=' New Claim ']") WebElement newclaim;
	@FindBy(xpath = "//label[text()='Ceding Company Name or Contract Code']/../input") WebElement compnameorcontract;
	@FindBy(xpath = "//label[text()='UW Year']/../input") WebElement contractyear;
	@FindBy(xpath = "//a[@class='btn btn-primary btn_AW_red search-btn']") WebElement searchbutton;
	@FindBy(xpath = "//button[text()=' Next Step ']") WebElement nextstep;
	
	
	public PhoenixNewClaim(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 20);
		//PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void clickonclaimsearch() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, Claimsearch);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void clickonNewClaim() {
		PhoenixPageUtility.clickOnElement(driver, newclaim);
	}
	
	public void setContractOrCompName (String compnameorcontractvalue) {
		compnameorcontract.sendKeys(compnameorcontractvalue);
	}
	
	public void setContractYear(String yearvalue) {
		contractyear.sendKeys(yearvalue);
	}
	
	public void clickonSearchbutton() {
		PhoenixPageUtility.clickOnElement(driver, searchbutton);
	}
	
	public void clickoncontract(String contractvalue) {
		//Actions action = new Actions(driver);
		WebElement contract=driver.findElement(By.xpath("//td[contains(text(),'"+contractvalue+"')]/span"));
		contract.click();
		PhoenixPageUtility.clickOnElement(driver, nextstep);
		//action.doubleClick(contract).build().perform();
	}
}
