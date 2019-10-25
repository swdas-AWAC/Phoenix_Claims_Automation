package phoenix_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class ApproveManager {
	public WebDriver driver;
	public WebDriverWait wait, logoimagewait;
	private JavascriptExecutor jsexecutor;

	public List<WebElement> list;
	@FindBy(xpath="//img[@src='assets/images/awac_home_logo.png']") WebElement logoimage;
	@FindBy(xpath="//label[contains(text(),'Manager to proceed.')]/../input") WebElement searchbox;
	@FindBy(xpath="//button[text()=' Proceed']") WebElement proceed;
	@FindBy(xpath="//a[text()=' Held Transactions']") WebElement heldtransaction;
	@FindBy(xpath="//button[text()=' Approve']") WebElement approvebutton;
	@FindBy(xpath="//i[@class='fa fa-chevron-down']") WebElement approvedrop;
	//@FindBy(xpath="//div[@id='assignedMe']//table") WebElement approvedtable;\
	

	private final String approvedtable = "//div[@id='assignedMe']//table/tbody/tr[@class='tr_totals']";
	private final String assignedtometable = "//table[@class = 'table table_sw']/tbody/tr";

	@FindBy(xpath="//button[text()=' Yes ']") WebElement yesbutton;
	@FindBy(xpath="//div[@class='modal-footer']/button[text()=' Assign ']") WebElement assigne;

	public ApproveManager(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	public void ClickOnYes() {
		PhoenixPageUtility.clickOnElement(driver, yesbutton);
	}

	public int returnlastrow(String tablexpath) {

		list = driver.findElements(By.xpath(tablexpath));
		int lastrow = list.size();
		return lastrow;
	}
	public void ClickOnlatestapproveddrop() {
		// TODO Auto-generated method stub
		int row = returnlastrow(approvedtable);
		System.out.println("Latest row: " +row);
		WebElement lastapprovedrow = list.get(row-1).findElement(By.xpath("./td[12]/a"));
		PhoenixPageUtility.clickOnElement(driver, lastapprovedrow);
		//lastapprovedrow.click();
		
	}

	public void ClickOnApproveButton() throws InterruptedException {
		//PhoenixPageUtility.clickOnElement(driver, approvebutton);
		int row = returnlastrow(assignedtometable);
		WebElement approvebutton = list.get(row-1).findElement(By.xpath("./td//following::button[contains(text(), 'Approve')]"));
		
		PhoenixPageUtility.clickOnElement(driver, approvebutton);
		
		Thread.sleep(3000);
	}

	public void ClickOnHeldTransaction() {
		PhoenixPageUtility.waitUntilPageActive(driver);
		PhoenixPageUtility.clickOnElement(driver, heldtransaction);
	}

	public boolean isLogoPresent() {
		logoimagewait =new WebDriverWait(driver, 10);	
		try {
			logoimagewait.until(ExpectedConditions.visibilityOf(logoimage));
			if (logoimage.isDisplayed()) {
				return true;
			}
			else {
				return false;
			}
		}catch(TimeoutException e) {
			return false;
		}			
	}
	public void SelectManager(String Manager) throws InterruptedException {
		PhoenixPageUtility.clickOnElement(driver, searchbox);
		searchbox.sendKeys(Manager);
		WebElement manager=driver.findElement(By.xpath("//td[contains(text(),'"+Manager+"')]"));
		PhoenixPageUtility.clickOnElement(driver, manager);
		PhoenixPageUtility.clickOnElement(driver, proceed);
		Thread.sleep(7000);
	}
	public void ManagerApproval(String Manager) {
		PhoenixPageUtility.clickOnElement(driver, searchbox);
		searchbox.sendKeys(Manager);
		WebElement manager=driver.findElement(By.xpath("//th[text()='Claim Manager Name']/../../..//td[contains(text(),'"+Manager+"')]"));
		PhoenixPageUtility.clickOnElement(driver, manager);
		PhoenixPageUtility.clickOnElement(driver, assigne);
	}
	public void ClickOnRejectButton() throws InterruptedException {
		
		int row = returnlastrow(assignedtometable);
		WebElement Rejectbutton = list.get(row-1).findElement(By.xpath("./td//following::button[contains(text(), 'Reject')]"));
		
		PhoenixPageUtility.clickOnElement(driver, Rejectbutton);
		
		Thread.sleep(3000);
		
	}

}
