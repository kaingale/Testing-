package com.tests.scenario4;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.CartPage;
import com.pageObjects.HomePage;
import com.pageObjects.ProductDetailsPage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;
import com.utilities.ScenarioContextForSavingDynamicValues;

public class TC_01_CartFunc_ValidateAddToCartFunctionalityTest extends BaseTest {
	
	@Test
	public void verifyAddToCartFunctionalityTest() {
		logger.info("== Starting TC_01_CartFunc_ValidateAddToCartFunctionalityTest ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			scenarioContext = new ScenarioContextForSavingDynamicValues();
			//goto homepage enter prod in search bar hit search
			logger.info("navigating to site and searching product");
			homePage = new HomePage(driver, actionLogger, scenarioContext);
			homePage.enterProductInSearchBox(prop.getProperty("searchProductName"));
			homePage.clickSearchBtn();
			
			//go to product details page and add the product to cart
			homePage.searchProductAndGotoProductDetailsPage(prop.getProperty("searchProductName"));
			productPage = new ProductDetailsPage(driver, actionLogger);
			productPage.chooseSizeForProduct(prop.getProperty("productSize"));
			productPage.chooseColorForProduct(prop.getProperty("productColor"));
			productPage.enterQuantity(prop.getProperty("productQuantity"));
			productPage.clickAddToCart();
			Assert.assertTrue(productPage.isAddToCartSuccessMsgDisplayed(),"Test failed! add to cart success msg not diplayed..");
			
			//goto cart page and validate correct product and price
			productPage.openShowCartPopup();
			productPage.gotoCartPage();
			
			cartPage = new CartPage(driver, actionLogger);
			Assert.assertTrue(cartPage.isCorrectProductAddedToCart(prop.getProperty("cartProductName"),prop.getProperty("cartProductPrice")),"Test failed! no correct product added in cartproudct list..");	
			
			//calculate price & use for assertion
			String ourPriceCal = priceCalculator(scenarioContext.getContext("normalPriceOfProduct").toString(), prop.getProperty("productQuantity"));
			Assert.assertTrue(cartPage.isFinalPriceRight(ourPriceCal),"Test failed! cart product price not right..");
			
			logger.info("test method completed");	
			
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug("This are debug logs: {}",e);
			Assert.fail();
		}
		logger.info("== Finished TC_01_CartFunc_ValidateAddToCartFunctionalityTest ==");
	}
	
	private String priceCalculator(String scenarioPrice, String productQuantity) {
		String onlyPrice = scenarioPrice.replaceAll("[^\\d.]", "");
		Double prodQuantityNum = Double.parseDouble(productQuantity);
		Double price = Double.parseDouble(onlyPrice) * prodQuantityNum;
		return "$" + String.format("%.2f",price);
	}
}
