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
import phoenix_automation_framework.PhoenixPageUtility;
import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.Activities_Flow;
import phoenix_pages.PhoenixLogin;

public class Phoenix_Activities_FirstNotice extends BaseTest {
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
	public void Phoenix_Accounting_Transaction (ListOrderedMap <String,String> values ) {
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
			activities.ClickOnView();
			PhoenixPageUtility.switchtowindow(driver, 0);
			activities.ClickOnOpenLink();*/
			activities.SetSelectCategory(values.get("Select category"));
			activities.SetAmount(values.get("Amount"));
			activities.SetCurrency(values.get("Currency"));
			activities.SetContractNumber(values.get("Contract Or Company Name"));
			activities.SetUnderwritingYear(values.get("UW Year"));
			activities.SetCedingCompany(values.get("Ceding Company"));
			activities.SetClaimExaminer(values.get("Claim Examiner"));
			driver.findElement(By.xpath("//body")).click();
			Thread.sleep(1000);
			takeScreenShot(driver, "Category_First Notice_User input");
			activities.ClickOnNewClaim();
			takeScreenShot(driver, "Contract Search screen");
			Thread.sleep(2000);
			//activities.ClickOnSearch();
			
			
			// Contract number & UWYear is carrying forward validation:
			
						Activities_Flow activities2 = new Activities_Flow(driver);
						String ContractCodeSearch =  activities2.GetContractCode_Search();
						String UWSearch =  activities2.Getuw_Search();
						softassert= Assertion.assertEquals(ContractCodeSearch, values.get("Contract Or Company Name"), softassert, "Value of ContractNo is not carried forward");
						softassert= Assertion.assertEquals(UWSearch, values.get("UW Year"), softassert, "Value of UwYear is not carried forward");
						softassert=Assertion.assertAll(softassert, "Contract No and UwYear carried forward for searching a claim" );
			
			activities.SelectContract();
			activities.setLossdate(values.get("Loss Date"));
			activities.setourReportDate(values.get("Our Report date"));
			activities.setCedantReportDate(values.get("Cedant report Date"));
			activities.setClaimNature(values.get("Claim nature"));
			activities.setClaimant(values.get("Claimant"));
			activities.setInsured(values.get("Insured"));
			activities.setBokerclaimref(values.get("Broker Claim ref"));
			activities.setCedantClaimRef(values.get("Cedant Claim ref"));	
			activities.SetExaminer(values.get("Claim Examiner"));
			activities.clickonNext();
			activities.setEffective(values.get("Policy Effective Date"));
			activities.setExpiry(values.get("Policy expiry Date"));
			activities.setPolicyType(values.get("Policy type"));
			activities.setPolicyLimit(values.get("Policy Limit"));
			activities.setpolicyAlae(values.get("Policy Alae"));
			activities.clickonNext();
			activities.setCountry(values.get("Country"));
			activities.setAddressLine1(values.get("AddressLine 1"));
			activities.setCity(values.get("City"));
			activities.setPostalcode(values.get("Postal code"));
			activities.clickonNext();
			activities.clickonCreateClaim();
			activities.clickonNo();
			
//Claim no HyperLink available in Activity Organization screen validation:
			
			Activities_Flow activities3 = new Activities_Flow(driver);
			String Clno =activities3.GetClaimno_ActivityOrg();
			softassert= Assertion.assertNotNull(Clno, softassert, "Claim no Not displayed");
			softassert=Assertion.assertAll(softassert, "Claim no HyperLink available in Activity Organization screen ");
			
			
			//activities.SortRecievedDate();
			takeScreenShot(driver, "Newly created Claim linked with the Activity in ACTIVITY ORGANIZATION screen");
			//activities.ClickOnOpenLink();
			Thread.sleep(3000);
			takeScreenShot(driver, "The ACTIVITY ORGANIZATION screen after claim linking");
			activities.ClickonClaimlink_ActivityOrg();
			/*activities.ClickOncancel();
			activities.ClickOnClaimLink();*/
			takeScreenShot(driver, "Claim abstract secreen of the linked Claim");
			
			//Linked Claim Abstract Validation
			Activities_Flow activities4 = new Activities_Flow(driver);
			String ClAb = activities4.GetClaimAbstract();
			softassert= Assertion.assertEquals(ClAb, Clno, softassert, "Not navigating to Claim Abstract");
			softassert=Assertion.assertAll(softassert, "Navigating to Claim Abstract");
			
			
			activities.ClickOnAccept();
			Thread.sleep(2000);
			//activities.ClickOnCloseServer();
			/*activities.ClickOnCompletedTab();
			activities.SortRecievedDate();*/
			takeScreenShot(driver, "Activity Completed");
			System.out.println("Activity Completed");
			Thread.sleep(3000);
			driver.close();
			
			
			} catch(Exception e) {
				e.printStackTrace();
				}
	
}
	}
