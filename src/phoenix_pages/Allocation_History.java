package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Allocation_History {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//a[contains(text(),'Allocations')]") WebElement allocationtab;
	
	public Allocation_History(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void ClickOnAllocationTab()  {
		PhoenixPageUtility.clickOnElement(driver, allocationtab); 
	}
}
