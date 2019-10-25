package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class SavingsAndDisputedRecord_Creation {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	
	@FindBy(xpath="//label[contains(text(),'Type')]/following::select[1]") WebElement Type;
	@FindBy(xpath="//label[contains(text(), 'Invoice') and not(contains(text(), 'Invoice Date *'))]/following::input[1]") WebElement Invoice;
	@FindBy(xpath="//label[contains(text(), 'Invoice Date *')]/following::input[1]") WebElement InvoiceDate;
	@FindBy(xpath="//label[contains(text(), 'Resolution') and not (contains(text(), 'Resolution Amount'))]/following::select[1]") WebElement Resolution;
	@FindBy(xpath="//label[(contains(text(), 'Resolution Amount'))]/following::input[1]") WebElement ResolutionAmount;
	@FindBy(xpath="//label[(contains(text(), 'Total Amount *'))]/following::input[1]") WebElement TotalAmount;
	@FindBy(xpath="//label[(contains(text(), 'Currency *'))]/following::select[1]") WebElement Currency;
	@FindBy(xpath="//label[(contains(text(), 'Reason'))]/following::select[1]") WebElement Reason;
	@FindBy(xpath="//button[contains(text(),' Create Disputed')]") WebElement CreateDisputed_Button;
	@FindBy(xpath="//label[contains(text(), 'Savings Amount')]/../p") WebElement savingsamount;
	@FindBy(xpath="//label[@class='segmented-control__label first active']") WebElement status;
	
	
	public SavingsAndDisputedRecord_Creation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	
	public void ClickOnType(String option ) {
		wait.until(ExpectedConditions.elementToBeClickable(Type));
		PhoenixPageUtility.clickOnElement(driver, Type);
		String type = "//option[contains(text(),'"+option+"')]";
		WebElement op = driver.findElement(By.xpath(type));
		op.click();
	}
	
	
	public void SetInvoice(String invoice) {
		wait.until(ExpectedConditions.visibilityOf(Invoice));
		Invoice.clear();
		Invoice.sendKeys(invoice);
	
	}
	
	public void SetInvoiceDate(String invoiceDate) {
		wait.until(ExpectedConditions.elementToBeClickable(InvoiceDate));
		InvoiceDate.clear();
		InvoiceDate.sendKeys(invoiceDate);
	}
	
	
	public void ClickOnResolution(String option1 ) {
		wait.until(ExpectedConditions.elementToBeClickable(Resolution));
		PhoenixPageUtility.clickOnElement(driver, Resolution);
		String resolution = "//option[contains(text(),'"+option1+"')]";
		WebElement op1 = driver.findElement(By.xpath(resolution));
		op1.click();
	}
	
	
	public void SetTotalAmount(String totalAmount) {
		wait.until(ExpectedConditions.elementToBeClickable(TotalAmount));
		TotalAmount.clear();
		TotalAmount.sendKeys(totalAmount);
	}
	
	public void SetResolutionAmount(String resolutionAmount) {
		if(!resolutionAmount.contains("NA")){
		wait.until(ExpectedConditions.elementToBeClickable(ResolutionAmount));
		ResolutionAmount.clear();
		ResolutionAmount.sendKeys(resolutionAmount);
		driver.findElement(By.xpath("//body")).click();
		}
		
	}
	
	
	public String GetSavingsAmount() throws InterruptedException {
		Thread.sleep(2000);
		String savingamount=savingsamount.getText();
		savingamount = savingamount.replaceAll(",", "");
		//String SavingAmount = Double.parseDouble(savingamount);
		return savingamount;
	}
	
	public String GetStatus() {
		
		 String Status= status.getText().trim();
		 System.out.println(Status);
		return Status;
		
	}
	
	
	public void ClickOnCurrency(String option3 ) {
		wait.until(ExpectedConditions.elementToBeClickable(Currency));
		PhoenixPageUtility.clickOnElement(driver, Currency);
		String currency = "//option[contains(text(),'"+option3+"')]";
		WebElement op3 = driver.findElement(By.xpath(currency));
		op3.click();
	}
	
	public void ClickOnReason(String option4 ) {
		wait.until(ExpectedConditions.elementToBeClickable(Reason));
		PhoenixPageUtility.clickOnElement(driver, Reason);
		String reason = "//option[contains(text(),'"+option4+"')]";
		WebElement op4 = driver.findElement(By.xpath(reason));
		op4.click();
	}
	
	
	public void ClickOnCreateDisputed() {
		wait.until(ExpectedConditions.elementToBeClickable(CreateDisputed_Button));
		PhoenixPageUtility.clickOnElement(driver, CreateDisputed_Button);
	
	}

	
}
