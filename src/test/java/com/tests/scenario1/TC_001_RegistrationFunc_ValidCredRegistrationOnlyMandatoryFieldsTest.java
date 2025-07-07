package com.tests.scenario1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.RegistrationPage;
import com.tests.BaseTest;
import com.tests.BaseTest2;
import com.utilities.ActionLogger;

public class TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest extends BaseTest{
	
	@Test
	public void verifyAccRegWithMandatoryFieldsOnly() {
		logger.info("== Starting TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			//goto app url landing page and goto registration page
			logger.info("navigating to homepage and hitting create acc btn");
			homePage = new HomePage(driver, actionLogger);
			homePage.clickOnCreateAccLnk();
			
			//enter form details and click on create account
			logger.info("filling registration form details");
			registrationPage = new RegistrationPage(driver, actionLogger);
			registrationPage.enterFname(fnameGenerator().trim());
			registrationPage.enterLname(lnameGenerator().trim());
			registrationPage.enterEmail(emailGenerator().trim());
			
			String pwd = pwdGenerator().trim()+"@12345";
			registrationPage.enterPassword(pwd);
			registrationPage.reEnterPwd(pwd);
			Thread.sleep(1000);
			registrationPage.clickOnCreateAccBtn();
						
			//validate my acc page header, acc success msg
			logger.info("validating header and acc created success msg");
			MyAccountPage myAccountpage = new MyAccountPage(driver, actionLogger);
			Assert.assertTrue(myAccountpage.isMyAccountHeaderDisplayed(),"Test failed: Expected my acc header not displayed..");
			Assert.assertTrue(myAccountpage.isAccCreatedSuccessMsgDisplayed(),"Test failed: Expected Acc created success msg not displayed..");
			
			logger.info("test method completed");

		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug(e);
			Assert.fail();
		}
		logger.info("== Finished TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
	}
}
