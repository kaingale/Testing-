package com.tests.scenario1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.LandingPage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.RegistrationPage;
import com.tests.BaseTest;

public class TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest extends BaseTest{
	
	@Test
	public void verifyAccRegWithMandatoryFieldsOnly() {
		
		try {
			//goto app url landing page and goto registration page
			landingPage = new LandingPage(driver);
			landingPage.clickOnCreateAccLnk();
			
			//enter form details and click on create account
			registrationPage = new RegistrationPage(driver);
			registrationPage.enterFname(fnameGenerator().trim());
			registrationPage.enterLname(lnameGenerator().trim());
			registrationPage.enterEmail(emailGenerator().trim());
			
			String pwd = pwdGenerator().trim()+"@12345";
			registrationPage.enterPassword(pwd);
			registrationPage.reEnterPwd(pwd);
			registrationPage.clickOnCreateAccBtn();
						
			//validate my acc page header, user name , user email
			myAccountPage = new MyAccountPage(driver);
			Assert.assertTrue(myAccountPage.isMyAccountHeaderDisplayed(),"Test failed: Expected my acc header not displayed..");
			Assert.assertTrue(myAccountPage.isAccCreatedSuccessMsgDisplayed(),"Test failed: Expected Acc created success msg not displayed..");
			Thread.sleep(5000);
			
		}catch(Exception e) {
			System.out.println("Test failed due to an exception.."+e.getMessage());
			Assert.fail();
		}
	}
}
