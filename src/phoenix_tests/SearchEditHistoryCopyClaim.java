package phoenix_tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.collections4.map.ListOrderedMap;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import phoenix_automation_framework.BaseTest;
import phoenix_automation_framework.ReadWriteExcel;
import phoenix_pages.PhoenixCopyClaim;
import phoenix_pages.PhoenixEditClaim;
import phoenix_pages.PhoenixLogin;
import phoenix_pages.Phoenix_HistoryTab;
import phoenix_pages.Searchclaim;

public class SearchEditHistoryCopyClaim extends BaseTest {
	private String workbook=System.getProperty("user.dir")+File.separator+"test_data"+ File.separator+"Phoenix_ClaimSearch_Edit_Copy.xlsx" ;
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
	
	@Test(dataProvider ="DataProvider" , description="")
	public void Phoenix_Search (ListOrderedMap<String,String> values)
	{	
		try {		
			PhoenixLogin phoenixlogin = new PhoenixLogin(driver);
			phoenixlogin.setusername(values.get("User Name"));
			phoenixlogin.setPassword(values.get("Password"));
			phoenixlogin.clickonsignin();
			
			Searchclaim claimobj = new Searchclaim(driver);
			claimobj.ClickOnClaimSearch();
			claimobj.SetClaimnumber(values.get("Claim Number"));
			claimobj.SetUWyear(values.get("Contract Year"));
			claimobj.SetCedingCompany(values.get("Company"));
			claimobj.SetBrokerName(values.get("Broker"));
			//claimobj.SetBrokerclaimNumber(values.get("Broker Claim ref"));
			claimobj.SetDateOfLoss(values.get("Loss Date"));
			claimobj.SetNameInsured(values.get("Insured"));
			claimobj.SetClaimant(values.get("Claimant"));
			claimobj.SetExaminer(values.get("Examiner"));
			claimobj.SetClaimType(values.get("claim Type"));
			claimobj.ClickOnSearch();
			Thread.sleep(2000);
			takeScreenShot(driver, "Claim Searched");
			claimobj.clickonClaim(values.get("Claim Number"));
			
			PhoenixEditClaim editclaim = new PhoenixEditClaim(driver);
			Thread.sleep(2000);
			editclaim.clickonEdit();
			editclaim.setAddressLine1(values.get("Update Addressline"));
			editclaim.setCountry(values.get("Country_Edit"));
			editclaim.setDescriptionOfLoss(values.get("Description Of Loss_Edit"));
			Thread.sleep(10000);
			takeScreenShot(driver, "Claim Edited");
			editclaim.clickonSaveChanges();
			System.out.println("Claim Editted Succesfully");
			
			Phoenix_HistoryTab history = new Phoenix_HistoryTab(driver);
			history.clickonHistory();
			Thread.sleep(10000);
			takeScreenShot(driver, "History tab_Claim modification");
			history.clickonClose();
			
			PhoenixCopyClaim copyclaim = new PhoenixCopyClaim(driver);
			copyclaim.clickOnSameContract();
			Thread.sleep(2000);
			takeScreenShot(driver, "Claim abstract while copying claim in Same Contract");
			copyclaim.setClaimant(values.get("Claimant_Copy"));
			copyclaim.clickonNext();
			copyclaim.clickonNext();
			copyclaim.clickonNext();
		    copyclaim.clickonCreateClaim();
		    Thread.sleep(2000);
		    takeScreenShot(driver, "Claim copied in Same Contract");
			System.out.println("Claim copied Succesfully for Same Contract"); 
			
			copyclaim.clickOnDiffcontract();
			Thread.sleep(2000);
			copyclaim.setcontractno(values.get("Contract Or Company Name_Copy"));
			copyclaim.setUWYR(values.get("UW Year_Copy"));
			copyclaim.clickonSearchbutton();
			copyclaim.clickonselectclaim();
			takeScreenShot(driver, "Contract Search screen while copying claim in Different Contract");
			copyclaim.clickonNext();
			copyclaim.clickonNext();
			copyclaim.clickonNext();
			copyclaim.clickonNext();
			copyclaim.clickonCreateClaim();
			Thread.sleep(2000);
			takeScreenShot(driver, "Claim copied in Different Contract");
			System.out.println("Claim copied Succesfully for Different Contract"); 
			
		}catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
