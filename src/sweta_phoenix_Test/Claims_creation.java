package sweta_phoenix_Test;

import org.testng.annotations.Test;

import phoenix_automation_framework.BaseTest;
import sweta_Phoenix_Pages.ClaimScreen_ClaimCreation;
import sweta_Phoenix_Pages.Claimsearch_Tab;
import sweta_Phoenix_Pages.ContractSearch_ClaimCreation;
import sweta_Phoenix_Pages.Login;
import sweta_Phoenix_Pages.Phoenix_Dashboard;

public class Claims_creation extends BaseTest {
	
	@Test
	public void createClaim() {
		
		Login login= new Login(driver);
		
		login.setUsername("sweta.das@awacservices.com");
		login.setpassword("Allied22$");
		login.ClickonSignIn();
		
		Phoenix_Dashboard ClaimSearch = new Phoenix_Dashboard(driver);
		ClaimSearch.clickonClaimSearch();
		
		Claimsearch_Tab NewClaimButton = new Claimsearch_Tab(driver);
		NewClaimButton.Clickonnewclaimbutton();
		
		ContractSearch_ClaimCreation Csearch = new ContractSearch_ClaimCreation(driver);
		Csearch.setContractCode("14432");
		Csearch.ClickonSearchbutton();
		Csearch.ClickonFirstRow();
		Csearch.ClickonNextStep();
	
		ClaimScreen_ClaimCreation Ccreation = new ClaimScreen_ClaimCreation(driver);
		Ccreation.SetSectionSelection();
	
	}

}
