package phoenix_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class Phoenix_EMLAttachment_Page {
	
	public WebDriver driver ;
	public WebDriverWait wait;
	
	
	
	@FindBy(xpath="//span[contains(text(),'W13-217676-1801A Odyssey Reinsurance Company _-2018, AH61242.pdf')]/..") WebElement Emlattachment1;
	@FindBy(xpath="//span[contains(text(),'AON May 2019 Reinsurance Bordereau.xlsx - AH6_ Round Ground.xlsx')]/..") WebElement Emlattachment2;

	//div[@class='attached_files']/a[@class='att_pdf']
	
	public Phoenix_EMLAttachment_Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	public void clickonEmlattachment1() {
		try {
		PhoenixPageUtility.waitUntilPageActive(driver);
		//PhoenixPageUtility.clickOnElement(driver, Emlattachment1);
		Thread.sleep(2000);
		Emlattachment1.click();
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			clickonEmlattachment1();
		}
	}
	public void clickonEmlattachment2() {
		try {
			PhoenixPageUtility.waitUntilPageActive(driver);
			//PhoenixPageUtility.clickOnElement(driver, Emlattachment2);
			Thread.sleep(2000);
			Emlattachment2.click();
				
			}
			catch(Exception ex)
			{
				clickonEmlattachment2();
			}
	}
	
	
	
}
