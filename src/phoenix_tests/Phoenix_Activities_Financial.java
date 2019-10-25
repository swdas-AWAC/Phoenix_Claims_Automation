package phoenix_tests;

import java.io.File;
import java.util.List;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import phoenix_automation_framework.Assertion;
import phoenix_automation_framework.BaseTest;
import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.Activities_Flow;
import phoenix_pages.PhoenixLogin;

public class Phoenix_Activities_Financial extends BaseTest {
	private String workbook = System.getProperty("user.dir") + File.separator + "test_data" + File.separator + "Phoenix_Claims.xlsx" ;
	private String sheetname = "Phoenix";
	private ReadWriteExcel readwriteexcel ;
	private List<Object[][]> softassert  ;
	
	@BeforeClass
	public void initialsetup() {
		readwriteexcel = new ReadWriteExcel();
		readwriteexcel.getexcel(workbook, sheetname);
	}
	
	@DataProvider (name="DataProvider" )
	public synchronized Object[][] gettestdata( ITestContext ctx)  {  	
		Object[][] obj=dataProviderFeed(readwriteexcel); 
		return obj;
    }
	
	@Test(dataProvider ="DataProvider" , description="")
	public void Phoenix_Activities_Financial (ListOrderedMap <String,String> values ) {
		softassert  = initialisesoftassert(softassert);
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
			Thread.sleep(6000);
			
			//Activity Organization header validation
			
			String PageHeader = new Activities_Flow(driver).GetPageHeader();
			System.out.println(PageHeader);
			softassert= Assertion.assertEquals(PageHeader, "ACTIVITY ORGANIZATION", softassert, "Activity Organization-Page Header Not Matched");
			softassert=Assertion.assertAll(softassert, "Activity Organization Page header displays after uploading a document ");
			
			
			/*activities.SortRecievedDate();
			activities.SelectDocument1();
			activities.ClickOnOpenLink();*/
			activities.SetSelectCategory(values.get("Select category"));
			activities.SetAmount(values.get("Amount"));
			activities.SetCurrency(values.get("Currency"));
			activities.SetContractNumber(values.get("Contract Or Company Name"));
			activities.SetUnderwritingYear(values.get("UW Year"));
			/*activities.SetCedingCompany(values.get("Ceding Company"));
			activities.SetClaimExaminer(values.get("Claim Examiner"));*/
			//activities.setClaimNumber(values.get("Claim No"));
			
			// After Entering Contract number,Fields are disabled validation:
			
			Activities_Flow activities1 = new Activities_Flow(driver);
			String ClaimNumber_Status=activities1.GetClaimNoVisibility();
		    String BrokerClaimRef_Status=activities1.GetBrokerClaimRefVisibility();
			String BrokerContractRef_Status=activities1.GetBrokerContractRefVisibility();
			String Examiner_Status=activities1.GetClaimExaminerVisibility();
			String CedingCompany_Status=activities1.GetcedingcompanyVisibility();
			softassert= Assertion.assertEquals(ClaimNumber_Status, "true", softassert, "Claim Number field not Disabled");
			softassert= Assertion.assertEquals(BrokerClaimRef_Status, "true", softassert, "BrokerClaimRef field not Disabled");
			softassert= Assertion.assertEquals(BrokerContractRef_Status, "true", softassert, "BrokerContractRef field not Disabled");
			softassert= Assertion.assertEquals(Examiner_Status, "true", softassert, "Examiner field not Disabled");
			softassert= Assertion.assertEquals(CedingCompany_Status, "true", softassert, "CedingCompany field not Disabled");
			softassert=Assertion.assertAll(softassert, "After Entering Contract number,Fields are disabled ");
			takeScreenShot(driver, "Category_Financial_User input");
			activities.ClickOnSearchClaim();
			Thread.sleep(2000);
			//activities.setBrokerClaimNo();
			/*activities.SetClaim(values.get("Claim No"));*/
			/*driver.findElement(By.xpath("//body")).click();
			activities.ClickOnSearchButton();*/
			
			// Contract number & UWYear is carrying forward validation:
			
			Activities_Flow activities2 = new Activities_Flow(driver);
			String ContractNoSearch =  activities2.GetContractno();
			String UWYearSearch =  activities2.GetUWYr();
			softassert= Assertion.assertEquals(ContractNoSearch, values.get("Contract Or Company Name"), softassert, "Value of ContractNo is not carried forward");
			softassert= Assertion.assertEquals(UWYearSearch, values.get("UW Year"), softassert, "Value of UwYear is not carried forward");
			softassert=Assertion.assertAll(softassert, "Contract No and UwYear carried forward for searching a claim" );
			
			
			activities.SelectClaim();
			//activities.ClickOnSelectClaimButton();
			Thread.sleep(2000);
			
			//Claim no HyperLink available in Activity Organization screen validation:
			
			Activities_Flow activities3 = new Activities_Flow(driver);
			String Clno =activities3.GetClaimno_ActivityOrg();
			softassert= Assertion.assertNotNull(Clno, softassert, "Claim no Not displayed");
			softassert=Assertion.assertAll(softassert, "Claim no HyperLink available in Activity Organization screen ");
			
			
			takeScreenShot(driver, "Category_Financial_User input_After claim selection");
			//activities.ClickOnsaveContinueButton();
			//activities.ClickOnSave();
			//activities.ClickOncancel();
			//activities.SortRecievedDate();
			Thread.sleep(2000);
			activities.ClickonClaimlink_ActivityOrg();
			//takeScreenShot(driver, "Claim Linked");
			//activities.ClickOnClaimLink();
			takeScreenShot(driver, "Claim abstract secreen of the linked Claim");
			
			
			//Linked Claim Abstract Validation
			Activities_Flow activities4 = new Activities_Flow(driver);
			String ClAb = activities4.GetClaimAbstract();
			softassert= Assertion.assertEquals(ClAb, Clno, softassert, "Not navigating to Claim Abstract");
			softassert=Assertion.assertAll(softassert, "Navigating to Claim Abstract");
			
			activities.ClickOnAccept();
			//activities.ClickOnCloseServer();
			/*activities.ClickOnCompletedTab();
			activities.SortRecievedDate();*/
			Thread.sleep(2000);
			takeScreenShot(driver, "Activity Completed");
			System.out.println("Activity Completed");
			Thread.sleep(3000);
			driver.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
				}
	
}
	}
