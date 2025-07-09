package com.tests.scenario11;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.CheckoutPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.ProductDetailsPage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;

public class TC_01_AccUpdateFunc_ValidateAccountDetailsUpdationTest extends BaseTest{
		
	@Test
	public void verifyAccUpdateFunctionality() throws InterruptedException {
		logger.info("== Starting TC_01_AccUpdateFunc_ValidateAccountDetailsUpdationTest ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			//navigate to landing page and click on sign in link
			logger.info("navigating to login page");
			homePage = new HomePage(driver, actionLogger);
			homePage.clickOnSignInLnk();
			
			//on login page fill all the details like email, pwd and click on sign in btn
			logger.info("entering all the sign in details and click on sign in");
			loginPage = new LoginPage(driver, actionLogger);
			loginPage.enterEmail(prop.getProperty("userSignInEmail").trim());
			loginPage.enterPassword(prop.getProperty("userSignInPwd").trim());
			loginPage.clickOnSignInBtn();
			
			//goto acc page -> click on edit -> 
			homePage.clickOnDrpDwnArrowNextToWelecomMsg();
			homePage.clickOnMyaccDrpDwnOpt();
			
			//click on edit details -> enter new details ->click save
			myAccountPage = new MyAccountPage(driver, actionLogger);
			myAccountPage.clickOnEditDetails();
			myAccountPage.updateFirstname(prop.getProperty("newUserFname").trim());
			myAccountPage.updateLastname(prop.getProperty("newUserLname").trim());
			myAccountPage.clickOnChangeEmailCheckBox();
			myAccountPage.updateEmail(prop.getProperty("newUserEmail").trim());
			myAccountPage.enterCurrentPassword(prop.getProperty("userSignInPwd").trim());	
			myAccountPage.clickOnSaveUpdatesBtn();
			
			//Validate update
			Assert.assertTrue(loginPage.isAccUpdateSuccessMsgDisplayed(),"Test failed! account update success msg not displayed..");
			
			logger.info("test method completed");
			
		}catch(AssertionError ae) {
			logger.error("Test failed due to an exception in catch block: {}", ae.getMessage());
			logger.debug(ae);
			throw ae;
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug(e);
			throw new RuntimeException("Test execution failed!", e);
		}
		
		logger.info("== Finished TC_01_AccUpdateFunc_ValidateAccountDetailsUpdationTest ==");
	}
}
