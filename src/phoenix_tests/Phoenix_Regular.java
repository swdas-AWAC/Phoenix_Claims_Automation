package phoenix_tests;

import static org.junit.Assert.assertNotNull;
import static phoenix_automation_framework.Assertion.assertAll;

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
import org.testng.asserts.SoftAssert;

import phoenix_automation_framework.Assertion;
import phoenix_automation_framework.BaseTest;
import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.AAD_validation;
import phoenix_pages.ApproveManager;
import phoenix_pages.PhoenixClaimDetails;
import phoenix_pages.PhoenixClaimNotes;
import phoenix_pages.PhoenixClaimSummary;
import phoenix_pages.PhoenixClaimTransaction;
import phoenix_pages.PhoenixLocationDetails;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.PhoenixNewClaim;
import phoenix_pages.PhoenixPolicyDetails;
import phoenix_pages.Phoenix_Claims_toppane;

public class Phoenix_Regular extends BaseTest {
	
	private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_Claims.xlsx" ;
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
	public void Regular_Claim (ListOrderedMap<String,String> values)
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
			PhoenixClaimTransaction claimtransaction = new PhoenixClaimTransaction(driver) ;
			claimtransaction.clickonTransactionTab();
			claimtransaction.clickonNewTransaction();
			claimtransaction.setStatementdate(values.get("Statement Date"));
			claimtransaction.setReportDate(values.get("Report Date"));
			claimtransaction.setlateReason(values.get("Late Reason"));
			claimtransaction.setPaidLoss(values.get("Paid Loss"));
			claimtransaction.setExpenses(values.get("Expenses"));
			claimtransaction.setOurExpenses(values.get("Our Expenses"));
			claimtransaction.setOSLossReserve(values.get("OS Loss Reserve"));
			claimtransaction.setLAEReserve(values.get("LAE Reserve"));
			claimtransaction.setACRReserve(values.get("ACR Reserve"));
		
			claimtransaction.clickonCreateTransaction();
			claimtransaction.ClickOnTransDrop();
			
			System.out.println("Transaction Created Succesfully");
			takeScreenShot(driver, "Transaction Created");

			PhoenixClaimNotes Notes = new PhoenixClaimNotes(driver);
			Notes.clickonNotesTab();
			Notes.setNotes(values.get("Notes"));
			Notes.clickonPostbutton();
			takeScreenShot(driver, "Notes Created");
			

			claimtransaction.clickonTransactionTab();
			claimtransaction.ClickonTransactionDropdown();
			String transaction_no=claimtransaction.GetTransNo();
			softassert = assertNotNull(transaction_no, softassert, "Transaction number Not Generated");
			softassert = assertAll(softassert, "Transaction no Generation:  "+transaction_no);
			
			
			Phoenix_Claims_toppane toppane= new Phoenix_Claims_toppane(driver);	
			String claim_no=toppane.GetClaimNo();
			System.out.print(claim_no);
			softassert = assertNotNull(claim_no, softassert, "Claim number Not Generated");
			softassert = assertAll(softassert, "Claim Generation:  "+claim_no);
			
			
			
			
			//softassert.assertNotNull(claim_no, "Claim Number Not Generated");
			//softassert.Assertion.
			/*AAD_validation validation = new AAD_validation(driver);
			double PaidLoss = validation.GetPaidLoss();
			double Expenses = validation.GetExpenses();
			double ExpectedpaymentAmount = PaidLoss + Expenses ;
			double ActualPaymentAmount = validation.GetPaymentAmount();
			softAsset.assertEquals(ActualPaymentAmount, ExpectedpaymentAmount, "Not matched");*/
			
			/*ApproveManager approvemang = new ApproveManager(driver);
			approvemang.SelectManager(values.get("Manager Name"));
			approvemang.ClickOnYes();
			
			//NewTab();			
			PhoenixLogin phoenixlogout = new PhoenixLogin(driver);
			Thread.sleep(5000);
			phoenixlogout.clickonLogOut();
			Thread.sleep(5000);
			
			PhoenixLogin phoenixmangerlogin = new PhoenixLogin(driver);
			phoenixmangerlogin.setManagerusername(values.get("Manager Username"));
			phoenixmangerlogin.setManagerPassword(values.get("Manager Password"));
			phoenixmangerlogin.clickonsignin();
			
			ApproveManager approvemang1 = new ApproveManager(driver);
			approvemang1.ClickOnHeldTransaction();
			approvemang1.ClickOnApprovedrop();
			approvemang1.ClickOnApproveButton();
			approvemang1.ManagerApproval(values.get("Manager Name"));*/
			
			//softAssert.assertAll();
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
