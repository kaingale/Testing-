package com.tests.scenario2;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.tests.BaseTest;
import com.tests.BaseTest2;
import com.utilities.ActionLogger;

public class TC_01_LoginFunc_LoginUsingValidCredsTest extends BaseTest {

		@Test
		public void verifyLoginUsingValidCreds() throws InterruptedException {
			logger.info("== Starting TC_01_LoginFunc_LoginUsingValidCredsTest ==");
			try {
				ActionLogger actionLogger = actionLoggerCreationHelper();
				//navigate to landing page and click on sign in link
				logger.info("navigating to login page");
				HomePage homepage = new HomePage(driver, actionLogger);
				homepage.clickOnSignInLnk();
				
				//on login page fill all the details like email, pwd and click on sign in btn
				logger.info("entering all the sign in details and click on sign in");
				LoginPage loginpage = new LoginPage(driver, actionLogger);
				loginpage.enterEmail(prop.getProperty("demoUserEmail").trim());
				loginpage.enterPassword(prop.getProperty("demoUserPwd").trim());
				loginpage.clickOnSignInBtn();
			
				//validate welcome user text msg and logout
				logger.info("validating user text and logout");	
				homepage.clickOnDrpDwnArrowNextToWelecomMsg();
				Assert.assertTrue(homepage.isSignOutOptVisible(),"Test failed! no sign out opt visible..");
				homepage.clickOnSignOut();
				
			}catch(AssertionError ae) {
				logger.error("Test failed due to an exception in catch block: {}", ae.getMessage());
				logger.debug(ae);
				throw ae;
			}catch(Exception e) {
				logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
				logger.debug(e);
				throw new RuntimeException("Test execution failed!", e);
			}
			
			logger.info("== Finished TC_01_LoginFunc_LoginUsingValidCredsTest ==");
		}
}
