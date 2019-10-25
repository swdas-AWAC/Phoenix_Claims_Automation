package phoenix_pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import javax.xml.xpath.XPath;

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

public class Activities_Flow {
	public WebDriver driver ;
	public WebDriverWait wait;
	
	@FindBy(xpath="//a[text()=' Activities']") WebElement activitiestab;
	@FindBy(xpath="//button[text()=' Start Workflow ']") WebElement startoverflowbutton;
	@FindBy(xpath="//label[text()=' browse ']") WebElement browsebutton;
	@FindBy(xpath="//button[contains(text(),'Upload Files')]") WebElement uploadbutton;
	@FindBy(xpath="//a[contains(text(),' Received Date ')]") WebElement receiveddate;
	@FindBy(xpath="//a[contains(text(),'Start Date')]//span") WebElement sortstartdatedown;
	@FindBy(xpath="//table[@class='table table-hover table_sw ng-star-inserted']//tbody//tr[1]/td[1]/div/input") WebElement docCheck1;
	@FindBy(xpath="//table[@class='table table-hover table_sw ng-star-inserted']//tbody//tr[2]/td[1]/label/div") WebElement docCheck2;
	@FindBy(xpath="//table[contains(@class,'table table-hover table_sw')]//tbody//tr[1]//i[@class='far fa-folder-open']") WebElement open;
	@FindBy(xpath="//table[contains(@class,'table table-hover table_sw')]//tbody//tr[1]//i[@class='far fa-eye']") WebElement view;
	@FindBy(xpath="//button[text()= ' Search Claim']") WebElement searchclaim;
	@FindBy(xpath="//form[contains(@class, 'row search_bar')]/following::label[contains(text(), 'or Claim Number')]/..//input") WebElement claim;
	@FindBy(xpath="//div[@class='tab-pane fade active in']//label[contains(text(), 'Underwriting Year')]/..//input") WebElement Uwyear;
	@FindBy(xpath="//form[contains(@class, 'earch_bar')]/following::a[contains(@class,'btn-primary btn_AW_red search-btn')][3]") WebElement searchbutton;
	@FindBy(xpath="//span[text()=' O ']") WebElement claimlink;
	@FindBy(xpath="//div[@class='ng-star-inserted']//following::a[contains(text(),'Select Claim')]") WebElement SelectClaimButton;
	@FindBy(xpath="//button[contains(text(),' Save ')]") WebElement savebutton;
	@FindBy(xpath="//label[text()='Select Category']/..//select") WebElement selectcategory;
	@FindBy(xpath="//label[text()='Amount']/following::input[1]") WebElement amount;
	@FindBy(xpath="//div[@class='row bckg_row ng-star-inserted']//label[contains(text(),'Contract Number')]/following::input[1]") WebElement contractno;
	@FindBy(xpath="//div[@class='row bckg_row ng-star-inserted']//label[contains(text(),'UW Year')]/following::input[1]") WebElement uwyear;
	@FindBy(xpath="//label[text()='Currency']/..//select") WebElement currency;
	@FindBy(xpath="//label[text()='Ceding Company Name']/..//select") WebElement cedingcompany;
	@FindBy(xpath="//label[text()='Claim Examiner']/..//select") WebElement claimexaminer;
	@FindBy(xpath="//button[contains(text(),'New Claim')]") WebElement newclaimbutton;
	@FindBy(xpath="//i[@class='fas fa-eye pull-right']") WebElement viewbutton;
	@FindBy(xpath="//span[text()='×']") WebElement closemark;
	@FindBy(xpath="//span[@class='fa fa-search']/..") WebElement search;
	@FindBy(xpath="//table[@class='table table-hover table_sw']/tbody/tr[1]") WebElement contract;
	@FindBy(xpath="//label[contains(text(),'Loss Date *')]/..//input") WebElement lossdate;
	@FindBy(xpath="//label[contains(text(),'Our Report Date *')]/..//input") WebElement ourreportdate;
	@FindBy(xpath="//label[contains(text(),'Cedant Report Date *')]/..//input") WebElement cedantreportdate;
	@FindBy(xpath="//label[contains(text(),'Claim Nature')]/..//input") WebElement claimnaturedrop;
	@FindBy(xpath="//label[contains(text(),'Cedant Claim Ref ')]/..//input") WebElement cedantclaimref;
	@FindBy(xpath="//label[contains(text(),'Broker Contract Ref')]/..//input") WebElement BrokerContractRef;
	@FindBy(xpath="//label[contains(text(),'Insured *')]/..//input") WebElement insured;
	@FindBy(xpath="//label[contains(text(),'Broker Claim Ref')]/..//input") WebElement brokerclaimref;
	@FindBy(xpath="//label[contains(text(),'Claimant *')]/..//input") WebElement claimant;
	@FindBy(xpath="//button[text()=' Next Step ']") WebElement nextbutton;
	@FindBy(xpath="//label[contains(text(),'Effective')]/..//input") WebElement effectivedate;
	@FindBy(xpath="//label[contains(text(),'Expiry')]/..//input") WebElement expirydate;
	@FindBy(xpath="//label[contains(text(),'Policy Type')]/..//input") WebElement policytype;
	@FindBy(xpath="//label[contains(text(),'Policy Limit')]/..//input") WebElement policylimit;
	@FindBy(xpath="//label[contains(text(),'Policy ALAE')]/..//input") WebElement policyalae;
	@FindBy(xpath="//label[contains(text(),'Country/Area')]/..//input") WebElement country;
	@FindBy(xpath="//label[contains(text(),'Address Line 1')]/..//input") WebElement addressline1;
	@FindBy(xpath="//label[contains(text(),'State/Province')]/..//input") WebElement state;
	@FindBy(xpath="//label[contains(text(),'City')]/..//input") WebElement city;
	@FindBy(xpath="//label[contains(text(),'Postal Code')]/..//input") WebElement postalcode;
	@FindBy(xpath="//button[text()=' Create Claim ']") WebElement createclaimbutton;
	@FindBy(xpath="//button[contains(text(),'Save & Continue')]") WebElement SaveContinuebutton;
	@FindBy(xpath="//table[@class='table table-hover table_sw ng-star-inserted']//tbody//tr[1]/td[2]/a") WebElement claimlinktoaccept;
	@FindBy(xpath="//button[text()=' Accept ']") WebElement acceptbutton;
	@FindBy(xpath=" //label[text()=' Completed ']") WebElement completedtab;
	//private final String examiner = "//label[text()='Examiner *']/../div/input";
	@FindBy(xpath="//label[text()='Examiner *']/../div/input") WebElement examiner;
	@FindBy(xpath="//button[text()=' Close ']") WebElement closeservererror;
	@FindBy(xpath="//button[text()=' Cancel ']") WebElement cancel;
	@FindBy(xpath="//div[@class='tab-pane fade active show']//following::label[text()='Broker Claim Number']/..//input") WebElement BrokerClaimNo;
	@FindBy(xpath="//label[text()='Claim Number']/following::input[1]") WebElement Claimnumber;
	@FindBy(xpath="//p[contains(text(),' Claim Number: ')]/button[1]") WebElement Claimlink_ActivityOrg;
	@FindBy(xpath="//div[@class='modal-dialog modal-xl']//h4") WebElement ActivityOrg_PageHeader;
	@FindBy(xpath="//div[@id='activities']//following::label[contains(text(),'Contract Number or Claim Number')]/following::input[1]") WebElement ContractNumberorClaimNumber_Search;
	@FindBy(xpath="//div[@id='activities']//following::label[contains(text(),'UW Year')]/following::input[1]") WebElement UWYear_Search;
	@FindBy(xpath="//div[@class='main-content claim_selection']//label[contains(text(),'Ceding Company Name or Contract Code')]/following::input[1]") WebElement CedCom_ContractCode_Search;
	@FindBy(xpath="//div[@class='main-content claim_selection']//label[contains(text(),'UW Year')]/following::input[1]") WebElement uw_Search;
	@FindBy(xpath="//div[@class='form-group']//label[contains(text(),'Claim No')]/following::p[1]") WebElement Claimno_ClaimAbstract;
	@FindBy(xpath="//button[contains(text(),' No')]") WebElement NoButton;
	@FindBy(xpath="//button[contains(text(),' Yes')]") WebElement YesButton;
	
	public Activities_Flow(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void ClickOncancel() {
		wait.until(ExpectedConditions.elementToBeClickable(cancel));
		PhoenixPageUtility.clickOnElement(driver, cancel);
	}
	public void ClickOnCompletedTab() throws InterruptedException {
		Thread.sleep(2000);
		PhoenixPageUtility.clickOnElement(driver, completedtab);
	}
	public void ClickOnCloseServer() {
		wait.until(ExpectedConditions.elementToBeClickable(closeservererror));
		PhoenixPageUtility.clickOnElement(driver, closeservererror);
	}
	public void ClickOnAccept() {
		wait.until(ExpectedConditions.elementToBeClickable(acceptbutton));
		PhoenixPageUtility.clickOnElement(driver, acceptbutton);
	}
	public void ClickOnClaimLink() {
		PhoenixPageUtility.clickOnElement(driver, claimlinktoaccept);
	}
	public void clickonCreateClaim() {
		PhoenixPageUtility.clickOnElement(driver, createclaimbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
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
	public void setCedantClaimRef(String cedantrefvalue) {
		cedantclaimref.click();
		cedantclaimref.sendKeys(cedantrefvalue);
	}
	public void setInsured(String insuredvalue) {
		insured.sendKeys(insuredvalue);
	}
	public void setClaimant(String claimantvalue) {
		claimant.sendKeys(claimantvalue);
	}
	public void setBokerclaimref(String brokerclaimrefvalue) {
		brokerclaimref.click();
		brokerclaimref.sendKeys(brokerclaimrefvalue);
	}
	public void setClaimNature(String claimnaturevalue) {
		claimnaturedrop.sendKeys(claimnaturevalue);
	}
	public void setLossdate(String lossdatevalue) {
		lossdate.sendKeys(lossdatevalue);
	}	
	public void setourReportDate(String ourdatevalue) {
		ourreportdate.sendKeys(ourdatevalue);
	}
	public void setCedantReportDate(String cedantreportdatevalue) {
		cedantreportdate.sendKeys(cedantreportdatevalue);
	}
	public void SelectContract() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		Actions action = new Actions(driver);
		action.doubleClick(contract).build().perform();
	}
	public void ClickOnSearch() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, search);
	}
	public void ClickOnClose() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, closemark);
	}
	public void ClickOnView() throws InterruptedException {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, view);
		Thread.sleep(12000);
	}
	public void ClickOnNewClaim() {
		PhoenixPageUtility.clickOnElement(driver, newclaimbutton);
	}
	public void SetSelectCategory(String categoryvalue) throws InterruptedException {
		//PhoenixPageUtility.clickOnElement(driver, selectcategory);
		Select SelectCtegory = new Select(driver.findElement(By.xpath("//label[text()='Select Category']/..//select")));
		Thread.sleep(2000);
		SelectCtegory.selectByVisibleText(categoryvalue);
	}
	public void SetAmount(String amountvalue) {
		amount.sendKeys(amountvalue);
	}
	public void SetContractNumber(String contractvalue) {
		contractno.sendKeys(contractvalue);
	}
	public void SetUnderwritingYear(String uwyearvalue) {
		uwyear.sendKeys(uwyearvalue);
	}
	public void SetCedingCompany(String cedingcompvalue) {
		PhoenixPageUtility.clickOnElement(driver, cedingcompany);
		WebElement CedingComp = driver.findElement(By.xpath("//option[contains(text(),'"+cedingcompvalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, CedingComp);
	}
	public void SetCurrency(String currencyvalue) {
		PhoenixPageUtility.clickOnElement(driver,currency );
		WebElement Currency = driver.findElement(By.xpath("//option[contains(text(),'"+currencyvalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, Currency);
	}
	public void SetExaminer(String examinervalue) {
		//PhoenixPageUtility.clickOnElement(driver, claimexaminer);
		//WebElement ClaimExaminer = driver.findElement(By.xpath("//option[contains(text(),'"+claimexaminervalue+"')]"));
		//PhoenixPageUtility.clickOnElement(driver, ClaimExaminer);
		//Select Examiner = new Select(driver.findElement(By.xpath(examiner)));
		//Examiner.selectByVisibleText(examinervalue);
		examiner.clear();
		examiner.sendKeys(examinervalue);
	}
	public void SetClaimExaminer(String claimexaminervalue) {
		PhoenixPageUtility.clickOnElement(driver, claimexaminer);
		WebElement ClaimExaminer = driver.findElement(By.xpath("//option[contains(text(),'"+claimexaminervalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, ClaimExaminer);
		//Select ClaimExaminer = new Select(driver.findElement(By.xpath(claimexaminer)));
		//ClaimExaminer.selectByVisibleText(claimexaminervalue);
	}
	public void ClickOnSave() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, savebutton);
	}
	public void SelectClaim() {
		Actions action = new Actions(driver);
		action.doubleClick(claimlink).build().perform();
	}
	public void ClickOnSearchButton() {
		PhoenixPageUtility.clickOnElement(driver, searchbutton);
	}
	public void SetUwYear(String yearvalue) {
		Uwyear.sendKeys(yearvalue);
	}
	public void SetClaim(String claimvalue) {
		claim.sendKeys(claimvalue);
	}
	public void ClickOnSearchClaim() throws InterruptedException {
		//PhoenixPageUtility.waitUntilPageActive(driver);
		Thread.sleep(4000);
		PhoenixPageUtility.clickOnElement(driver, searchclaim);
	}
	public void ClickOnOpenLink() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, open);
	}
	public void SelectDocument1() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, docCheck1);
	}
	public void SelectDocument2() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, docCheck2);
	}
	public void SortRecievedDate() throws InterruptedException {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, receiveddate);
		Thread.sleep(3000);
		PhoenixPageUtility.clickOnElement(driver, receiveddate);
		
	}
	
	public void setClaimNumber(String Claimno) {
		
		Claimnumber.sendKeys(Claimno);
	}
	
    public void ClickOnSelectClaimButton() {
    	
		wait.until(ExpectedConditions.visibilityOf(SelectClaimButton));
    	SelectClaimButton.click();
	}
    
public void ClickOnsaveContinueButton() {
    	
		wait.until(ExpectedConditions.visibilityOf(SaveContinuebutton));
		SaveContinuebutton.click();
	}

	public void ClickOnUpload() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, uploadbutton);
	}
	public void ClickOnActivitiesTab() {
		PhoenixPageUtility.clickOnElement(driver, activitiestab);
	}
	public void ClickOnStartOverFlow() {
		PhoenixPageUtility.clickOnElement(driver, startoverflowbutton);
	}
	public void UploadDocument(String filepath) throws AWTException, InterruptedException {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, browsebutton);
		Thread.sleep(3000);
		StringSelection path = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path,null);
		 
		 Robot robot=new Robot();
		 robot.keyPress(KeyEvent.VK_ENTER);
		 
		// Release Enter
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 
		// Press CTRL+V
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);
		 
		// Release CTRL+V
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 Thread.sleep(1000);
		        
		//Press Enter 
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void setBrokerClaimNo()
	{
		BrokerClaimNo.clear();
		BrokerClaimNo.sendKeys("   ");
	}
	
	public String GetPageHeader() {
		String Header= ActivityOrg_PageHeader.getText().trim();
		return Header;
	}
	
	public String GetClaimNoVisibility() {
		String Status= Claimnumber.getAttribute("Disabled");
		return Status;
	}
	
	public String GetClaimExaminerVisibility() {
		String Status= claimexaminer.getAttribute("Disabled");
		return Status;
	}
	
	public String GetBrokerClaimRefVisibility() {
		String Status= brokerclaimref.getAttribute("Disabled");
		return Status;
	}
	
	public String GetBrokerContractRefVisibility() {
		String Status= BrokerContractRef.getAttribute("Disabled");
		return Status;
	}
	
	public String GetcedingcompanyVisibility() {
		String Status= cedingcompany.getAttribute("Disabled");
		return Status;
	}
	
	public String GetcontractnoVisibility() {
		String Status= contractno.getAttribute("Disabled");
		return Status;
	}
	
	public String GetUwyearVisibility() {
		String Status= Uwyear.getAttribute("Disabled");
		return Status;
	}
	
	public String GetContractno() {
		String CntrctSearch= ContractNumberorClaimNumber_Search.getAttribute("ng-reflect-model");
		return CntrctSearch;
	}
	
	public String GetUWYr() {
		String UWYRSearch= UWYear_Search.getAttribute("ng-reflect-model");
		return UWYRSearch;
	}
	
	public void ClickonClaimlink_ActivityOrg() {
		Claimlink_ActivityOrg.click();
	}
	
	public String GetClaimno_ActivityOrg() {
		String ClNo= Claimlink_ActivityOrg.findElement(By.xpath("./span")).getText().trim();
		return ClNo;
	}
	public String GetContractCode_Search() {
		String CntrctcodeSearch= CedCom_ContractCode_Search.getAttribute("ng-reflect-model");
		return CntrctcodeSearch;
	}
	
	public String Getuw_Search() {
		String uw= uw_Search.getAttribute("ng-reflect-model");
		return uw;
	}
	public String GetClaimAbstract() {
		String ClaimAb= Claimno_ClaimAbstract.getText().trim();
		return ClaimAb;
	
	}
	
	public void clickonNo() {
		NoButton.click();
	}
	
	public void clickonYesbutton() {
		YesButton.click();
	}
}


