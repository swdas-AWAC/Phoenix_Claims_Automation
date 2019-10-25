package phoenix_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Phoenix_Claims_toppane {

	public WebDriver driver;
	public WebDriverWait wait;
	
	public Phoenix_Claims_toppane(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	public String GetClaimNo() {
		
		WebElement no= driver.findElement(By.xpath("//label[text()='Claim No']/..//p"));
		return no.getText().trim();
	
	}
}
