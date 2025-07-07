package com.tests.scenario10;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.tests.BaseTest;
import com.utilities.ActionLogger;

public class TC_01_AdvFilteringFunc_ValidAdvancedProductFilteringFunctionalityTest extends BaseTest {

	@Test
	public void verifyAdvancedFiltering() {
		logger.info("== Starting TC_01_AdvFilteringFunc_ValidAdvancedProductFilteringFunctionalityTest ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			//homepage -> whats new section -> hoodies 
			logger.info("navigating to site and product category");
			homePage = new HomePage(driver, actionLogger);
			homePage.clickOnWhatNewLnk();
			homePage.gotoMensSectionHoodies();
			homePage.opensSizeFilterOptions();
			homePage.chooseSizeFromFilterDirect(prop.getProperty("productSize").trim());
			homePage.openPriceFilterOptions();
			homePage.choosePriceFromFilterDirect(prop.getProperty("priceForFiltering").trim());
			homePage.openColorFilterOptions();
			homePage.chooseColorFromFilterDirect(prop.getProperty("productColor").trim());
						
			//validate the result
			Assert.assertTrue(homePage.isSearchProductInList(prop.getProperty("advFilterProdName").trim(), prop.getProperty("advFilterProdPrice").trim()),"Test failed! no correct product found..");
				
			logger.info("test method completed");	
			
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug("This are debug logs: {}",e);
			Assert.fail();
		}
		logger.info("== Finished TC_01_AdvFilteringFunc_ValidAdvancedProductFilteringFunctionalityTest ==");
	}
	
}
