package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixCopyClaim {

	public WebDriver driver;
	public WebDriverWait wait;
	
//	@FindBy(xpath="//button[text()=' Copy ']/span") WebElement copybutton;
//	@FindBy(xpath="//a[text()='Same Contract']") WebElement samecontract;
	@FindBy(xpath="//div[@id = 'tabClaim']//span[@title = 'Same Contract']/ancestor::button[1]") WebElement samecontractButton;
	@FindBy(xpath="//label[contains(text(),'Claimant *')]/following::input[1]") WebElement Claimant;
	@FindBy(xpath="//button[text()=' Next Step ']") WebElement nextbutton;
	@FindBy(xpath="//button[text()=' Create Claim ']") WebElement createclaimbutton;
	@FindBy(xpath="//div[@id = 'tabClaim']//span[@title = 'Different Contract']/ancestor::button[1]") WebElement DiffcontractButton;
	@FindBy(xpath="//label[contains(text(),'Ceding Company Name or Contract Code')]/following::input[1]") WebElement contractno;
	@FindBy(xpath="//label[contains(text(),'Year')]/following::input[1]") WebElement UWYR;
	@FindBy(xpath="//table[@class='table table-hover table_sw']//tbody//td[2]") WebElement selectclaim;
	@FindBy(xpath="//span[@class='fa fa-search']") WebElement Searchbutton;
	
	
	public PhoenixCopyClaim(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	
	public void clickOnSameContract() {
		try {
			wait.until(ExpectedConditions.visibilityOf(samecontractButton));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(samecontractButton).build().perform();
	        samecontractButton.click();
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	            clickOnSameContract();
	        }

	}
	
	public void setClaimant(String claimantvalue) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, Claimant);
		Claimant.sendKeys(claimantvalue);
	}
	
	public void clickonNext() {
		PhoenixPageUtility.clickOnElement(driver, nextbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void clickonCreateClaim() {
		PhoenixPageUtility.clickOnElement(driver, createclaimbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void clickOnDiffcontract() {
		try {
			wait.until(ExpectedConditions.visibilityOf(DiffcontractButton));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(DiffcontractButton).build().perform();
	        DiffcontractButton.click();
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	            clickOnDiffcontract();
	        }

	}
	
	public void setcontractno(String contractvalue) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, contractno);
		contractno.sendKeys(contractvalue);
	}
	
	public void setUWYR(String uwyr) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, UWYR);
		UWYR.sendKeys(uwyr);
		}
	
	public void clickonSearchbutton() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, Searchbutton);
	}
	public void clickonselectclaim() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, selectclaim);
	}
	
	
	
	
}
