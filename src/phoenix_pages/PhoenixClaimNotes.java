package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixClaimNotes {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//a[text()='Notes']") WebElement notestab;
	@FindBy(xpath="//h3[text()='Write a note']/..//textarea") WebElement notetext;
	@FindBy(xpath="//button[text()=' Post ']") WebElement postbutton;
	@FindBy(xpath="//a[text()='Aad']") WebElement AADtab;
	
	public PhoenixClaimNotes(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void clickonNotesTab() {
		PhoenixPageUtility.clickOnElement(driver, notestab); 
	}
	
	public void setNotes(String notevalue) {
		notetext.sendKeys(notevalue);
	}
	
	public void clickonPostbutton() {
		PhoenixPageUtility.clickOnElement(driver, postbutton); 
	}
	
	public void clickonAADTab() {
		PhoenixPageUtility.clickOnElement(driver, AADtab); 
	}
}
