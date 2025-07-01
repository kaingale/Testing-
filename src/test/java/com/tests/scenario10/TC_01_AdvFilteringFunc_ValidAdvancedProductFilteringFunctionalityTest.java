package com.tests.scenario10;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.tests.BaseTest;

public class TC_01_AdvFilteringFunc_ValidAdvancedProductFilteringFunctionalityTest extends BaseTest {

	@Test
	public void verifyAdvancedFiltering() {
		logger.info("== Starting TC_01_AdvFilteringFunc_ValidAdvancedProductFilteringFunctionalityTest ==");
		try {
			//homepage -> whats new section -> hoodies 
			logger.info("navigating to site and product category");
			homePage = new HomePage(driver);
			homePage.clickOnWhatNewLnk();
			homePage.gotoMensSectionHoodies();
			Thread.sleep(3000);
			homePage.opensSizeFilterOptions();
			Thread.sleep(2000);
			homePage.chooseSizeFromFilterDirect(prop.getProperty("productSize").trim());
			Thread.sleep(3000);
			homePage.openPriceFilterOptions();
			Thread.sleep(2000);
			homePage.choosePriceFromFilterDirect(prop.getProperty("priceForFiltering").trim());
			Thread.sleep(3000);
			homePage.openColorFilterOptions();
			Thread.sleep(2000);
			homePage.chooseColorFromFilterDirect(prop.getProperty("productColor").trim());
			Thread.sleep(3000);
						
			//validate the result
			Assert.assertTrue(homePage.isSearchProductInList(prop.getProperty("advFilterProdName").trim(), prop.getProperty("advFilterProdPrice").trim()),"Test failed! no correct product found..");
			Thread.sleep(10000);
				
			logger.info("test method completed");	
			
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug("This are debug logs: {}",e);
			Assert.fail();
		}
		logger.info("== Finished TC_01_AdvFilteringFunc_ValidAdvancedProductFilteringFunctionalityTest ==");
	}
	
}
