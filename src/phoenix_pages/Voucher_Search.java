package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Voucher_Search {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//a[text()=' Voucher Search']") WebElement vouchersearchtab;
	@FindBy(xpath = "//label[text()='Voucher#']/../input") WebElement vouchernumber;
	@FindBy(xpath = "//a[text()=' Search ']") WebElement searchbutton;
	@FindBy(xpath = "//a[text()='Select Voucher']") WebElement selectvoucher;
	
	public Voucher_Search(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	public void ClickOnVoucherSearch() {
		PhoenixPageUtility.clickOnElement(driver, vouchersearchtab);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void SetVoucherNumber(String vouchernumbervalue) {
		vouchernumber.sendKeys(vouchernumbervalue);
	}
	public void SetVoucherType() {
		Select vouchertype = new Select(driver.findElement(By.xpath("//label[text()='Voucher Type']/../select")));
		vouchertype.selectByVisibleText("Receipt");
	}
	public void SetBranch() {
		Select branch = new Select(driver.findElement(By.xpath("//label[text()='Branch']/../select")));
		branch.selectByVisibleText("BDA");
	}
	public void SetPayor() {
		Select payor = new Select(driver.findElement(By.xpath("//label[text()='Payor/Payee']/../select")));
		payor.selectByVisibleText("ABA SEGUROS SA");
	}
	public void ClickOnSearch() {
		PhoenixPageUtility.clickOnElement(driver, searchbutton);
	}
	public void ClickOnSearchedVoucher(String vouchervalue) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		WebElement searchedvoucher = driver.findElement(By.xpath("//td[contains(text(),'"+vouchervalue+"')]/.."));
		PhoenixPageUtility.clickOnElement(driver, searchedvoucher);
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, selectvoucher);
		} 
		
	
	
}
