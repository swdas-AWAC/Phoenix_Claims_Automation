package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Searchclaim {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//a[text()=' Claim Search']") WebElement claimsearch;
	@FindBy(xpath="//label[text()='Contract Number or Claim Number']/..//input") WebElement claimnumber;
	@FindBy(xpath="//label[text()='UW Year']/..//input") WebElement uwyear;
	@FindBy(xpath="//label[text()='Ceding Company']/..//input") WebElement cedingcomp;
	@FindBy(xpath="//label[text()='Broker Name']/..//input") WebElement brokername;
	@FindBy(xpath="//label[text()='Broker Claim Number']/..//input") WebElement brokerclaimnumber;
	@FindBy(xpath="//label[text()='Date of Loss']/..//input") WebElement dateofloss;
	@FindBy(xpath="//label[text()='Name Insured']/..//input") WebElement nameinsured;
	@FindBy(xpath="//label[text()='Claimant']/..//input") WebElement claimant;
	@FindBy(xpath="//label[text()='Examiner']/..//input") WebElement examiner;
	@FindBy(xpath="//label[text()='Claim Type']/..//select") WebElement claimtype;	
	@FindBy(xpath="//form//span[@class='fa fa-search']") WebElement searchbutton;
	@FindBy(xpath="//a[text() = 'Select Claim' and not(contains(@class, 'disabled'))]") WebElement selectclaim;
	
	public Searchclaim(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void clickonClaim(String claimno) {
		//Actions action = new Actions(driver);
		WebElement Claim=driver.findElement(By.xpath("//td[text() = '"+claimno+"' and not(contains(@class, ' '))]"));
		wait.until(ExpectedConditions.elementToBeClickable(Claim));
		PhoenixPageUtility.clickOnElement(driver, Claim);
		wait.until(ExpectedConditions.elementToBeClickable(selectclaim));
		PhoenixPageUtility.clickOnElement(driver, selectclaim);
		//action.doubleClick(Claim).build().perform();
	}
	public void ClickOnSearch() {
		PhoenixPageUtility.clickOnElement(driver, searchbutton);
	}
	public void SetCedingCompany(String companyvalue) {
		PhoenixPageUtility.clickOnElement(driver, cedingcomp);
		cedingcomp.sendKeys(companyvalue);
	}
	public void SetBrokerName(String brokernamevalue) {
		PhoenixPageUtility.clickOnElement(driver, brokername);
		brokername.sendKeys(brokernamevalue);
	}
	public void SetBrokerclaimNumber(String brokerclaimnumvalue) {
		PhoenixPageUtility.clickOnElement(driver, brokerclaimnumber);
		brokerclaimnumber.sendKeys(brokerclaimnumvalue);
	}
	public void SetDateOfLoss(String lossdatevalue) {
		PhoenixPageUtility.clickOnElement(driver, dateofloss);
		dateofloss.sendKeys(lossdatevalue);
	}
	public void SetNameInsured(String nameinsuredvalue) {
		PhoenixPageUtility.clickOnElement(driver, nameinsured);
		nameinsured.sendKeys(nameinsuredvalue);
	}
	public void SetClaimant(String claimantvalue) {
		PhoenixPageUtility.clickOnElement(driver, claimant);
		claimant.sendKeys(claimantvalue);
	}
	public void SetExaminer(String examinervalue) {
		PhoenixPageUtility.clickOnElement(driver, examiner);
		examiner.sendKeys(examinervalue);
		//Select Examiner = new Select(driver.findElement(By.xpath("//label[text()='Examiner']/..//input")));
		//Examiner.selectByVisibleText(examinervalue);
	}
	public void SetClaimType(String claimtypevalue) {
		PhoenixPageUtility.clickOnElement(driver, claimtype);
		WebElement selectclaimtype = driver.findElement(By.xpath("//label[text()='Claim Type']/..//select/option[text()='"+claimtypevalue+"']"));
		PhoenixPageUtility.clickOnElement(driver, selectclaimtype);
	}
	public void ClickOnClaimSearch() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, claimsearch);
	}
	public void SetClaimnumber(String claimnumbervalue) {
		PhoenixPageUtility.clickOnElement(driver, claimnumber);
		claimnumber.sendKeys(claimnumbervalue);
	}	
	public void SetUWyear(String yearvalue) {
		PhoenixPageUtility.clickOnElement(driver, uwyear);
		uwyear.sendKeys(yearvalue);
	}
}
