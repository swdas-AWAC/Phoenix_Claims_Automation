package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixPolicyDetails {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//label[contains(text(),'Effective')]/..//input") WebElement effectivedate;
	@FindBy(xpath="//label[contains(text(),'Expiry')]/..//input") WebElement expirydate;
	@FindBy(xpath="//label[contains(text(),'Policy Type')]/..//input") WebElement policytype;
	@FindBy(xpath="//label[contains(text(),'Policy Limit')]/..//input") WebElement policylimit;
	@FindBy(xpath="//label[contains(text(),'Policy ALAE')]/..//input") WebElement policyalae;
	@FindBy(xpath="//button[text()=' Next Step ']") WebElement nextbutton;
	
	public PhoenixPolicyDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void setEffective(String effdatevalue) {
		effectivedate.sendKeys(effdatevalue);
	}
	
	public void setExpiry(String expdatevalue) {
		expirydate.sendKeys(expdatevalue);
	}
	
	public void setPolicyType(String policytypevalue) {
		policytype.sendKeys(policytypevalue);
	}
	
	public void setPolicyLimit(String policylimitvalue) {
		policylimit.sendKeys(policylimitvalue);
	}
	
	public void setpolicyAlae(String policyalaevalue) {
		policyalae.sendKeys(policyalaevalue);
	}
	
	public void clickonNext() {
		PhoenixPageUtility.clickOnElement(driver, nextbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
}
