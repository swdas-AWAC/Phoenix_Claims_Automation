package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Phoenix_SavingsAndDisputed_Edit {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	//@FindBy(xpath="//h3[contains(text(),'Totals by Currency')]/following::i[@class='fas fa-pencil-alt']") WebElement Edit_Button;
	@FindBy(xpath="//label[(contains(text(), 'Currency *'))]/following::select[1]") WebElement Currency;
	@FindBy(xpath="//label[contains(text(),'Type')]/following::select[1]") WebElement Type;
	@FindBy(xpath="//button[contains(text(),' Save')]") WebElement save_Button;
	@FindBy(xpath="//button[contains(text(),' Back to Home')]") WebElement BackToHome_Button;
	
	public Phoenix_SavingsAndDisputed_Edit(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
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
	
	public void ClickOnBackToHome() {
		wait.until(ExpectedConditions.elementToBeClickable(BackToHome_Button));
		PhoenixPageUtility.clickOnElement(driver, BackToHome_Button);
	}
	
	public void ClickOnSave() {
		wait.until(ExpectedConditions.elementToBeClickable(save_Button));
		PhoenixPageUtility.clickOnElement(driver, save_Button);
	}
	
}
