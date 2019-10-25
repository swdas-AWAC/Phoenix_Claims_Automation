package phoenix_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Phoenix_SavingsAndDisputedRecord_Tab {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//a[contains(text(),'Savings and Disputed')]") WebElement Savings_Disputedtab;
	@FindBy(xpath="//button[contains(text(),' New Disputed ')]")WebElement NewDisputedButton;
	@FindBy(xpath="//button[contains(text(),' Yes ')]") WebElement Delete_Yes;
	//@FindBy(xpath="//table[@class='table table-hover table_sw']//tbody//tr[2]/td[11]") WebElement ResolutionValue;
	
	public Phoenix_SavingsAndDisputedRecord_Tab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}

	public void ClickOnSavings_Disputedtab() {
		wait.until(ExpectedConditions.elementToBeClickable(Savings_Disputedtab));
		PhoenixPageUtility.clickOnElement(driver, Savings_Disputedtab);
	}
	
	public void ClickOnSavings_NewDisputedButton() {
		wait.until(ExpectedConditions.elementToBeClickable(NewDisputedButton));
		PhoenixPageUtility.clickOnElement(driver, NewDisputedButton);
	}
	
	public String Delete_Edit_Enabled (int index) {
		String del_edit= "//table[@class='table table-hover table_sw']//tbody//tr["+index+"]/td[11]";
		WebElement ResolutionValue=driver.findElement(By.xpath(del_edit));
		String Del_Edit;
		String ResValue= ResolutionValue.getText().trim();
		if (ResValue.contains("Pending")) {
			Del_Edit="Visible";
			
		}else {
			Del_Edit="Not Visible";
		}
		
		return Del_Edit;
	}
	
	public void ClickOnView(int index) {
		String view= "//h3[contains(text(),'Totals by Currency')]/following::i[@class='far fa-eye']["+index+"]";
		WebElement Index= driver.findElement(By.xpath(view));
		PhoenixPageUtility.clickOnElement(driver, Index);
	}
	
	public void ClickOnEditIcon(int index) {
		String edit= "//h3[contains(text(),'Totals by Currency')]/following::i[@class='fas fa-pencil-alt']["+index+"]";
		WebElement Index= driver.findElement(By.xpath(edit));
		PhoenixPageUtility.clickOnElement(driver, Index);
	}
	
	public void ClickOnDelete(int index) {
		String DEl= "//h3[contains(text(),'Totals by Currency')]/following::i[@class='far fa-trash-alt']["+index+"]";
		WebElement Index= driver.findElement(By.xpath(DEl));
		PhoenixPageUtility.clickOnElement(driver, Index);
	}
	
	
	public void ClickonDeleteYes() {
		wait.until(ExpectedConditions.elementToBeClickable(Delete_Yes));
		PhoenixPageUtility.clickOnElement(driver, Delete_Yes);
	}
	
	public String EditIcon_Visibility(int index) {
		String edit="Visible";
		String editk= "//h3[contains(text(),'Totals by Currency')]/following::i[@class='fas fa-pencil-alt']["+index+"]";
				try {
			WebElement Index= driver.findElement(By.xpath(editk));
			wait.until(ExpectedConditions.elementToBeClickable(Index));
		}
		catch(NoSuchElementException | TimeoutException e){
			
			edit="Not Visible";
		}
		return edit;
	
	}
	
	public String GetDISPUTEDID	(int index) {
		String DisptId;
		String DID="//h3[contains(text(),'Totals by Currency')]/following::table[@class='table table-hover table_sw']/tbody/tr["+index+"]/td[2]";
		WebElement Index= driver.findElement(By.xpath(DID));
		DisptId=Index.getText().trim();
		return DisptId;
	}
	public boolean DisputedIdPresent(String ID) {
		
		boolean l1=false;
		List<WebElement> list= driver.findElements(By.xpath("//h3[contains(text(),'Totals by Currency')]/following::table[@class='table table-hover table_sw']/tbody/tr"));
	    for(int i=0; i<list.size();i++) {
	    	String list1 = list.get(i).getText().trim();
	    	if(list1.contains(ID)) {
	    		l1=true;
	    		System.out.println("found in " +i);
	    		break;
	    	}
	    }
	    return l1;
	    		
	}
	
	public String DeleteIcon_Visibility(int index) {
		String Delete="Visible";
		String Del= "//h3[contains(text(),'Totals by Currency')]/following::i[@class='far fa-trash-alt']["+index+"]";
		try {
			WebElement Index= driver.findElement(By.xpath(Del));
			wait.until(ExpectedConditions.elementToBeClickable(Index));
		}
		catch(NoSuchElementException | TimeoutException e){
			
			Delete="Not Visible";
		}
		return Delete;
	
	}
	
}
