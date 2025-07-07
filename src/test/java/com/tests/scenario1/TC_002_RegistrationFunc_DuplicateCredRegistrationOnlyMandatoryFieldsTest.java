package com.tests.scenario1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.RegistrationPage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;

public class TC_002_RegistrationFunc_DuplicateCredRegistrationOnlyMandatoryFieldsTest extends BaseTest{
	
	@Test
	public void verifyAccRegWithMandatoryFieldsOnly() {
		logger.info("== Starting TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			//goto app url landing page and goto registration page
			logger.info("navigating homepage hitting create acc btn");
			homePage = new HomePage(driver, actionLogger);
			homePage.clickOnCreateAccLnk();
			
			//enter form details and click on create account
			logger.info("filling registration form details with duplicate email");
			registrationPage = new RegistrationPage(driver, actionLogger);
			registrationPage.enterFname(fnameGenerator().trim());
			registrationPage.enterLname(lnameGenerator().trim());
			registrationPage.enterEmail(prop.getProperty("userSignInEmail").trim());
			
			String pwd = pwdGenerator().trim()+"@12345";
			registrationPage.enterPassword(pwd);
			registrationPage.reEnterPwd(pwd);
			Thread.sleep(1000);
			registrationPage.clickOnCreateAccBtn();
						
			//validate duplicate email error msg
			logger.info("validating duplicate email err msg");
			Assert.assertTrue(registrationPage.isEmailExitErrorMsgDisplayed(), "Test failed! email already exist error not displayed..");
			
			logger.info("test method completed");

		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			Assert.fail();
		}
		logger.info("== Finished TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
	}
}
