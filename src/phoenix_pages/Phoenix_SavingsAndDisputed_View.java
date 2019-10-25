package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Phoenix_SavingsAndDisputed_View {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	/*@FindBy(xpath="//h3[contains(text(),'Totals by Currency')]/following::i[@class='far fa-eye'][1]") WebElement Savings_View1;
	@FindBy(xpath="//h3[contains(text(),'Totals by Currency')]/following::i[@class='far fa-eye'][2]") WebElement Savings_View2;*/
	@FindBy(xpath="//button[contains(text(),' Back to Home')]") WebElement BackToHome_Button;
	@FindBy(xpath="//label[(contains(text(), 'Currency *'))]/following::select[1]") WebElement Currency;
	@FindBy(xpath="//label[contains(text(),'Type')]/following::select[1]") WebElement Type;
	@FindBy(xpath="//label[contains(text(), 'Invoice') and not(contains(text(), 'Invoice Date *'))]/following::input[1]") WebElement Invoice;
	@FindBy(xpath="//label[contains(text(), 'Invoice Date *')]/following::input[1]") WebElement InvoiceDate;
	@FindBy(xpath="//label[(contains(text(), 'Resolution Amount'))]/following::input[1]") WebElement ResolutionAmount;
	@FindBy(xpath="//label[(contains(text(), 'Total Amount *'))]/following::input[1]") WebElement TotalAmount;
	@FindBy(xpath="//label[contains(text(), 'Savings Amount')]/../p") WebElement savingsamount;
	@FindBy(xpath="//button[contains(text(),' Save')]") WebElement save_Button;
	@FindBy(xpath="//button[contains(text(),' Edit')]") WebElement Edit_Button;
	@FindBy(xpath="//button[contains(text(),' Cancel Edit')]") WebElement CancelEdit_Button;
	@FindBy(xpath="//button[contains(text(),' No')]") WebElement No_Button;
	
	
	public Phoenix_SavingsAndDisputed_View(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}


	
	/*public void ClickOnView1() {
		wait.until(ExpectedConditions.elementToBeClickable(Savings_View1));
		PhoenixPageUtility.clickOnElement(driver, Savings_View1);
	}*/
	
	public void ClickOnBackToHome() {
		wait.until(ExpectedConditions.elementToBeClickable(BackToHome_Button));
		PhoenixPageUtility.clickOnElement(driver, BackToHome_Button);
	}
	
	public void ClickOnCurrency(String option3 ) {
		wait.until(ExpectedConditions.elementToBeClickable(Currency));
		PhoenixPageUtility.clickOnElement(driver, Currency);
		String currency = "//option[contains(text(),'"+option3+"')]";
		WebElement op3 = driver.findElement(By.xpath(currency));
		op3.click();
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
	
	public void ClickOnSave() {
		wait.until(ExpectedConditions.elementToBeClickable(save_Button));
		PhoenixPageUtility.clickOnElement(driver, save_Button);
	}
	
	public void ClickOnEditButton() {
		wait.until(ExpectedConditions.elementToBeClickable(Edit_Button));
		PhoenixPageUtility.clickOnElement(driver, Edit_Button);
	}
	
	public void ClickOnCancelEdit() {
		wait.until(ExpectedConditions.elementToBeClickable(CancelEdit_Button));
		PhoenixPageUtility.clickOnElement(driver, CancelEdit_Button);
	}
	
	public void ClickOnNo() {
		wait.until(ExpectedConditions.elementToBeClickable(No_Button));
		PhoenixPageUtility.clickOnElement(driver, No_Button);
	}
	
	public String EditButton_Visibility() {
		String edit="Visible";
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Edit_Button));
		}
		catch(NoSuchElementException | TimeoutException e){
			
			edit="Not Visible";
		}
		return edit;
	
	}
}
