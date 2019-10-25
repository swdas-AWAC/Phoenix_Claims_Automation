package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Accounting_HomePage {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//img[@src='assets/images/awac_home_logo.png']") WebElement logo;
	@FindBy(xpath = "//a[text()=' Go To Accounting ']") WebElement accountingtab;
	@FindBy(xpath="//label[text()='Year']/..//input") WebElement year;
	@FindBy(xpath="//label[text()='Ceding Company Name or Contract Code']/..//input") WebElement contractcode;
	@FindBy(xpath="//a[@class='btn btn-primary btn_AW_red search-btn']") WebElement searchbutton;
	
	public Accounting_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void ClickOnLogo() {
		PhoenixPageUtility.clickOnElement(driver, logo);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void ClickOnAccountingTab() {
		PhoenixPageUtility.clickOnElement(driver, accountingtab);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void SetContractCode(String contractvalue) {
		PhoenixPageUtility.clickOnElement(driver, contractcode);
		contractcode.sendKeys(contractvalue);
	}
	
	public void ClickOnSearch() {
		PhoenixPageUtility.clickOnElement(driver, searchbutton);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void SetYear(String yearvalue) {
		PhoenixPageUtility.clickOnElement(driver, year);
		year.sendKeys(yearvalue);
	}
	
	public void SelectContract() {
		Actions action = new Actions(driver);
		WebElement contract=driver.findElement(By.xpath("//span[text()='T']"));
		action.doubleClick(contract).build().perform();
	}
}
