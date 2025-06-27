package com.tests.scenario2;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.tests.BaseTest;

public class TC_01_LoginFunc_LoginUsingValidCredsTest extends BaseTest {

		@Test
		public void verifyLoginUsingValidCreds() throws InterruptedException {
			logger.info("== Starting TC_01_LoginFunc_LoginUsingValidCredsTest ==");
			try {
				//navigate to landing page and click on sign in link
				logger.info("navigating to login page");
				homePage = new HomePage(driver);
				homePage.clickOnSignInLnk();
				
				//on login page fill all the details like email, pwd and click on sign in btn
				logger.info("entering all the sign in details and click on sign in");
				loginPage = new LoginPage(driver);
				loginPage.enterEmail(prop.getProperty("userSignInEmail").trim());
				loginPage.enterPassword(prop.getProperty("userSignInPwd").trim());
				loginPage.clickOnSignInBtn();
			
				//validate welcome user text msg and logout
				logger.info("validating user text and logout");
	//			Assert.assertTrue(homePage.isWelcomeMsgCorrect1("Welcome, "+prop.getProperty("userName").trim()+"!"));
	//			Assert.assertTrue(homePage.isWelcomeMessageCorrect("Welcome, "+prop.getProperty("userName").trim()+"!"));
				homePage.clickOnDrpDwnArrowNextToWelecomMsg();
				Assert.assertTrue(homePage.isSignOutOptVisible(),"Test failed! sign out not visible..");
				homePage.clickOnSignOut();
				
			}catch(Exception e) {
				logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
				Assert.fail();
			}
			
			logger.info("== Finished TC_01_LoginFunc_LoginUsingValidCredsTest ==");
		}
}
