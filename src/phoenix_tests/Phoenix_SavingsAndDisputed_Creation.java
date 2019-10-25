package phoenix_tests;

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
import phoenix_pages.PhoenixClaimNotes;
import phoenix_pages.PhoenixClaimSummary;
import phoenix_pages.PhoenixClaimTransaction;
import phoenix_pages.PhoenixLocationDetails;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.PhoenixNewClaim;
import phoenix_pages.PhoenixPolicyDetails;
import phoenix_pages.SavingsAndDisputedRecord_Creation;
import phoenix_pages.Phoenix_SavingsAndDisputedRecord_Tab;

public class Phoenix_SavingsAndDisputed_Creation extends BaseTest {
		
		private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_Claims_Savings&Disputed.xlsx" ;
		private String sheetname = "phoenix";
		private ReadWriteExcel readwriteexcel;
		private List<Object[][]> softassert  ;
		
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
		public void Savings_Disputed (ListOrderedMap<String,String> values)
		
		{	//SoftAssert softAsset = new SoftAssert();
			softassert  = initialisesoftassert(softassert);
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
				claimdetails.setDescriptionofLoss(values.get("Description Of Loss"));
				claimdetails.setInjury(values.get("Injury"));
				claimdetails.checkSalvage();
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
				
				Phoenix_SavingsAndDisputedRecord_Tab SavingsAndDisputed_Tab = new Phoenix_SavingsAndDisputedRecord_Tab(driver) ;
				SavingsAndDisputed_Tab.ClickOnSavings_Disputedtab();
				takeScreenShot(driver, "Disputed And Savings tab before creating Disputed Transaction");
				
				//Disputed Transaction1 creation
				
				SavingsAndDisputed_Tab.ClickOnSavings_NewDisputedButton();
				
				SavingsAndDisputedRecord_Creation  SavingsCreation =new SavingsAndDisputedRecord_Creation(driver);
				SavingsCreation.ClickOnType(values.get("Disputed_Type"));
				SavingsCreation.SetInvoice(values.get("Disputed_Invoice"));
				SavingsCreation.SetInvoiceDate(values.get("Disputed_Invoice Date"));
				String Resolution = values.get("Disputed_Resolution").trim();
				SavingsCreation.ClickOnResolution(Resolution);		
				String TotalAmount = values.get("Disputed_Total Amount");
				String ResolutionAmount = values.get("Disputed_Resolution Amount");
				
            
				
//				Savings Amount Validation
				
				double TotalAmountValue = Double.parseDouble(TotalAmount);
				double ResolutionAmountValue = 0.00;
				if(!ResolutionAmount.contains("NA")) {
				ResolutionAmountValue = Double.parseDouble(ResolutionAmount);
				}
				
				SavingsCreation.SetTotalAmount(TotalAmount);
				SavingsCreation.SetResolutionAmount(ResolutionAmount);	
				String ActualSavingsAmount = SavingsCreation.GetSavingsAmount();
				
				if(!ResolutionAmount.contains("NA")) {
					double ActualSavingAmnt=  Double.parseDouble(ActualSavingsAmount);
					System.out.println("Actual Savings Amount: " +ActualSavingsAmount);
					double ExpectedSavingsAmount = TotalAmountValue - ResolutionAmountValue;
					System.out.println("Expected Savings Amount: " +ExpectedSavingsAmount);
					softassert = Assertion.assertEquals(ActualSavingAmnt, ExpectedSavingsAmount, softassert, "Not Matched double");
				}
				else {
					String expSavings="";
					softassert = Assertion.assertEquals(ActualSavingsAmount, expSavings, softassert, "Not Matched null");
					
				}
				
				softassert = Assertion.assertAll(softassert, "Calculation of Saving Amount: "+ActualSavingsAmount );
				System.out.println(Resolution);
//				Status Validation as per Resolution  		
				String Expectedstatus;
				if (Resolution.equalsIgnoreCase("Pending")) {
					Expectedstatus = "Open";
	
				}
				else {
					Expectedstatus = "Closed";
				}
				String actualstatus = SavingsCreation.GetStatus();
				System.out.println(actualstatus);
				softassert = Assertion.assertEquals(actualstatus, Expectedstatus, softassert);
				softassert = Assertion.assertAll(softassert, "Status as per Resolution : "+actualstatus );
				
				SavingsCreation.ClickOnCurrency(values.get("Disputed_Currency"));
				SavingsCreation.ClickOnReason(values.get("Disputed_Reason"));
				driver.findElement(By.xpath("//body")).click();
				Thread.sleep(2000);
				takeScreenShot(driver, "Savings And Disputed Transaction creation screen with User input");
				SavingsCreation.ClickOnCreateDisputed();
				takeScreenShot(driver, "Transaction list displayed under Savings And Disputed tab");
				
				System.out.println("Savings And Disputed Record1 Created Succesfully");
				
				
			//Disputed Transaction2 creation
				
                SavingsAndDisputed_Tab.ClickOnSavings_NewDisputedButton();
				SavingsCreation.ClickOnType(values.get("Disputed_Type2"));
				SavingsCreation.SetInvoice(values.get("Disputed_Invoice2"));
				SavingsCreation.SetInvoiceDate(values.get("Disputed_Invoice Date2"));
				String Resolution1 = values.get("Disputed_Resolution2").trim();
				SavingsCreation.ClickOnResolution(Resolution1);		
				String TotalAmount1 = values.get("Disputed_Total Amount2");
				String ResolutionAmount1 = values.get("Disputed_Resolution Amount2");
				
            
				
//				Savings Amount Validation
				
				double TotalAmountValue1 = Double.parseDouble(TotalAmount1);
				double ResolutionAmountValue1 = Double.parseDouble(ResolutionAmount1);
				double ExpectedSavingsAmount1 = TotalAmountValue1 - ResolutionAmountValue1;
				System.out.println("Expected Savings Amount: " +ExpectedSavingsAmount1);
				SavingsCreation.SetTotalAmount(TotalAmount1);
				SavingsCreation.SetResolutionAmount(ResolutionAmount1);	
				String ActualSavingsAmount1 = SavingsCreation.GetSavingsAmount();
				double ActualSavingAmnt1=  Double.parseDouble(ActualSavingsAmount1);
				System.out.println("Actual Savings Amount: " +ActualSavingsAmount1);
				softassert = Assertion.assertEquals(ActualSavingAmnt1, ExpectedSavingsAmount1, softassert, "Not Matched");
				softassert = Assertion.assertAll(softassert, "Calculation of Saving Amount: "+ActualSavingsAmount1 );
				System.out.println(Resolution);
//				Status Validation as per Resolution  		
				String Expectedstatus1;
				if (Resolution1.equalsIgnoreCase("Pending")) {
					Expectedstatus1 = "Open";
	
				}
				else {
					Expectedstatus1 = "Closed";
				}
				String actualstatus1 = SavingsCreation.GetStatus();
				System.out.println(actualstatus1);
				softassert = Assertion.assertEquals(actualstatus1, Expectedstatus1, softassert);
				softassert = Assertion.assertAll(softassert, "Status as per Resolution : "+actualstatus1 );
				
				SavingsCreation.ClickOnCurrency(values.get("Disputed_Currency2"));
				SavingsCreation.ClickOnReason(values.get("Disputed_Reason2"));
				driver.findElement(By.xpath("//body")).click();
				Thread.sleep(2000);
				takeScreenShot(driver, "Savings And Disputed Transaction2 creation screen with User input");
				SavingsCreation.ClickOnCreateDisputed();
				takeScreenShot(driver, "Transaction list2 displayed under Savings And Disputed tab");
				
				System.out.println("Savings And Disputed Record Created Succesfully");
				
				
				
				
				
			} catch (Exception e) {	
				e.printStackTrace();
			}
		}
	}

