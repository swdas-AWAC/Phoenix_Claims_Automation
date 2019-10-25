package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.log.SysoLogger;

import phoenix_automation_framework.PhoenixPageUtility;

public class Accounting_Transaction_Screen {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath = "//button[text()=' New Transaction ']") WebElement newtransactiontab;
	@FindBy(xpath = "//select[@name='transactionType']") WebElement transactiontype;
	@FindBy(xpath = "//label[text()='From Dt *']/..//input") WebElement fromdate;
	@FindBy(xpath = "//label[text()='To Dt *']/..//input") WebElement todate;
	@FindBy(xpath = "//label[text()='Due Dt *']/..//input") WebElement duedate;
	@FindBy(xpath = "//label[text()='Net Balance (OC)']/following::p[1]") WebElement netbalance;
	@FindBy(xpath = "//label[text()='Input Net Balance (OC)']/..//input") WebElement inputnetbalance;
	@FindBy(xpath = "//th[text()='Commission']/..//input") WebElement comission;
	@FindBy(xpath = "//th[text()='Brokerage']/..//input") WebElement brokerage;
	@FindBy(xpath = "//th[text()='Premium Tax']/..//input") WebElement premiumtax;
	@FindBy(xpath = "//th[text()='Common Account Premium']/..//input") WebElement commonaccpremium;
	@FindBy(xpath = "//th[text()='Federal Excise Tax']/..//input") WebElement federalexcisetax;
	@FindBy(xpath = "//th[text()='Deposit Premium']/..//input") WebElement depositprem;
	@FindBy(xpath = "//th[text()='Override Commission']/..//input") WebElement overridecommission;
	@FindBy(xpath = "//th[text()='Fronting Fee']/..//input") WebElement frontingfee;
	@FindBy(xpath = "//th[text()='Misc Expense']/..//input") WebElement miscExpense;
	@FindBy(xpath = "//th[text()='Reinstatement Premium']/..//input") WebElement Reinstatementprem;	 
	@FindBy(xpath = "//th[text()=' Written Premium ']/..//input") WebElement writtenprem;
	@FindBy(xpath = "//th[text()='Retro Rated Premium']/..//input") WebElement retrorated;
	@FindBy(xpath = "//th[text()='No Claims Bonus']/..//input") WebElement noclaimbonus;
	@FindBy(xpath = "//th[text()='Profit Commission Reserve']/..//input") WebElement profitcommission;
	@FindBy(xpath = "//th[text()='Sliding Scale Reserve']/..//input") WebElement slidingscale;
	@FindBy(xpath = "//th[text()='Paid Loss']/..//input") WebElement paidloss;
	@FindBy(xpath = "//th[text()='Paid LAE']/..//input") WebElement paidlae;
	@FindBy(xpath = "//th[text()='Adjustment Premium']/..//input") WebElement adjustmentprem;
	@FindBy(xpath = "//th[text()='Premium Portfolio In']/..//input") WebElement premportfolioIn;
	@FindBy(xpath = "//th[text()='Premium Portfolio Out']/..//input") WebElement premportfolioOut;
	@FindBy(xpath = "//th[text()='Loss Port In']/..//input") WebElement lossportIn;
	@FindBy(xpath = "//th[text()='Loss Port Out']/..//input") WebElement lossportout;
	@FindBy(xpath="//button[contains(text(),'Close')]") WebElement attentionClose;
	@FindBy(xpath = "//th[text()='Funds Held Premium']/..//input") WebElement fundsheldprem;
	@FindBy(xpath = "//th[text()='Funds Held Losses']/..//input") WebElement fundsheldloss;
	@FindBy(xpath = "//th[text()=' Funds Released Premium ']/..//input") WebElement fundreleasedprem;
	@FindBy(xpath = "//th[text()='Funds Released Losses']/..//input") WebElement fundreleasedloss;
	@FindBy(xpath = "//th[text()='OSR']/following::th[text()=' Ending A '][1]/../td[1]/input") WebElement osrEnding;
	@FindBy(xpath = "//th[text()='OSLAE']/following::th[text()=' Ending A '][1]/../td[1]/input") WebElement oslaeEnding;
	@FindBy(xpath = "//span[@title='Post Transaction']") WebElement postbutton;
	@FindBy(xpath = "//a[text()='Posted']") WebElement postedtab;
	@FindBy(xpath = "//label[text()='Comment']/../div/textarea") WebElement commentbox;
	@FindBy(xpath = "//span[text()='Transaction By Currency']/../following::i[@class='fa fa-chevron-down']") WebElement dropdown;
	@FindBy(xpath = "//div[@class='left_btns']/following::span[@title='Back to Landing Page']") WebElement backtocontract;

	public Accounting_Transaction_Screen(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	public void ClickOnBackToContract() {
		Actions action = new Actions(driver);
		action.moveToElement(backtocontract).build().perform();
		PhoenixPageUtility.clickOnElement(driver, backtocontract);
		
	}
	public void ClickOnPostedDropdown() {
		PhoenixPageUtility.clickOnElement(driver, dropdown);
	}
	public void SetAdjustmentPremium(String adjustmentvalue) {
		adjustmentprem.sendKeys(adjustmentvalue);	
	}
	public void SetPaidLoss(String paidlossvalue) {
		paidloss.sendKeys(paidlossvalue);	
	}
	public void SetPaidLAE(String paidlaevalue) {
		paidlae.sendKeys(paidlaevalue);	
	}
	public void SetSlidingScaleReserve(String slidingscalevalue) {
		slidingscale.sendKeys(slidingscalevalue);	
	}
	public void SetProfitCommissionReserve(String profitcomvalue) {
		profitcommission.sendKeys(profitcomvalue);	
	}
	public void SetNoClaimBonus(String noclaimbonusvalue) {
		noclaimbonus.sendKeys(noclaimbonusvalue);	
	}
	public void SetRetroRatedPrem(String retroratedvalue) {
		retrorated.sendKeys(retroratedvalue);	
	}
	public void SetWrittenPremium(String writtenpremvalue) {
		writtenprem.sendKeys(writtenpremvalue);	
	}
	public void SetReinstatementPremium(String reinstatementvalue) {
		Reinstatementprem.sendKeys(reinstatementvalue);	
	}
	public void SetMiscExpenses(String miscexpensevalue) {
		miscExpense.sendKeys(miscexpensevalue);
	}
	public void SetFrontingfee(String frontingfeevalue) {
		frontingfee.sendKeys(frontingfeevalue);
	}
	public void SetOverrideCommission(String overridevalue) {
		overridecommission.sendKeys(overridevalue);
	}
	public void SetDepositPremium(String depositevalue) {
		depositprem.sendKeys(depositevalue);
	}
	public void SetInputNetBalance(String inputnetbalancevalue) {
		//inputnetbalance.click();
		inputnetbalance.clear();
		inputnetbalance.sendKeys(inputnetbalancevalue);
	}
	
	public void closeAttention() {
		attentionClose.click();
	}
	public String GetNetBalance() {
		//PhoenixPageUtility.clickOnElement(driver, netbalance);
		//String Netbalance=netbalance.getAttribute("ng-reflect-model");
		String Netbalance=netbalance.getText();
		System.out.println("Net balance is : "+Netbalance);
		return Netbalance;
	}
	public void ClickOnPostedTab() {
		PhoenixPageUtility.clickOnElement(driver, postedtab);
	}
	public void clickonPostTransaction() {
		Actions actions = new Actions(driver);
		actions.moveToElement(postbutton).build().perform();
		PhoenixPageUtility.clickOnElement(driver, postbutton); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	public void SetOslaeEnding(String oslaeendingvalue) {
		oslaeEnding.sendKeys(oslaeendingvalue);
	}
	public void SetOsrEnding(String osrendingvalue) {
		osrEnding.sendKeys(osrendingvalue);
	}
	public void SetFundReleasedPrem(String releasedpremvalue) {
		fundreleasedprem.sendKeys(releasedpremvalue);
	}
	public void SetFundReleasedLoss(String releasedlossvalue) {
		fundreleasedloss.sendKeys(releasedlossvalue);
	}
	public void SetFundHeldLoss(String fundheldlossvalue) {
		fundsheldloss.sendKeys(fundheldlossvalue);
	}
	public void SetFundHeldPremium(String fundheldpremiumvalue) {
		fundsheldprem.sendKeys(fundheldpremiumvalue);
	}
	public void SetLossPortOut(String lossportoutvalue) {
		lossportout.sendKeys(lossportoutvalue);
		
	}
	public void SetLossPortIN(String lossportinvalue) {
		lossportIn.sendKeys(lossportinvalue);
	}
	public void SetPremPortFolioOut(String portfolioOutvalue) {
		premportfolioOut.sendKeys(portfolioOutvalue);
	}
	public void SetPremPortFolioIN(String portfolioinvalue) {
		premportfolioIn.sendKeys(portfolioinvalue);
	}
	public void SetFederalExciseTax(String exisetaxvalue) {
		federalexcisetax.sendKeys(exisetaxvalue);
	}
	public void SetCommonAccPremium(String commonaccpremvalue) {
		commonaccpremium.sendKeys(commonaccpremvalue);
	}
	public void SetPremiumTax(String premiumtaxvalue) {
		premiumtax.sendKeys(premiumtaxvalue);
	}
	
	public void SetBrokerage(String brokeragevalue) {
		brokerage.sendKeys(brokeragevalue);
	}
	
	public void SetComission(String commisionvalue) {
		comission.sendKeys(commisionvalue);
	}
	
	public void ClickOnNewTransactionTab() {
		PhoenixPageUtility.clickOnElement(driver, newtransactiontab);
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public void SetTransactionType(String transactiontypevalue) {
		PhoenixPageUtility.clickOnElement(driver, transactiontype);
		//PhoenixPageUtility.waitUntilPageActive(driver);
		WebElement transactype = driver.findElement(By.xpath("//select[@name='transactionType']/option[text()='"+transactiontypevalue+"']"));
		PhoenixPageUtility.clickOnElement(driver, transactype);
	}
	
	public void SetFromDate(String frmdatevalue) {
		fromdate.sendKeys(frmdatevalue);
	}
	
	public void SetToDate(String todatevalue) {
		todate.sendKeys(todatevalue);
	}
	
	public void SetDueDate(String duedatevalue) {
		duedate.sendKeys(duedatevalue);
	}
	public void SetComment() {
		commentbox.sendKeys("Testing");
	}
	
	
}
