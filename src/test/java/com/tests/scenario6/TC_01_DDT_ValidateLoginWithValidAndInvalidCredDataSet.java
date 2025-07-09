package com.tests.scenario6;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.tests.BaseTest;
import com.tests.WebDriverOptionsSetup;
import com.utilities.ActionLogger;

public class TC_01_DDT_ValidateLoginWithValidAndInvalidCredDataSet extends BaseTest {
	
	@Parameters({"os","browser"})
    @BeforeMethod
    public void setUpMethod(@Optional("Windows") String os, @Optional("chrome") String browser) {
        // Repeat the setup logic from BaseTest, but at method level
        try {
            FileReader propFile = new FileReader("./src//test//resources//config.properties");
            prop = new Properties();
            prop.load(propFile);
        } catch(IOException ioe) {
            System.out.println("Failed to load config.properties file.."+ioe.getMessage());
        }

        if(driver==null) {
            WebDriverOptionsSetup optionSetup = new WebDriverOptionsSetup();
            switch(browser.trim().toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(optionSetup.initChromeOptions());
                    break;
                case "edge":
                    driver = new EdgeDriver(optionSetup.initEdgeOptions());
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default : System.out.println("Invalid browser..");return;
            }
            logger = LogManager.getLogger(BaseTest.class);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.get(prop.getProperty("appUrl"));
        }
    }

    @AfterMethod
    public void tearDownMethod() {
        if(driver!= null) {
            driver.quit();
            driver = null; // Important to set to null so next method gets a new instance
        }
    }
		
	@Test(dataProvider = "credentialData")
	public void verifyLoginDDT(String status, String email, String password) {
		logger.info("== Starting TC_01_DDT_ValidateLoginWithValidAndInvalidCredDataSet ==");
		try {
			ActionLogger actionLogger = actionLoggerCreationHelper();
			//navigate to landing page and click on sign in link
			logger.info("navigating to login page");
			HomePage homePage = new HomePage(driver, actionLogger);
			homePage.clickOnSignInLnk();
			
			//on login page fill all the details like email, pwd and click on sign in btn
			logger.info("entering all the sign in details and click on sign in");
			LoginPage loginPage = new LoginPage(driver, actionLogger);
			loginPage.enterEmail(email);
			loginPage.enterPassword(password);
			loginPage.clickOnSignInBtn();

		
			//validate welcome user text msg and logout
			logger.info("validating DDT datasets");
			if(status.equalsIgnoreCase("Valid")) {
				logger.info("validating DDT email: {}", email);
				homePage.clickOnDrpDwnArrowNextToWelecomMsg();
				Assert.assertTrue(homePage.isSignOutOptVisible(),"Test failed! no sign out opt visible..");
				homePage.clickOnSignOut();
				Thread.sleep(6000);
			}
			if (status.equalsIgnoreCase("Invalid")) {
				logger.info("validating DDT email: {}", email);
				Assert.assertTrue(loginPage.isSignInErrorMsgDisplayed(),"Test failed! no error msg displayed..");
				Thread.sleep(6000);
			}
			
			logger.info("DDT complete for: {}", email);
			
		}catch(AssertionError ae) {
			logger.error("Test failed due to an exception in catch block: {}", ae.getMessage());
			logger.debug(ae);
			throw ae;
		}catch(Exception e) {
			logger.error("Test failed due to an exception in catch block: {}", e.getMessage());
			logger.debug(e);
			throw new RuntimeException("Test execution failed!", e);
		}
		
		logger.info("== Finished TC_01_DDT_ValidateLoginWithValidAndInvalidCredDataSet ==");
	}
	
	@DataProvider(name = "credentialData")
	public Object[][] getCreds(){
		return new Object[][] {
		{"Valid", "abc3@myorg.com", "Abc@1234"},
		{"Invalid", "mno@myorg.com", "Abcxyz@123"},
		{"Invalid", "abc1@myorg.com", "Abcasfds@133eq"},
		{"Valid", "roni_cost@example.com", "roni_cost3@example.com"},
		{"Invalid", "mno@myorg.com", "Abc@1234"},
		};
	}
}
