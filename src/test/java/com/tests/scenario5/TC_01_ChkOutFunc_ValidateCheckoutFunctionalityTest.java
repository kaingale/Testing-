package com.tests.scenario5;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.CartPage;
import com.pageObjects.CheckoutPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.ProductDetailsPage;
import com.tests.BaseTest;

public class TC_01_ChkOutFunc_ValidateCheckoutFunctionalityTest extends BaseTest{
		
	@Test
	public void verifyCheckoutFunctionlity() throws InterruptedException {
		logger.info("== Starting TC_01_ChkOutFunc_ValidateCheckoutFunctionalityTest ==");
		try {
			//navigate to landing page and click on sign in link
			logger.info("navigating to login page");
			HomePage homePage = new HomePage(driver);
			homePage.clickOnSignInLnk();
			
			//on login page fill all the details like email, pwd and click on sign in btn
			logger.info("entering all the sign in details and click on sign in");
			LoginPage loginpage = new LoginPage(driver);
			loginpage.enterEmail(prop.getProperty("demoUserEmail").trim());
			loginpage.enterPassword(prop.getProperty("demoUserPwd").trim());
			loginpage.clickOnSignInBtn();
			Thread.sleep(3000);
		
			//search product -> search product -> goto product details
			logger.info("searching product, goto product details");
			homePage.enterProductInSearchBox(prop.getProperty("searchProductName").trim());
			homePage.clickSearchBtn();
			Thread.sleep(3000);
			homePage.searchProductAndGotoProductDetailsPage(prop.getProperty("searchProductName").trim());
			Thread.sleep(10000);
			
			//add product details -> add to cart
			logger.info("entering product details, adding to cart");
			productPage = new ProductDetailsPage(driver);
			productPage.clearCartBeforeTest();
			Thread.sleep(3000);
			productPage.chooseSizeForProduct(prop.getProperty("productSize").trim());
			productPage.chooseColorForProduct(prop.getProperty("productColor").trim());
			Thread.sleep(5000);
			productPage.enterQuantity(prop.getProperty("productQuantity").trim());
			productPage.clickAddToCart();
			Thread.sleep(5000);
			Assert.assertTrue(productPage.isAddToCartSuccessMsgDisplayed(),"Test failed! add to cart success msg not diplayed..");
			productPage.openShowCartPopup();
			productPage.clickOnPopUpCheckoutBtn();
			Thread.sleep(3000);
						
			//goto checkout page -> add details like shipping, payment -> submit order
			logger.info("going checkout, entering shipping details, goto payements");
			checkoutPage = new CheckoutPage(driver);
			Assert.assertTrue(checkoutPage.isOrderSummeryDetailsCorrect(prop.getProperty("productQuantity").trim(), prop.getProperty("productColor").trim(), prop.getProperty("productSize").trim()),"Test failed! details mismatch..");
			Thread.sleep(3000);
			checkoutPage.chooseCorrectShippingPrice(prop.getProperty("shippingWay"));
			checkoutPage.clickOnNextBtn();
			Thread.sleep(3000);

			//validate final price -> place order
			Assert.assertTrue(checkoutPage.isFinalPriceAfterShippinRight(prop.getProperty("paymentFinalPrice")),"Test failed! final shipping price wrong..");
			checkoutPage.clickOnPlaceOrder();
			Assert.assertTrue(checkoutPage.isOrderPlacedSuccessMsgDisplayed(),"Test failed! order success msg not displayed");
			Thread.sleep(10000);
			
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
}
