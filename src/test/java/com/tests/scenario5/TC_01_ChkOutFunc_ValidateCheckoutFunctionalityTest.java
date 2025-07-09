package com.tests.scenario5;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.CheckoutPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProductDetailsPage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;
import com.utilities.ScenarioContextForSavingDynamicValues;

public class TC_01_ChkOutFunc_ValidateCheckoutFunctionalityTest extends BaseTest{
		
	@Test
	public void verifyCheckoutFunctionlity() throws InterruptedException {
		logger.info("== Starting TC_01_ChkOutFunc_ValidateCheckoutFunctionalityTest ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			scenarioContext =  new ScenarioContextForSavingDynamicValues();
			//navigate to landing page and click on sign in link
			logger.info("navigating to login page");
			homePage = new HomePage(driver, actionLogger,scenarioContext);
			homePage.clickOnSignInLnk();
			
			//on login page fill all the details like email, pwd and click on sign in btn
			logger.info("entering all the sign in details and click on sign in");
			loginPage = new LoginPage(driver, actionLogger);
			loginPage.enterEmail(prop.getProperty("demoUserEmail").trim());
			loginPage.enterPassword(prop.getProperty("demoUserPwd").trim());
			loginPage.clickOnSignInBtn();
		
			//search product -> search product -> goto product details
			logger.info("searching product, goto product details");
			homePage.enterProductInSearchBox(prop.getProperty("searchProductName").trim());
			homePage.clickSearchBtn();
			homePage.searchProductAndGotoProductDetailsPage(prop.getProperty("searchProductName").trim());
			
			//add product details -> add to cart
			logger.info("entering product details, adding to cart");
			productPage = new ProductDetailsPage(driver, actionLogger);
			productPage.clearCartBeforeTest();
			productPage.chooseSizeForProduct(prop.getProperty("productSize").trim());
			productPage.chooseColorForProduct(prop.getProperty("productColor").trim());
			productPage.enterQuantity(prop.getProperty("productQuantity").trim());
			productPage.clickAddToCart();
			Assert.assertTrue(productPage.isAddToCartSuccessMsgDisplayed(),"Test failed! add to cart success msg not diplayed..");
			productPage.openShowCartPopup();
			productPage.clickOnPopUpCheckoutBtn();
						
			//goto checkout page -> add details like shipping, payment -> submit order
			logger.info("going checkout, entering shipping details, goto payements");
			checkoutPage = new CheckoutPage(driver, actionLogger, scenarioContext);
			Assert.assertTrue(checkoutPage.isOrderSummeryDetailsCorrect(prop.getProperty("productQuantity").trim(), prop.getProperty("productColor").trim(), prop.getProperty("productSize").trim()),"Test failed! details mismatch..");
			checkoutPage.chooseCorrectShippingPrice(prop.getProperty("shippingWay"));
			checkoutPage.clickOnNextBtn();

			//validate final price -> place order
			String finalPriceCal = priceCalculator(scenarioContext.getContext("normalPriceOfProduct").toString(), prop.getProperty("productQuantity"), scenarioContext.getContext("shippingPriceOnCheckOutPage").toString());
			Assert.assertTrue(checkoutPage.isFinalPriceAfterShippinRight(finalPriceCal),"Test failed! final shipping price wrong..");
			
			checkoutPage.clickOnPlaceOrder();
			Assert.assertTrue(checkoutPage.isOrderPlacedSuccessMsgDisplayed(),"Test failed! order success msg not displayed");
			
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
		
		logger.info("== Finished TC_01_ChkOutFunc_ValidateCheckoutFunctionalityTest ==");
	}
	
	private String priceCalculator(String scenarioPrice, String productQuantity, String shippingPrice) {
		String onlyPrice = scenarioPrice.replaceAll("[^\\d.]", "");
		String shippingPrice1 = shippingPrice.replaceAll("[^\\d.]", "");
		Double prodQuantityNum = Double.parseDouble(productQuantity);
		Double price = (Double.parseDouble(onlyPrice) * prodQuantityNum) + Double.parseDouble(shippingPrice1) + Double.parseDouble(prop.getProperty("taxPriceFor2Items"));
		return "$" + String.format("%.2f",price);
	}
}
