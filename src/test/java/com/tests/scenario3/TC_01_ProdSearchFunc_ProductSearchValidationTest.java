package com.tests.scenario3;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.tests.BaseTest;

public class TC_01_ProdSearchFunc_ProductSearchValidationTest extends BaseTest {
	
	@Test
	public void verifyProductSearchFunctionalityTest() {
		logger.info("== Starting TC_01_ProdSearchFunc_ProductSearchValidationTest ==");
		try {
			//goto homepage enter prod in search bar hit search
			logger.info("navigating to site and searching product");
			homePage = new HomePage(driver);
			homePage.enterProductInSearchBox(prop.getProperty("searchProductName"));
			homePage.clickSearchBtn();
			
			//validate the product is present in the list by name & price
			logger.info("validating the search result");
			Assert.assertTrue(homePage.isSearchProductInList(prop.getProperty("searchProductName").trim(), prop.getProperty("searchProductPrice").trim()),"Test failed! no correct product found..");
			
			logger.info("test method completed");	
			
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug("This are debug logs: {}",e);
			Assert.fail();
		}
		logger.info("== Finished TC_001_RegistrationFunc_ValidCredRegistrationOnlyMandatoryFieldsTest ==");
	}
}
