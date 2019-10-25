package phoenix_tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import phoenix_automation_framework.BaseTest;
import static phoenix_automation_framework.Assertion.*;

import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.AAD_validation;
import phoenix_pages.PhoenixClaimDetails;
import phoenix_pages.PhoenixClaimNotes;
import phoenix_pages.PhoenixClaimSummary;
import phoenix_pages.PhoenixClaimTransaction;
import phoenix_pages.PhoenixLocationDetails;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.PhoenixNewClaim;
import phoenix_pages.PhoenixPolicyDetails;

public class Phoenix_AAD extends BaseTest {
	
	private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_Claims.xlsx" ;
	private String sheetname = "phoenix_AAD";
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
	public void Phoenix_POC (ListOrderedMap<String,String> values)
	{	
		softassert  = initialisesoftassert(softassert);
		try {
			
			PhoenixLogin phoenixlogin = new PhoenixLogin(driver);
			phoenixlogin.setusername(values.get("User Name"));
			phoenixlogin.setPassword(values.get("Password"));
			phoenixlogin.clickonsignin();
			
			PhoenixNewClaim newclaim = new PhoenixNewClaim(driver);
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
			Thread.sleep(2000);	
			takeScreenShot(driver, "Claim Created");
			String claimnumber = claimsummary.GetClaimNo();
			System.out.println(claimnumber);
			softassert = assertNotNull(claimnumber,softassert,"Claim Number Not Generated" );
			softassert = assertAll(softassert, "Claim Creation");
			System.out.println("Claim Created Succesfully");
			
			
			PhoenixClaimTransaction claimtransaction = new PhoenixClaimTransaction(driver) ;
			claimtransaction.clickonTransactionTab();
			Thread.sleep(2000);
			claimtransaction.clickonNewTransaction();
			Thread.sleep(2000);	
			takeScreenShot(driver, "Transaction Screen");
			claimtransaction.setStatementdate(values.get("Statement Date"));
			claimtransaction.setReportDate(values.get("Report Date"));
			claimtransaction.setlateReason(values.get("Late Reason"));
			
			if(url =="http://reinsurance.phoenix.awacgbl.com/") {
				
				claimtransaction.setPaidLoss(values.get("Paid Loss"));
				claimtransaction.setExpenses(values.get("Expenses"));
				claimtransaction.setOurExpenses(values.get("Our Expenses"));
			} else {
				claimtransaction.setPaidLossFGU(values.get("Paid Loss FGU"));
				claimtransaction.setExpensesFGU(values.get("Expenses FGU"));
				claimtransaction.SetReserveFGU(values.get("Reserve FGU"));
			}
			Thread.sleep(1000);	
			takeScreenShot(driver, "Transaction Screen after filling values");
			claimtransaction.clickonCreateTransaction();
			System.out.println("Transaction Created Succesfully");
			Thread.sleep(2000);
			takeScreenShot(driver, "Transaction Created");
			String newtransaction = claimtransaction.GetNewtransaction();
			softassert = assertNotNull(newtransaction,softassert,"Transaction Not Created" );
			softassert = assertAll(softassert, "Transaction Creation");
			
			
			
			PhoenixClaimNotes claimnotes = new PhoenixClaimNotes(driver);
			claimnotes.clickonAADTab();
			
			AAD_validation validation1 = new AAD_validation(driver);
			validation1.clickonAadDrop();
			
			takeScreenShot(driver, "AAD Tab");
			
			/*Thread.sleep(2000);
			claimnotes.clickonNotesTab();
			claimnotes.setNotes(values.get("Notes Comment"));
			claimnotes.clickonPostbutton();
			Thread.sleep(2000);*/
			
//			double actualAadincurred = validation1.GetAadIncurred();
//			double expectedAadincurred = validation1.checkAadIncurred();
//			System.out.println("Actual AAD Incurred: "+actualAadincurred);
//			System.out.println("Expected  AAD Incurred: "+expectedAadincurred);		
//			softAsset.assertEquals(actualAadincurred, expectedAadincurred, "Not matched");
//			
//			double actualAadindemnity = validation1.GetAadIdemnity();
//			double expectedAadindemnity = validation1.checkAadIndemnity();
//			System.out.println("Actual AAD Indemnity: "+actualAadindemnity);
//			System.out.println("Expected  AAD Indemnity: "+expectedAadindemnity);		
//			softAsset.assertEquals(actualAadindemnity, expectedAadindemnity, "Not matched");
			
			/*PhoenixClaimTransaction claimtransaction1 = new PhoenixClaimTransaction(driver) ;
			Thread.sleep(2000);
			claimtransaction1.clickonTransactionTab();
			Thread.sleep(2000);
			claimtransaction1.clickonNewTransaction();
			claimtransaction1.setStatementdate(values.get("Statement Date"));
			claimtransaction1.setReportDate(values.get("Report Date"));
			claimtransaction1.setlateReason(values.get("Late Reason"));
			claimtransaction1.ClickonFormUpDrop();
			claimtransaction1.setIndemnityFGU(values.get("Indemnity FGU For 2nd Transac"));
			claimtransaction1.setExpensesFGU(values.get("Expenses FGU For 2nd Transac"));
			claimtransaction1.SetReserveFGU(values.get("Reserve amount For 2nd Transac"));
			claimtransaction1.clickonCreateTransaction();
			System.out.println("Second Transaction Created Succesfully");
			*/
			
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
