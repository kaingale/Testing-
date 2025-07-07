package com.tests.scenario2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;

public class TC_02_LoginFunc_LoginUsingInValidCredsTest extends BaseTest {

		@Test
		public void verifyLoginUsingInValidCreds() throws InterruptedException {
			logger.info("== Starting TC_02_LoginFunc_LoginUsingInValidCredsTest ==");
			try {
				ActionLogger actionLogger = actionLoggerCreationHelper();
				//navigate to landing page and click on sign in link
				logger.info("navigating to login page");
				homePage = new HomePage(driver, actionLogger);
				homePage.clickOnSignInLnk();
				
				//on login page fill all the details like email, pwd and click on sign in btn
				logger.info("entering all the sign in details and click on sign in");
				loginPage = new LoginPage(driver, actionLogger);
				loginPage.enterEmail(emailGenerator1().trim());
				String pwd = pwdGenerator().trim()+"@12345";
				loginPage.enterPassword(pwd);
				loginPage.clickOnSignInBtn();
			
				//validate welcome user text msg and logout
				logger.info("validating sign in error msg");
				Assert.assertTrue(loginPage.isSignInErrorMsgDisplayed(),"Test failed! no error msg displayed..");
				
			}catch(Exception e) {
				logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
				Assert.fail();
			}
			
			logger.info("== Finished TC_02_LoginFunc_LoginUsingInValidCredsTest ==");
		}
}
