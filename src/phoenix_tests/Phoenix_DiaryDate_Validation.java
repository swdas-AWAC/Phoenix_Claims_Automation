package phoenix_tests;

import static phoenix_automation_framework.Assertion.assertAll;

import java.io.File;
import java.io.IOException;
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
import phoenix_pages.PhoenixClaimDetails;
import phoenix_pages.PhoenixClaimSummary;
import phoenix_pages.PhoenixEditClaim;
import phoenix_pages.PhoenixLocationDetails;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.PhoenixNewClaim;
import phoenix_pages.PhoenixPolicyDetails;
import phoenix_pages.Phoenix_Claims_toppane;

public class Phoenix_DiaryDate_Validation  extends BaseTest {
	
	private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_Claims_Diary Date Validation.xlsx" ;
	private String sheetname = "phoenix";
	private ReadWriteExcel readwriteexcel;
	private List<Object[][]> softassert;
	
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
	
	@Test(dataProvider ="DataProvider" , description="")
	public void DairyDate_Validation(ListOrderedMap<String,String> values)
	{	//SoftAssert softassert = new SoftAssert();
		softassert = initialisesoftassert(softassert);
		try {
			
			PhoenixLogin phoenixlogin = new PhoenixLogin(driver);
			phoenixlogin.setusername(values.get("User Name"));
			phoenixlogin.setPassword(values.get("Password"));
			phoenixlogin.clickonsignin();
			
			PhoenixNewClaim newclaim = new PhoenixNewClaim(driver);
			Thread.sleep(3000);
			newclaim.clickonclaimsearch();
			newclaim.clickonNewClaim();
			newclaim.setContractOrCompName(values.get("Contract Or Company Name"));
			newclaim.setContractYear(values.get("UW Year"));
			newclaim.clickonSearchbutton();
			newclaim.clickoncontract(values.get("Contract Or Company Name"));
			
			PhoenixClaimDetails claimdetails = new PhoenixClaimDetails(driver);
			//claimdetails.setSelection(values.get("section")); 
			//claimdetails.SetLineOfBusiness(values.get("LOB"));
			claimdetails.setLossdate(values.get("Loss Date"));
			claimdetails.setourReportDate(values.get("Our Report date"));
			claimdetails.setCedantReportDate(values.get("Cedant report Date"));
			claimdetails.setClaimNature(values.get("Claim nature"));
			claimdetails.setCedantClaimRef(values.get("Cedant Claim ref"));
			claimdetails.setInsured(values.get("Insured"));
			claimdetails.setBokerclaimref(values.get("Broker Claim ref"));
			claimdetails.setClaimant(values.get("Claimant"));
			claimdetails.setExaminer(values.get("Examiner"));
			claimdetails.setdiaryfrequency(values.get("Diary Frequency"));
			claimdetails.setDescriptionofLoss(values.get("Description Of Loss"));
			claimdetails.setInjury(values.get("Injury"));
			//claimdetails.checkSalvage();
			claimdetails.clickonNext();
			
			PhoenixPolicyDetails policydetails = new PhoenixPolicyDetails(driver);
			policydetails.setEffective(values.get("Policy Effective Date"));
			policydetails.setExpiry(values.get("Policy expiry Date"));
			policydetails.setPolicyType(values.get("Policy type"));
			policydetails.setPolicyLimit(values.get("Policy Limit"));
			policydetails.setpolicyAlae(values.get("Policy Alae"));
			policydetails.clickonNext();
			
			PhoenixLocationDetails locationdetails = new PhoenixLocationDetails(driver) ;
			locationdetails.setCountry(values.get("Country"));
			locationdetails.setAddressLine1(values.get("AddressLine 1"));
			locationdetails.setState(values.get("State"));
			locationdetails.setCity(values.get("City"));
			locationdetails.setPostalcode(values.get("Postal code"));
			locationdetails.clickonNext();
			
			PhoenixClaimSummary claimsummary = new PhoenixClaimSummary(driver);
			claimsummary.clickonCreateClaim();
			System.out.println("Claim Created Succesfully");
			Thread.sleep(2000);
			takeScreenShot(driver, "Claim Created");
			
			Phoenix_Claims_toppane toppane= new Phoenix_Claims_toppane(driver);	
			String claim_no=toppane.GetClaimNo();
			System.out.print(claim_no);
			softassert = assertNotNull(claim_no, softassert, "Claim number Not Generated");
			softassert = assertAll(softassert, "Claim Generation:  "+claim_no);
			
			//Diary Date and frequency validation after Claim creation:
			
			String DiaryFreq= new PhoenixClaimSummary(driver).GetDiaryFrequency();
			System.out.println("Diary Frequency "+ DiaryFreq);
			softassert= Assertion.assertEquals(DiaryFreq, (values.get("Diary Frequency")), softassert, "Not Matched");
			softassert=Assertion.assertAll(softassert, "Diary Frequency displayed as per user input: "+ DiaryFreq);
		
			Thread.sleep(1000);
			takeScreenShot(driver, "Diary Date and Diary Frequency Displayed correctly");
			
			
			//Editing Claim with Diary Date & Frequency:
			
			PhoenixEditClaim editclaim = new PhoenixEditClaim(driver);
			Thread.sleep(2000);
			editclaim.clickonEdit();
			editclaim.SetDiaryFreq(values.get("Diary Frequency_Edit"));
			editclaim.clickonSaveChanges();
			takeScreenShot(driver, "Claim Edited for Diary frequency");
			System.out.println("Claim Editted Succesfully");
			
		// After Editing of Diary Freq, Dairy date validation:
			
			PhoenixClaimSummary claimsummary1 = new PhoenixClaimSummary(driver);
			String DiaryFreq1= new PhoenixClaimSummary(driver).GetDiaryFrequency();
			System.out.println("Diary Frequency "+ DiaryFreq);
			softassert= Assertion.assertEquals(DiaryFreq, (values.get("Diary Frequency_Edit")), softassert, "Not Matched");
			softassert=Assertion.assertAll(softassert, "Diary Frequency displayed after Edition: "+ DiaryFreq1);	
			
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
	


