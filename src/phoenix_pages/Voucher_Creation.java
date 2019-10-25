package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Voucher_Creation {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//img[@src='assets/images/awac_home_logo.png']") WebElement logo;
	@FindBy(xpath = "//a[text()=' Go To Accounting ']") WebElement accountingtab;
	@FindBy(xpath = "//a[text()=' Voucher Search']") WebElement vouchersearchtab;
	@FindBy(xpath = "//button[text()=' New Voucher ']") WebElement newvoucherbutton;
	@FindBy(xpath = "//label[contains(text(),'Branch*')]/..//select") WebElement branch;
	@FindBy(xpath = "//label[contains(text(),'Bank Account Currency*')]/..//select") WebElement bankaccountcurrency;
	@FindBy(xpath = "//label[contains(text(),'Bank Account Number*')]/..//select") WebElement bankaccountnumber;
	@FindBy(xpath = "//label[text()='Bank Currency Amount*']/..//input") WebElement bankcurramount;
	@FindBy(xpath = "//label[text()='Bank Charges']/..//input") WebElement bankcharges;
	@FindBy(xpath = "//label[contains(text(),'Payment Type*')]/..//select") WebElement paymentType;
	@FindBy(xpath = "//label[text()='Reference#*']/..//input") WebElement referenceno;
	@FindBy(xpath = "//label[text()='Bank Transaction Date*']/..//input") WebElement banktransacdate;
	@FindBy(xpath = "//label[text()='Check Date']/..//input") WebElement checkdate;
	@FindBy(xpath = "//label[contains(text(),'Payor/Payee*')]/..//select") WebElement payor;
	@FindBy(xpath = "//label[contains(text(),'Business Department*')]/..//select") WebElement businessdepartment;
	@FindBy(xpath = "//label[contains(text(),'Accountant Responsable')]/..//select") WebElement accountantresponsble;
	@FindBy(xpath = "//label[text()='Comments']/..//textarea") WebElement comment;
	@FindBy(xpath = "//button[text()=' Create Voucher ']") WebElement CreateVoucherbutton;
	@FindBy(xpath = "//button[text()=' Add Currency ']") WebElement addcurrencybutton;
	
	public Voucher_Creation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void ClickOnAddCurrency() {
		PhoenixPageUtility.clickOnElement(driver, addcurrencybutton);
	}
	public void ClickOnCreateVoucher() {
		PhoenixPageUtility.clickOnElement(driver, CreateVoucherbutton);
	}
	public void SetComment(String commenttext) {
		comment.sendKeys(commenttext);
	}
	public void SetAccountantResponsable(String accresponsablevalue) {
		PhoenixPageUtility.clickOnElement(driver, accountantresponsble);
		WebElement AcctResponsable = driver.findElement(By.xpath("//option[contains(text(),'"+accresponsablevalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, AcctResponsable);
	}
	public void SetBusinessDepartment(String busideptvalue) {
		PhoenixPageUtility.clickOnElement(driver, businessdepartment);
		WebElement BusinessDepartment = driver.findElement(By.xpath("//option[contains(text(),'"+busideptvalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, BusinessDepartment);
	}
	public void SetPayor(String payorvalue) {
		PhoenixPageUtility.clickOnElement(driver, payor);
		WebElement PayorName = driver.findElement(By.xpath("//option[contains(text(),'"+payorvalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, PayorName);
	}
	public void SetCheckDate(String checkdatevalue) {
		checkdate.sendKeys(checkdatevalue);
	}
	public void SetbankTransacDate(String transacdatevalue) {
		banktransacdate.sendKeys(transacdatevalue);
	}
	public void SetReferenceNo(String referncenovalue) {
		referenceno.sendKeys(referncenovalue);
	}
	public void SetPaymentType(String paymenttype) {
		PhoenixPageUtility.clickOnElement(driver, paymentType);
		WebElement PaymentTypevalue = driver.findElement(By.xpath("//option[contains(text(),'"+paymenttype+"')]"));
		PhoenixPageUtility.clickOnElement(driver, PaymentTypevalue);
	}
	public void SetBankCharges(String bankchargevalue) {
		bankcharges.sendKeys(bankchargevalue);
	}
	public void SetBankCurrencyAmount(String bankcurramountvalue) {
		bankcurramount.sendKeys(bankcurramountvalue);
	}
	public void SetBankAccountNumber(String bankaccountnum) {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, bankaccountnumber);
		WebElement BankAccountNumberValue = driver.findElement(By.xpath("//option[contains(text(),'"+bankaccountnum+"')]"));
		PhoenixPageUtility.clickOnElement(driver, BankAccountNumberValue);
	}
	public void SetBankAccountCurrency(String bankacntcurrency) {
		PhoenixPageUtility.clickOnElement(driver, bankaccountcurrency);
		WebElement BankAccountCurrencyValue = driver.findElement(By.xpath("//option[contains(text(),'"+bankacntcurrency+"')]"));
		PhoenixPageUtility.clickOnElement(driver, BankAccountCurrencyValue);
	}
	public void SetBranch(String branchvalue) {
		PhoenixPageUtility.clickOnElement(driver, branch);
		WebElement BranchValue = driver.findElement(By.xpath("//option[contains(text(),'"+branchvalue+"')]"));
		PhoenixPageUtility.clickOnElement(driver, BranchValue);
	}
	public void ClickOnLogo() {
		PhoenixPageUtility.clickOnElement(driver, logo);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void ClickOnAccountingTab() {
		PhoenixPageUtility.clickOnElement(driver, accountingtab);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void ClickOnVoucherSearch() {
		PhoenixPageUtility.clickOnElement(driver, vouchersearchtab);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void ClickOnNewVoucher() {
		PhoenixPageUtility.clickOnElement(driver, newvoucherbutton);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void SetVoucherType(String vouchertype) {
		WebElement VoucherType = driver.findElement(By.xpath("//label[contains(text(),'"+vouchertype+"')]"));
		PhoenixPageUtility.clickOnElement(driver, VoucherType);
	}

}
