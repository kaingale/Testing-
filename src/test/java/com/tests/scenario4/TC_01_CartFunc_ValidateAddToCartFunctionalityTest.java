package com.tests.scenario4;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.CartPage;
import com.pageObjects.HomePage;
import com.pageObjects.ProductDetailsPage;
import com.tests.BaseTest;

public class TC_01_CartFunc_ValidateAddToCartFunctionalityTest extends BaseTest {
	
	@Test
	public void verifyAddToCartFunctionalityTest() {
		logger.info("== Starting TC_01_CartFunc_ValidateAddToCartFunctionalityTest ==");
		try {
			//goto homepage enter prod in search bar hit search
			logger.info("navigating to site and searching product");
			homePage = new HomePage(driver);
			homePage.enterProductInSearchBox(prop.getProperty("searchProductName"));
			homePage.clickSearchBtn();
			
			//go to product details page and add the product to cart
			homePage.searchProductAndGotoProductDetailsPage(prop.getProperty("searchProductName"));
			productPage = new ProductDetailsPage(driver);
			productPage.chooseSizeForProduct(prop.getProperty("productSize"));
			productPage.chooseColorForProduct(prop.getProperty("productColor"));
			productPage.enterQuantity(prop.getProperty("productQuantity"));
			productPage.clickAddToCart();
			Assert.assertTrue(productPage.isAddToCartSuccessMsgDisplayed(),"Test failed! add to cart success msg not diplayed..");
			
			//goto cart page and validate correct product and price
			productPage.openShowCartPopup();
			productPage.gotoCartPage();
			
			cartPage = new CartPage(driver);
			Assert.assertTrue(cartPage.isCorrectProductAddedToCart(prop.getProperty("cartProductName"),prop.getProperty("cartProductPrice")),"Test failed! no correct product added in cartproudct list..");	
			Assert.assertTrue(cartPage.isFinalPriceRight(prop.getProperty("cartFinalPrice")));
			Thread.sleep(5000);
			
			logger.info("test method completed");	
			
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug("This are debug logs: {}",e);
			Assert.fail();
		}
		logger.info("== Finished TC_01_CartFunc_ValidateAddToCartFunctionalityTest ==");
	}
}
