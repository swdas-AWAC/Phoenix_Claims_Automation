package sweta_Phoenix_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContractSearch_ClaimCreation {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath=("//label[contains(text(),'Ceding Company Name or Contract Code')]/following::input[1]"))WebElement ContractCode;
	@FindBy(xpath=("//span[@class='fa fa-search']/.."))WebElement Searchbutton;
	@FindBy(xpath=("//table[@class='table table-hover table_sw']//tr[1]//td[2]"))WebElement FirstRow;
	@FindBy(xpath=("//button[contains(text(),'Next Step')]"))WebElement NextStepButton;
	

	
	
	
	public ContractSearch_ClaimCreation(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	
	}
	
	public void setContractCode(String ccode)
	
	{
		wait.until(ExpectedConditions.elementToBeClickable(ContractCode));
		ContractCode.clear();
		ContractCode.sendKeys(ccode);
	}
	
	public void ClickonSearchbutton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(Searchbutton));
		Searchbutton.click();
	}
	
	public void ClickonFirstRow()
	{
		wait.until(ExpectedConditions.elementToBeClickable(FirstRow));
		FirstRow.click();
	}
	
	public void ClickonNextStep()
	
	{
		wait.until(ExpectedConditions.elementToBeClickable(NextStepButton));
		NextStepButton.click();
	}
}

