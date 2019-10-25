package phoenix_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class AAD_validation {

	public WebDriver driver ;
	public WebDriverWait wait;
	public double aadindemnityvalue, aadincurredvalue;
	public double groundupincurredvalue,groundupindemnityvalue, groundupreservevalue ;
	public double PaidLossValue,ExpensesValue,paymentamountValue;
	public List<WebElement> list;
	
	
	@FindBy(xpath="//h3[text()='Annual Aggregate Deductible']/following::i[@class='fa fa-chevron-down'][1]") WebElement aaddrop;
	@FindBy(xpath="//table[@class='table table_sw inside_table']//tr[3]/td[8]") WebElement groundupincurred;
	@FindBy(xpath="//table[@class='table table_sw']//tr[@class='tr_totals in']/td[9]") WebElement retentionamount;
	@FindBy(xpath="//table[@class='table table_sw inside_table']//tr[3]/td[5]") WebElement aadincurred;
	@FindBy(xpath="//table[@class='table table_sw inside_table']//tr[3]/td[4]") WebElement aadidemnity;
	@FindBy(xpath="//table[@class='table table_sw inside_table']/tbody") WebElement aadtable;
	@FindBy(xpath="//rnsr-transaction-list[@ng-reflect-can-revert='true']//table//tr[@class='tr_totals']/td[5]") WebElement paidloss;
	@FindBy(xpath="//rnsr-transaction-list[@ng-reflect-can-revert='true']//table//tr[@class='tr_totals']/td[6]") WebElement expenses;
	@FindBy(xpath="//rnsr-transaction-list[@ng-reflect-can-revert='true']//table//tr[@class='tr_totals']/td[12]") WebElement paymentamount;
	
	public AAD_validation(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	public double GetPaymentAmount() {
		String PaymentAmount = paymentamount.getText();
		PaymentAmount = PaymentAmount.replaceAll(",", "");
		paymentamountValue = Double.parseDouble(PaymentAmount);
		return paymentamountValue;
	}
	
	public double GetPaidLoss() {
		String PaidLoss = paidloss.getText();
		PaidLoss = PaidLoss.replaceAll(",", "");
		PaidLossValue = Double.parseDouble(PaidLoss);
		return PaidLossValue;
	}
	
	public double GetExpenses() {
		String Expenses = expenses.getText();
		Expenses = Expenses.replaceAll(",", "");
		ExpensesValue = Double.parseDouble(Expenses);
		return ExpensesValue;
	}
	
	private int aadtablelastrow()
	{
		list=aadtable.findElements(By.xpath("./tr"));
		int lastrow=list.size();
		return lastrow-1;
	}
	
	public void clickonAadDrop() {
		PhoenixPageUtility.clickOnElement(driver, aaddrop); 
		PhoenixPageUtility.waitUntilPageActive(driver);
	}
	
	public double GetAadIncurred() {	
		int row=aadtablelastrow();
		String aadincurred=list.get(row).findElement(By.xpath("./td[5]")).getText();
		aadincurred = aadincurred.replaceAll(",", "");
		aadincurredvalue = Double.parseDouble(aadincurred) ;
		return aadincurredvalue;
	}
	
	public double GetAadIdemnity() {	
		int row1=aadtablelastrow();
		String aadidemnity=list.get(row1).findElement(By.xpath("./td[4]")).getText();
		aadidemnity = aadidemnity.replaceAll(",", "");
		aadindemnityvalue = Double.parseDouble(aadidemnity) ;
		return aadindemnityvalue;
	}
	
	public double GetGroundupIncurred() {
		int row=aadtablelastrow();
		String groundupincurred=list.get(row).findElement(By.xpath("./td[8]")).getText();
		groundupincurred = groundupincurred.replaceAll(",", "");
		groundupincurredvalue = Double.parseDouble(groundupincurred);
		return groundupincurredvalue;
	}
	/*
	public double checkAadIncurred() {
		int row=aadtablelastrow();
		String groundupincurred=list.get(row).findElement(By.xpath("./td[8]")).getText();
		groundupincurred = groundupincurred.replaceAll(",", "");
		groundupincurredvalue = Double.parseDouble(groundupincurred);
		String retention = retentionamount.getText();
		retention = retention.replaceAll(",", "");
		retentionvalue = Double.parseDouble(retention);
		double actualincurredvalue = groundupincurredvalue - retentionvalue;
		return actualincurredvalue;		
	}
	
	public double checkAadIndemnity() {
		int row=aadtablelastrow();
		String groundupindemnity=list.get(row).findElement(By.xpath("./td[6]")).getText();
		groundupindemnity = groundupindemnity.replaceAll(",", "");
		groundupindemnityvalue = Double.parseDouble(groundupindemnity);
		String groundupreserve=list.get(row).findElement(By.xpath("./td[7]")).getText();
		groundupreserve = groundupreserve.replaceAll(",", "");
		groundupreservevalue = Double.parseDouble(groundupreserve);
		String retention = retentionamount.getText();
		retention = retention.replaceAll(",", "");
		retentionvalue = Double.parseDouble(retention);
		double actualincurredvalue = (groundupindemnityvalue + groundupreservevalue) - retentionvalue;
		return actualincurredvalue;		
	}*/
	
}
