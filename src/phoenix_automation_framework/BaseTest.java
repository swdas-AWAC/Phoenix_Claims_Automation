package phoenix_automation_framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public int rownum=1;
	public WebDriver driver;
	public String url =null;
	private InputStream input = null;
	private Properties prop ;
	protected String test_output_path =null;
	protected XSSFWorkbook result_workbook =null;
	protected static XSSFSheet result_sheet ;
	protected static int rowCount;
	protected static String parent_folder;
	protected String class_folder;
	public String screenshot_folder;
	protected FileOutputStream reportoutputStream=null;
	private String report_xlsx =null;
	
	
	public void readPropertiesFile() throws IOException {
		String propertyinput= System.getProperty("user.dir")+ File.separator+"config.properties" ;
		input = new FileInputStream(propertyinput);
		prop = new Properties();
		prop.load(input);
		url= prop.getProperty("url").trim();
		test_output_path=prop.getProperty("test_output_path").trim();
	}
	
	public void NewTab() {
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.get(url);
	}
	
	public void launchBrowser() throws IOException
	{	
		System. setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"Driver_Details"+File.separator+"chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("launched browser");
	}
	public synchronized void setup(String methodname ) throws IOException 
	{	
		createTest_Output_Sheet(methodname);
		System.out.println("Created output sheet");
		launchBrowser();	
	}
	
	public synchronized void createTest_Output_Sheet(String methodname)
	{
		 result_sheet = result_workbook.createSheet(methodname);
		 rowCount=0;
		 if((result_sheet==null)) System.out.println("Sheet is null in Base Test");
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public synchronized void createTest_Output_Workbook() {
		String className; 
		File classfolder, screenshotfolder;
		boolean created;
		className = this.getClass().getName();
		String splitted[] = className.split("\\.");
		className=(splitted[(splitted.length)-1]);
		class_folder= parent_folder+File.separator+className;
		System.out.println("Folder "+class_folder);
		classfolder = new File(class_folder);
 		created = classfolder.mkdir();
		result_workbook = new XSSFWorkbook();
		screenshot_folder = class_folder+File.separator+"Screenshots";
		screenshotfolder  = new File(screenshot_folder);
 		created = screenshotfolder.mkdir();
	}
	public synchronized Object[][] dataProviderFeed(ReadWriteExcel readwriteexcel)  {  	
		
		Object[][] obj = new Object[readwriteexcel.exceldata.size()][1];
		Set<String> keys = readwriteexcel.exceldata.keySet();
    	int i=0;
    	for(String key: keys)
    	{
    		
    		obj[i++][0]= readwriteexcel.exceldata.get(key);
    	}
    	return obj;   	
    }
	
	public synchronized static List<Object[][]> assertEquals(double actual, double expected, List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		actual=Math.ceil(actual);
		expected=Math.ceil(expected);
		if (actual==expected) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				
				obj[0][1]=	optionalmessage[0];
			}
			else {
				obj[0][1]="Expected "+expected+" but actual "+actual;
			}
			softassert.add(obj);
		}
		return softassert;
	}
	public static synchronized  List<Object[][]>  initialisesoftassert(List<Object[][]> softassert) {
		softassert = new ArrayList<Object[][]>();
		return softassert;
	}
	
	public synchronized static List<Object[][]> assertNotNull(Object object ,List<Object[][]> softassert, String ... optionalmessage) {
		Object[][] obj = new Object[1][2];
		if (object!=null) {
			obj[0][0]=true;
			obj[0][1]="";
			softassert.add(obj);
		}
		else {
			obj[0][0]=false;
			if(optionalmessage.length>0) {
				obj[0][1]=optionalmessage[0];
				}
			softassert.add(obj);
		}
		return softassert;
	}
	public synchronized String getFormattedTime() {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("hhmmss");
	    String formattedTime=dateFormat.format(date);
	    return formattedTime;
	}
	public synchronized String getFormattedDateandTime() {
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("MMddyy_hhmmss");
	    String formattedDate=dateFormat.format(date);
	    return formattedDate;
	}
	@BeforeSuite
 	public synchronized void suitesetup() throws IOException {
 		String formatteddatetime;
 		readPropertiesFile();
 		formatteddatetime=getFormattedDateandTime();
 		parent_folder = test_output_path+"/"+formatteddatetime;
 		File parentfolder  = new File(parent_folder);
 		boolean created =  parentfolder.mkdir();

 	}
	@BeforeClass
	public void create_report_workbook() throws IOException
	{
 		createTest_Output_Workbook();
	}
	@BeforeMethod 	
	public void Initialise(Method method) throws IOException {
		setup(method.getName()+"_"+getFormattedTime());	
	}
	
//	@AfterMethod
	public void close() {
		driver.close();
	}
	
	@AfterClass(alwaysRun=true)
	public synchronized void writeReport() {
		System.out.println("In after Class");
		String className = this.getClass().getName();
		String splitted[] = className.split("\\.");
		writeReportToSheet(splitted[(splitted.length)-1]);
		//driver.close();	
	}
	
	public synchronized void writeReportToSheet(String workbookname ) {
		report_xlsx = class_folder+File.separator+workbookname+".xlsx";
		try {
			reportoutputStream = new FileOutputStream(new File(report_xlsx));
			result_workbook.write(reportoutputStream);
		}catch(Exception e)
		{
			System.out.println("Could not print to sheet");
		}      
	}
	
	public synchronized  void  takeScreenShot(WebDriver driver, String screenshotname)  {
        PhoenixPageUtility.waitUntilPageActive(driver);
        try{File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(screenshot_folder+File.separator+screenshotname+".png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
