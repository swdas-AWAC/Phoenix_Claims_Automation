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

public class OpenItem_Search {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//a[contains(text(),'Open Items')]") WebElement openitemtab;
	@FindBy(xpath = "//label[contains(text(),'Ceding Company')]/../input") WebElement cedingcomp;
	@FindBy(xpath = "//label[contains(text(),'Broker')]/../input") WebElement broker;
	@FindBy(xpath = "//label[contains(text(),'Contract Number')]/../input") WebElement contractno;
	@FindBy(xpath = "//label[contains(text(),'Underwriting Year')]/../input") WebElement uwyear;
	@FindBy(xpath = "//label[contains(text(),'Due Date')]/../input") WebElement duedate;
	@FindBy(xpath = "//a[text()=' Search ']") WebElement searchbutton;
	@FindBy(xpath = "//div[@class='sticky-container currencyAmount1']//div[@class='tableRelativeCont']/table/tbody/tr[1]/td[1]//label") WebElement checkopenitem;
	@FindBy(xpath = "//div[@class='sticky-container currencyAmount1']//div[@class='tableRelativeCont']/table/tbody/tr/td[2]//i") WebElement plussign;
	@FindBy(xpath = "//label[text()='Applied Amount']/..//input") WebElement appliedamount;
	@FindBy(xpath = "//label[text()='Applied Over Short Amount']") WebElement appliedovershort;
	@FindBy(xpath = "//span[@title='Match']") WebElement match;
	
	public OpenItem_Search(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	public void clickonMatch() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		Actions actions = new Actions(driver);
		actions.moveToElement(match).build().perform();
		PhoenixPageUtility.clickOnElement(driver, match); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void SetAppliedAmount() {
		appliedamount.sendKeys("500");
		PhoenixPageUtility.clickOnElement(driver, appliedovershort);
	}
	public void SetAppliedType() {
		//PhoenixPageUtility.clickOnElement(driver, appliedtype);
		Select appliedtype = new Select(driver.findElement(By.xpath("//label[text()='Applied Type']/..//select")));
		appliedtype.selectByVisibleText("Premium");
	}
	public void SelectOpenItem() throws InterruptedException {
		Thread.sleep(2000);
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, checkopenitem);
	}
	public void SelectPlusSign() {
		PhoenixPageUtility.clickOnElement(driver, plussign);
	}
	public void ClickOnSearch() {
		PhoenixPageUtility.clickOnElement(driver, searchbutton);
	}
	public void SetUWYear(String contractyearvalue) {
		uwyear.sendKeys(contractyearvalue);
	}
	public void SetContractNumber(String contractvalue) {
		contractno.sendKeys(contractvalue);
	}
	public void ClickOnOpenItemTab() {
		PhoenixPageUtility.clickOnElement(driver, openitemtab);
	}
	public void SetBranch() {
		Select branch = new Select(driver.findElement(By.xpath("//label[text()='Branch']/../select")));
		branch.selectByVisibleText("BDA");
	}
	public void SetCedingCompany(String cedingcompvalue) {
		cedingcomp.sendKeys(cedingcompvalue);
	}
	public void SetBroker(String brokervalue) {
		broker.sendKeys(brokervalue);
	}
	public void SetDueDate(String duedatevalue) {
		duedate.sendKeys(duedatevalue);
		
	}
	public void SetTransactionType() {
		Select transactype = new Select(driver.findElement(By.xpath("//label[text()='Transaction Type']/../select")));
		transactype.selectByVisibleText("Deposit");
	}
	
}
