package phoenix_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Transaction_Report {
	public WebDriver driver ;
	public WebDriverWait wait;
	public List<WebElement> list;
	
	@FindBy(xpath="//table[@class='table table-hover table_sw']/tbody") WebElement posttransactable;
	
	public Transaction_Report(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public int PostedTransacTableRow() {
		list = posttransactable.findElements(By.xpath("./tr[@class='collapse in']"));
		int lastrow = list.size();
		return lastrow- 1;
	}
	public void ClickOnlatestTransaction() {
		int row = PostedTransacTableRow();
		WebElement lastTransac = list.get(row);
		PhoenixPageUtility.clickOnElement(driver, lastTransac);
	}
	public double GetPremium() {
		int row=PostedTransacTableRow();
		String premium=list.get(row).findElement(By.xpath("./following::tbody/tr/td[2]")).getText();
		premium = premium.replaceAll(",", "");
		double premiumvalue = Double.parseDouble(premium) ;
		return premiumvalue;
	}
	public double GetExpense() {
		int row=PostedTransacTableRow();
		String Expense=list.get(row).findElement(By.xpath("./following::tbody/tr/td[3]")).getText();
		Expense = Expense.replaceAll(",", "");
		double Expensevalue = Double.parseDouble(Expense) ;
		return Expensevalue;
	}
	public double GetBrokerage() {
		int row=PostedTransacTableRow();
		String brokerage=list.get(row).findElement(By.xpath("./following::tbody/tr/td[5]")).getText();
		brokerage = brokerage.replaceAll(",", "");
		double brokeragevalue = Double.parseDouble(brokerage) ;
		return brokeragevalue;
	}
	public double GetNetBalance() {
		int row=PostedTransacTableRow();
		String netbalance=list.get(row).findElement(By.xpath("./following::tbody/tr/td[9]")).getText();
		netbalance = netbalance.replaceAll(",", "");
		double netbalancevalue = Double.parseDouble(netbalance) ;
		return netbalancevalue;
	}
}
