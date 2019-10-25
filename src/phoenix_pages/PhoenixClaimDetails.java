package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixClaimDetails {
	
	public WebDriver driver ;
	public WebDriverWait wait;
	
	@FindBy(xpath="//label[contains(text(),'Section Selection *')]/..//select") WebElement selection;
	@FindBy(xpath="//label[contains(text(),'Line of Business *')]/..//select") WebElement lob;
	@FindBy(xpath="//label[contains(text(),'Claim Nature')]/..//input") WebElement claimnaturedrop;
	@FindBy(xpath="//label[contains(text(),'Loss Date *')]/..//input") WebElement lossdate;
	@FindBy(xpath="//label[contains(text(),'Our Report Date *')]/..//input") WebElement ourreportdate;
	@FindBy(xpath="//label[contains(text(),'Cedant Report Date *')]/..//input") WebElement cedantreportdate;
	@FindBy(xpath="//label[contains(text(),'Cedant Claim Ref ')]/..//input") WebElement cedantclaimref;
	@FindBy(xpath="//label[contains(text(),'Insured *')]/..//input") WebElement insured;
	@FindBy(xpath="//label[contains(text(),'Broker Claim Ref')]/..//input") WebElement brokerclaimref;
	@FindBy(xpath="//label[contains(text(),'Claimant *')]/following::input[1]") WebElement claimant;
	@FindBy(xpath="//label[contains(text(),'Examiner *')]/..//input") WebElement examinerdrop;
	@FindBy(xpath="//label[contains(text(),'Diary Frequency ')]/..//input") WebElement diaryfrequency;
	@FindBy(xpath="//label[contains(text(),'Injury')]/..//input") WebElement injurydrop;
	@FindBy(xpath="//label[contains(text(),'Description Of Loss')]/..//textarea") WebElement descriptionofloss;
	@FindBy(xpath="//label[contains(text(),' Salvage/Subro ')]/..//div") WebElement checksalvage;
	@FindBy(xpath="//button[text()=' Next Step ']") WebElement nextbutton;


	public PhoenixClaimDetails(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void setSelection(String selectionvalue ) {
		PhoenixPageUtility.clickOnElement(driver, selection);
		//WebElement selectiondrop=driver.findElement(By.xpath("//label[contains(text(),'Section Selection *')]/..//select/option[text()='"+selectionvalue+"']"));
		
		WebElement selectiondrop=driver.findElement(By.xpath("//option[text()='"+selectionvalue+"']"));
		PhoenixPageUtility.clickOnElement(driver, selectiondrop);
	}
	
	public void SetLineOfBusiness(String lobvalue ) {
		PhoenixPageUtility.clickOnElement(driver, lob);
		WebElement lobdrop=driver.findElement(By.xpath("//label[contains(text(),'Line of Business *')]/..//select/option[text()='"+lobvalue+"']"));
		PhoenixPageUtility.clickOnElement(driver, lobdrop);
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
	
	public void setCedantClaimRef(String cedantrefvalue) {
		cedantclaimref.sendKeys(cedantrefvalue);
	}
	
	public void setInsured(String insuredvalue) {
		insured.sendKeys(insuredvalue);
	}
	
	public void setClaimant(String claimantvalue) {
		claimant.sendKeys(claimantvalue);
	}
	
	public void setBokerclaimref(String brokerclaimrefvalue) {
		brokerclaimref.sendKeys(brokerclaimrefvalue);
	}
	
	public void setExaminer(String examinervalue) {
		examinerdrop.sendKeys(examinervalue);
	}
	
	public void setdiaryfrequency(String diaryfequencyvalue) {
		diaryfrequency.clear();
		diaryfrequency.sendKeys(diaryfequencyvalue);
	}
	
	public void setInjury(String injuryvalue) {
		injurydrop.sendKeys(injuryvalue);
	}
	
	public void setDescriptionofLoss(String descriptionvalue) {
		descriptionofloss.sendKeys(descriptionvalue);
	}
	
	public void checkSalvage() {
		PhoenixPageUtility.clickOnElement(driver, checksalvage); 
	}
	
	public void clickonNext() {
		PhoenixPageUtility.clickOnElement(driver, nextbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
}
