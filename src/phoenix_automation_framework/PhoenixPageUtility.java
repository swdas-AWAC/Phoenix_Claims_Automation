package phoenix_automation_framework;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import phoenix_automation_framework.PhoenixPageUtility;

public class PhoenixPageUtility {
	
	private static WebDriverWait wait;
	private static JavascriptExecutor jsexecutor;
	private static WebDriverWait pageactivewait;
	protected static final long max_page_timeout=60 ;
	private final static String progressbar ="//div[@class='spinner' and @role='progressbar']";
	
	public synchronized static void waitUntilPageActive(WebDriver driver)
	{
		try{
			pageactivewait = new WebDriverWait(driver, max_page_timeout);	
			pageactivewait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(progressbar)));
		}catch(StaleElementReferenceException e) {
			waitUntilPageActive( driver);
		}
		}
	

	public synchronized static  void clickOnElement(WebDriver driver, WebElement element)
	{	wait=new WebDriverWait(driver, 3);
		jsexecutor = (JavascriptExecutor)driver;
		try {
			//waitUntilPageActive(driver);
			jsexecutor.executeScript("arguments[0].scrollIntoView();", element);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			//waitUntilPageActive(driver);
			element.click();
		}catch(WebDriverException e)
		{
			if(e.toString().contains("navbar") || e.toString().contains("spinner"))
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				jsexecutor.executeScript("arguments[0].scrollIntoView();", element);
				jsexecutor.executeScript("arguments[0].click();", element);
			}catch(StaleElementReferenceException ex)
			{
				ex.printStackTrace();
				clickOnElement(driver, element);
			}
		}
		finally {
			//waitUntilPageActive(driver);
		}
	}
	
	/*public synchronized static  void  selectdropdown(WebDriver driver, String locator, String str)
	{	
		try {
		wait=new WebDriverWait(driver, 3);	 
		jsexecutor = (JavascriptExecutor)driver;
		//waitUntilPageActive(driver);
		eldrop= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		eldrop.click();	
		spl= vaultdropdown.split("'");
		dropdown = spl[0]+"'"+str+"']";
		List<WebElement> lis = driver.findElements(By.xpath(dropdown));
		if (lis.size()>=1)
		{for (WebElement li :lis)
			{
			WebElement parentdiv= li.findElement(By.xpath("./ancestor::div[2]"));
			if (!(parentdiv.getAttribute("style").contains("display: none")))
					{
				
						li.click();
					break;
					}
			}
		}
		
		}catch(WebDriverException e)
			{	
			e.printStackTrace();
			}
		}*/
	
	public synchronized static  void switchtowindow(WebDriver driver, int windowindex)
	{

		Set<String> allhandles= driver.getWindowHandles();

		int counter=0;
		for(String windhandle : allhandles)
		{
			if(counter==windowindex)
			{
				driver.switchTo().window(windhandle);
				break;
			}
			
			counter++;
		}
	}
		
	}

