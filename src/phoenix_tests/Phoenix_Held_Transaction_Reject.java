package phoenix_tests;

import static phoenix_automation_framework.Assertion.assertAll;
import static phoenix_automation_framework.Assertion.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import phoenix_automation_framework.Assertion;
import phoenix_automation_framework.BaseTest;
import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.ApproveManager;
import phoenix_pages.PhoenixClaimDetails;
import phoenix_pages.PhoenixClaimSummary;
import phoenix_pages.PhoenixClaimTransaction;
import phoenix_pages.PhoenixLocationDetails;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.PhoenixNewClaim;
import phoenix_pages.PhoenixPolicyDetails;
import phoenix_pages.Searchclaim;

public class Phoenix_Held_Transaction_Reject extends BaseTest {
	
	private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_Claims_Held Transaction.xlsx" ;
	private String sheetname = "phoenix";
	private ReadWriteExcel readwriteexcel;
	private List<Object[][]> softassert;
	public String claimnumber;
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
	
	@Test(priority=1, dataProvider ="DataProvider" , description="")
	public void Claim_Transaction_Creation(ListOrderedMap<String,String> values)
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
			newclaim.setContractYear(values.get("Contract Year"));
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
			//claimdetails.setdiaryfrequency(values.get("Diary Frequency"));
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
			Thread.sleep(3000);
			claimnumber = claimsummary.GetClaimNo();
			System.out.println(claimnumber);
			System.out.println("Claim Created Succesfully");
			
			softassert = Assertion.assertNotNull(claimnumber, softassert);
			softassert = assertAll(softassert, "Claim Creation");
			
			PhoenixClaimTransaction claimtransaction = new PhoenixClaimTransaction(driver) ;
			claimtransaction.clickonTransactionTab();
			claimtransaction.clickonNewTransaction();
			claimtransaction.setStatementdate(values.get("Statement Date"));
			claimtransaction.setReportDate(values.get("Report Date"));
			claimtransaction.setlateReason(values.get("Late Reason"));
			claimtransaction.setPaidLoss(values.get("Paid Loss"));
			claimtransaction.setExpenses(values.get("Expenses"));
			claimtransaction.setOurExpenses(values.get("Our Expenses"));
			claimtransaction.clickonCreateTransaction();
			System.out.println("Transaction Created Succesfully");
			softassert = Assertion.assertEquals("", "", softassert);
			softassert = assertAll(softassert, "Transaction Creation");
			
			
			ApproveManager approvemang = new ApproveManager(driver);
			approvemang.SelectManager(values.get("Manager Name"));
			approvemang.ClickOnYes();
			
			PhoenixClaimTransaction claimtransaction1 = new PhoenixClaimTransaction(driver) ;
			claimtransaction1.ClickOnTransDrop();
			Thread.sleep(2000);
			takeScreenShot(driver, "Transaction is On Hold");
			String ActualClaimStatus= claimtransaction1.GetClaimStatus();
			softassert = Assertion.assertEquals(ActualClaimStatus, "On Hold", softassert);
			softassert = assertAll(softassert, "Transaction Status before Manger's approval: "+ ActualClaimStatus);
			
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	@Test(priority=2, dataProvider ="DataProvider" , description="")
	public void Managers_Approval (ListOrderedMap<String,String> values)
	{	
		try {
			PhoenixLogin phoenixmangerlogin = new PhoenixLogin(driver);
			phoenixmangerlogin.setManagerusername(values.get("Manager Username"));
			phoenixmangerlogin.setManagerPassword(values.get("Manager Password"));
			phoenixmangerlogin.clickonsignin();
			
			ApproveManager approvemang1 = new ApproveManager(driver);
			approvemang1.ClickOnHeldTransaction();
			approvemang1.ClickOnlatestapproveddrop();
			Thread.sleep(3000);
			takeScreenShot(driver, "Held Transaction listed in Manger's Assigned To me Tab");
			approvemang1.ClickOnRejectButton();
		
			softassert = Assertion.assertEquals("", "", softassert);
			softassert = assertAll(softassert, " Manager Approval");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	@Test(priority=3, dataProvider ="DataProvider" , description="")
	public void TransactionearsedafterManagersRejection (ListOrderedMap<String,String> values)
	{	
		try {
			PhoenixLogin phoenixlogin = new PhoenixLogin(driver);
			phoenixlogin.setusername(values.get("User Name"));
			phoenixlogin.setPassword(values.get("Password"));
			phoenixlogin.clickonsignin();
			
			Searchclaim claimobj = new Searchclaim(driver);
			claimobj.ClickOnClaimSearch();
			claimobj.SetClaimnumber(claimnumber);
			claimobj.ClickOnSearch();
			claimobj.clickonClaim(claimnumber);
			
			PhoenixClaimTransaction claimtransaction2 = new PhoenixClaimTransaction(driver) ;
			claimtransaction2.clickonTransactionTab();
			Thread.sleep(4000);
			takeScreenShot(driver, "Transaction status after Manager's Rejection");
			String ntpaymt=claimtransaction2.GetNetPayAmt();
			softassert = Assertion.assertEquals(ntpaymt, "0.00", softassert);
			softassert = assertAll(softassert, "Transaction earsed after Manager's Rejection");
			
} catch (Exception e) {	
	e.printStackTrace();
}
}
}

