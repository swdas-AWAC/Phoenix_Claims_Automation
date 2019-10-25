package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixLocationDetails {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//label[contains(text(),'Country/Area')]/..//input") WebElement country;
	@FindBy(xpath="//label[contains(text(),'Address Line 1')]/..//input") WebElement addressline1;
	@FindBy(xpath="//label[contains(text(),'State/Province')]/..//input") WebElement state;
	@FindBy(xpath="//label[contains(text(),'City')]/..//input") WebElement city;
	@FindBy(xpath="//label[contains(text(),'Postal Code')]/..//input") WebElement postalcode;
	@FindBy(xpath="//button[text()=' Next Step ']") WebElement nextbutton;
	
	public PhoenixLocationDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void setCountry(String countryvalue) {
		country.sendKeys(countryvalue);
	}
	
	public void setAddressLine1(String address1value) {
		addressline1.sendKeys(address1value);
	}
	
	public void setState(String statevalue) {
		state.sendKeys(statevalue);
	}
	
	public void setCity(String cityvalue) {
		city.sendKeys(cityvalue);
	}
	
	public void setPostalcode(String postalcodevalue) {
		postalcode.sendKeys(postalcodevalue);
	}
	
	public void clickonNext() {
		PhoenixPageUtility.clickOnElement(driver, nextbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
}


