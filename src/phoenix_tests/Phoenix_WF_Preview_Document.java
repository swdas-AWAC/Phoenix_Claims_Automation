package phoenix_tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import phoenix_automation_framework.BaseTest;
import phoenix_automation_framework.PhoenixPageUtility;
import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.Activities_Flow;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.Phoenix_EMLAttachment_Page;

public class Phoenix_WF_Preview_Document  extends BaseTest {
	private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_Claims.xlsx" ;
	private String sheetname = "phoenix";
	private ReadWriteExcel readwriteexcel;
	
	@BeforeClass
	public void initialsetup() throws IOException
	{
		readwriteexcel= new ReadWriteExcel();
		readwriteexcel.getexcel(workbook, sheetname);	
	}
	@DataProvider(name = "DataProvider")
	public synchronized Object[][] gettestdata( ITestContext ctx)  {  	
		Object[][] obj=dataProviderFeed(readwriteexcel); 
		return obj;
    }
	
	@Test(priority =1, dataProvider ="DataProvider" , description="Previewing eml, xlsx, pdf file")
	public void Phoenix_Preview (ListOrderedMap<String,String> values)
	{	
		try {		
			PhoenixLogin phoenixlogin = new PhoenixLogin(driver);
			phoenixlogin.setusername(values.get("User Name"));
			phoenixlogin.setPassword(values.get("Password"));
			phoenixlogin.clickonsignin();
			
			Activities_Flow activities = new Activities_Flow(driver);
			activities.ClickOnActivitiesTab();
			activities.ClickOnStartOverFlow();
			activities.UploadDocument("\\\\awfmpfil01\\group$\\IT\\Cognizant\\05 Phoenix\\01 Claims\\TestEmails\\AON\\477347.eml");
			activities.ClickOnUpload();
			activities.SortRecievedDate();
			activities.SelectDocument1();
			takeScreenShot(driver, "Activity Created1");
			activities.ClickOnView();
			PhoenixPageUtility.switchtowindow(driver, 1);
			takeScreenShot(driver, "Email Content viewed");
			Phoenix_EMLAttachment_Page  EMLAttach = new Phoenix_EMLAttachment_Page(driver);
			EMLAttach.clickonEmlattachment1();
			Thread.sleep(2000);
			PhoenixPageUtility.switchtowindow(driver, 2);
			takeScreenShot(driver, "Email attachment1 viewed");
			PhoenixPageUtility.switchtowindow(driver, 1);
			EMLAttach.clickonEmlattachment2();
			Thread.sleep(2000);
			PhoenixPageUtility.switchtowindow(driver, 3);
			takeScreenShot(driver, "Email attachment2 viewed");
			PhoenixPageUtility.switchtowindow(driver, 0);
			Thread.sleep(2000);
			
			Activities_Flow activities1 = new Activities_Flow(driver);
			activities1.ClickOnStartOverFlow();
			activities1.UploadDocument("\\\\awfmpfil01\\group$\\IT\\Cognizant\\05 Phoenix\\01 Claims\\TestEmails\\AON May 2019 Reinsurance BordereauAB1_rt Bros Const.xlsx");
			activities1.ClickOnUpload();
			activities1.SortRecievedDate();
			activities1.SelectDocument1();
			takeScreenShot(driver, "Activity created2");
			activities1.ClickOnView();
			Thread.sleep(2000);
			PhoenixPageUtility.switchtowindow(driver, 4);
			takeScreenShot(driver, "xlsx viewed");
			PhoenixPageUtility.switchtowindow(driver, 0);
			
			
			Activities_Flow activities2 = new Activities_Flow(driver);
			activities2.ClickOnStartOverFlow();
			activities2.UploadDocument("\\\\awfmpfil01\\group$\\IT\\Cognizant\\05 Phoenix\\01 Claims\\TestEmails\\W13-100081-0601 Odyssey Reinsurance Company -_-2006, AB13887.pdf");
			activities2.ClickOnUpload();
			activities2.SortRecievedDate();
			activities2.SelectDocument1();
			takeScreenShot(driver, "Activity created3");
			activities2.ClickOnView();
			Thread.sleep(2000);
			PhoenixPageUtility.switchtowindow(driver, 5);	
			takeScreenShot(driver, "PDF viewed");
			PhoenixPageUtility.switchtowindow(driver, 0);
			
			Activities_Flow activities3 = new Activities_Flow(driver);
			activities3.ClickOnStartOverFlow();
			activities3.UploadDocument("\\\\awfmpfil01\\group$\\IT\\Cognizant\\05 Phoenix\\01 Claims\\TestEmails\\test.docx");
			activities3.ClickOnUpload();
			activities3.SortRecievedDate();
			activities3.SelectDocument1();
			takeScreenShot(driver, "Activity created4");
			activities3.ClickOnView();
			Thread.sleep(2000);
			PhoenixPageUtility.switchtowindow(driver, 6);
			takeScreenShot(driver, "docx viewed");
			PhoenixPageUtility.switchtowindow(driver, 0);
			
			Activities_Flow activities4 = new Activities_Flow(driver);
			activities4.ClickOnStartOverFlow();
			activities4.UploadDocument("\\\\awfmpfil01\\group$\\IT\\Cognizant\\05 Phoenix\\01 Claims\\TestEmails\\test1.doc");
			activities4.ClickOnUpload();
			activities4.SortRecievedDate();
			activities4.SelectDocument1();
			takeScreenShot(driver, "Activity created5");
			activities4.ClickOnView();
			Thread.sleep(2000);
			PhoenixPageUtility.switchtowindow(driver, 7);
			takeScreenShot(driver, "doc viewed");
			PhoenixPageUtility.switchtowindow(driver, 0);
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
}
