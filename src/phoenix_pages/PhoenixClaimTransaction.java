package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixClaimTransaction {

	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//a[text()='Transactions']") WebElement transactiontab;
	@FindBy(xpath="//button[text()=' New Transaction ']") WebElement newtransaction;
	@FindBy(xpath="//label[contains(text(),'Statement Date')]/..//input") WebElement statementdate;
	@FindBy(xpath="//label[contains(text(),'Report Date *')]/..//input") WebElement reportdate;
	@FindBy(xpath="//label[contains(text(),'Late Reason')]/..//input") WebElement latereason;
	@FindBy(xpath="//label[text()='Paid Loss FGU']/following::input[@name='indemnityFgu']") WebElement paidlossFGU;
	@FindBy(xpath="//label[text()='Expense FGU']/following::input[@name='expenseFgu']") WebElement ExpensesFGU;
	@FindBy(xpath="//label[text()='Our Expenses']/../following::label[text()='Amount'][1]/../input") WebElement OurExpenses;
	@FindBy(xpath="//label[text()='Expenses']/../following::label[text()='Amount'][1]/../input") WebElement Expenses;
	@FindBy(xpath="//label[text()='Paid Loss']/../following::label[text()='Amount'][1]/../input") WebElement paidloss;
	@FindBy(xpath="//label[text()='OS Loss Reserve']/../following::label[text()='Ending'][1]/following::input[1]") WebElement OSLossReserve;
	@FindBy(xpath="//label[text()='LAE Reserve']/../following::label[text()='Ending'][1]/following::input[1]") WebElement LAEReserve;
	@FindBy(xpath="//label[text()='ACR Reserve']/../following::label[text()='Ending'][1]/following::input[1]") WebElement ACRReserve;
	@FindBy(xpath="//label[text()='Salvage / Subrogation']/../following::label[text()='Amount'][1]/../input") WebElement Salvage;
	@FindBy(xpath="//label[text()='Indemnity FGU']/../following::label[text()='Ending'][1]/../input") WebElement IndemnityFGU;
	@FindBy(xpath="//label[text()='Reserve FGU']/following::input[@name='reserveFgu']") WebElement reserveFGU;
	@FindBy(xpath="//span[@title='Create Transaction']/..") WebElement createtransaction;
	@FindBy(xpath="//i[@class='pull-right fa fa-chevron-down']") WebElement currencyarrow;
	@FindBy(xpath="//a[text()=' FROM GROUND-UP ']/i[@class='pull-right fa fa-chevron-down']") WebElement formupdrop;
	@FindBy(xpath="//label[text()='Total Amount Paid']") WebElement tmp;
	@FindBy(xpath="//h3[text()='Totals By Currency']/../clm-transaction-list[1]//tr[@class='tr_totals']//i") WebElement transdrop;
	@FindBy(xpath="//label[text()='Total Amount Paid']") WebElement TransactionNo;
	@FindBy(xpath="//h3[text()='Totals By Currency']/../clm-transaction-list[1]//tr[@class='tr_totals show']//i") WebElement TransactionDropdown;
	@FindBy(xpath="//h3[text()='Totals By Currency']/../clm-transaction-list[1]//tr[@class='totals_USD']//td[2]") WebElement TransNo;
	@FindBy(xpath="//h3[text()='Totals By Currency']/../clm-transaction-list[1]//table/tbody/tr[2]/td[1]")WebElement ClaimStatus;
	@FindBy(xpath="//h3[text()='Totals By Currency']/../clm-transaction-list[1]//tr[@class='tr_totals']/td[12]")WebElement NetPaymentAmt;
	public PhoenixClaimTransaction(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	public void ClickOnTransDrop() {
		wait.until(ExpectedConditions.elementToBeClickable(transdrop));
		PhoenixPageUtility.clickOnElement(driver, transdrop);
	}
	public String GetNewtransaction() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		String NewTransaction = newtransaction.getText();
		return NewTransaction;
	}
	
	public void ClickonTMP() {
		PhoenixPageUtility.clickOnElement(driver, tmp); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void ClickonFormUpDrop() {
		PhoenixPageUtility.clickOnElement(driver, formupdrop); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void ClickonCurrencyArrow() {
		PhoenixPageUtility.clickOnElement(driver, currencyarrow); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	
	public void clickonTransactionTab() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, transactiontab); 
	}
	
	public void clickonNewTransaction() {
		PhoenixPageUtility.clickOnElement(driver, newtransaction); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void isNewTransactionPresent() {
		wait.until(ExpectedConditions.elementToBeClickable(newtransaction));
	}
	
	public void setStatementdate(String statementdatevalue) {
		statementdate.sendKeys(statementdatevalue);
	}
	
	public void setReportDate(String reportdatevalue) {
		reportdate.sendKeys(reportdatevalue);
	}
	
	public void setlateReason(String reasonvalue) {
		latereason.sendKeys(reasonvalue);
	}
	public void setPaidLoss(String paidlossvalue) {
		PhoenixPageUtility.clickOnElement(driver, paidloss);
		paidloss.clear();
		paidloss.sendKeys(paidlossvalue);
	}
	public void setPaidLossFGU(String paidlossvalue) {
		PhoenixPageUtility.clickOnElement(driver, paidlossFGU);
		paidlossFGU.clear();
		paidlossFGU.sendKeys(paidlossvalue);
	}
	public void setExpensesFGU(String expensevalue) {
		PhoenixPageUtility.clickOnElement(driver, ExpensesFGU);
		ExpensesFGU.clear();
		ExpensesFGU.sendKeys(expensevalue);
	}
	public void setExpenses(String expensevalue) {
		PhoenixPageUtility.clickOnElement(driver, Expenses);
		Expenses.clear();
		Expenses.sendKeys(expensevalue);
	}
	public void setOurExpenses(String ourexpensevalue) {
		PhoenixPageUtility.clickOnElement(driver, OurExpenses);
		OurExpenses.clear();
		OurExpenses.sendKeys(ourexpensevalue);
	}
	
	public void SetSalvage(String salvagevalue) {
		PhoenixPageUtility.clickOnElement(driver, Salvage);
		Salvage.clear();
		Salvage.sendKeys(salvagevalue);
	}
	public void setOSLossReserve(String OSLossReservevalue) {
		PhoenixPageUtility.clickOnElement(driver, OSLossReserve);
		OSLossReserve.clear();
		OSLossReserve.sendKeys(OSLossReservevalue);
	}
	public void setLAEReserve(String LAEReservevalue) {
		PhoenixPageUtility.clickOnElement(driver, LAEReserve);
		LAEReserve.clear();
		LAEReserve.sendKeys(LAEReservevalue);
	}
	public void setACRReserve(String ACRReservevalue) {
		PhoenixPageUtility.clickOnElement(driver, ACRReserve);
		ACRReserve.clear();
		ACRReserve.sendKeys(ACRReservevalue);
	}
	
	
	public void SetReserveFGU(String reservevalue) {
		PhoenixPageUtility.clickOnElement(driver, reserveFGU);
		reserveFGU.sendKeys(reservevalue);
	}
	
	public void setIndemnityFGU(String indemnityFGUvalue) {
		PhoenixPageUtility.clickOnElement(driver, IndemnityFGU);
		IndemnityFGU.sendKeys(indemnityFGUvalue);
	}
	
	public void clickonCreateTransaction() {
		Actions actions = new Actions(driver);
		actions.moveToElement(createtransaction).build().perform();
		PhoenixPageUtility.clickOnElement(driver, createtransaction); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void ClickonTransactionDropdown() {
		PhoenixPageUtility.clickOnElement(driver, transdrop);
	}
public String GetTransNo() {
	
		return TransNo.getText().trim();
	
	}

public String GetClaimStatus() {
	String status = null;
	try {
		status = ClaimStatus.findElement(By.xpath("./span")).getText().trim();
	}catch(NoSuchElementException e) {
		status = "";
	}
	return status;
}

public String GetNetPayAmt() {
	return NetPaymentAmt.getText().trim();
	
}

}
