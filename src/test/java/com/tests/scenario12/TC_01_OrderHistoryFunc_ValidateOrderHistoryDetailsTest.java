package com.tests.scenario12;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.OrderHistoryPage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;

public class TC_01_OrderHistoryFunc_ValidateOrderHistoryDetailsTest extends BaseTest{

	@Test
	public void verifyOrdersInOrderHistory() throws InterruptedException {
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
			loginPage.enterEmail(prop.getProperty("demoUserEmail").trim());
			loginPage.enterPassword(prop.getProperty("demoUserPwd").trim());
			loginPage.clickOnSignInBtn();
			
			//goto acc page -> goto my orders 
			homePage.clickOnDrpDwnArrowNextToWelecomMsg();
			homePage.clickOnMyaccDrpDwnOpt();
			myAccountPage = new MyAccountPage(driver, actionLogger);
			myAccountPage.clickOnMyOrdersLnk();
			
			//goto view orders of correct orderid -> validate the details
			orderHistoryPage = new OrderHistoryPage(driver, actionLogger);
			orderHistoryPage.searchOrderIdInListAndGotoOrderDetails(prop.getProperty("orderId").trim());
			Assert.assertTrue(orderHistoryPage.areAllDetailsCorrect(prop.getProperty("searchProductName").trim(), prop.getProperty("productSize").trim(), prop.getProperty("productColor").trim(), prop.getProperty("paymentFinalPrice").trim()),"Test failed! order history details not correct..");	
			Thread.sleep(5000);
			
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
