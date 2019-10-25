package sweta_Phoenix_Pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClaimScreen_ClaimCreation {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath=("//label[contains(text(),'Section Selection *')]/following::select[1]"))WebElement SectionSelection;
	
	
	public ClaimScreen_ClaimCreation(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	
	}
	
	
	public void SetSectionSelection() {
		
		wait.until(ExpectedConditions.elementToBeClickable(SectionSelection));
		SectionSelection.click();
		
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


