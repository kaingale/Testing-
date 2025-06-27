package com.tests.scenario1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.RegistrationPage;
import com.tests.BaseTest;

public class TC_002_RegistrationFunc_DuplicateCredRegistrationOnlyMandatoryFieldsTest extends BaseTest{
	
	@Test
	public void verifyAccRegWithMandatoryFieldsOnly() {
		logger.info("== Starting TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
		try {
			//goto app url landing page and goto registration page
			homePage = new HomePage(driver);
			homePage.clickOnCreateAccLnk();
			
			//enter form details and click on create account
			registrationPage = new RegistrationPage(driver);
			registrationPage.enterFname(fnameGenerator().trim());
			registrationPage.enterLname(lnameGenerator().trim());
			registrationPage.enterEmail(prop.getProperty("userSignInEmail").trim());
			
			String pwd = pwdGenerator().trim()+"@12345";
			registrationPage.enterPassword(pwd);
			registrationPage.reEnterPwd(pwd);
			registrationPage.clickOnCreateAccBtn();
						
			//validate my acc page header, user name , user email
			Assert.assertTrue(registrationPage.isEmailExitErrorMsgDisplayed(), "Test failed! email already exist error not displayed..");
			
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			Assert.fail();
		}
		logger.info("== Finished TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
	}
}
