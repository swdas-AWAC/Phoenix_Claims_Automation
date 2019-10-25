package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Phoenix_HistoryTab {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@FindBy(xpath="//div[@id = 'tabClaim']//span[@title = 'History']/ancestor::button[1]")WebElement HistoryButton;
	@FindBy(xpath="//i[@class='fa fa-times']")WebElement Close;

	public Phoenix_HistoryTab(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public void clickonHistory() {

		try {
			wait.until(ExpectedConditions.visibilityOf(HistoryButton));
	        Actions actions = new Actions(driver);
	        actions.moveToElement(HistoryButton).build().perform();
	        HistoryButton.click();
	        
	        } catch(Exception ex) {
	            ex.printStackTrace();
	            clickonHistory();
	        }

	}
	
	public void clickonClose() {
		Close.click();
	}
}
