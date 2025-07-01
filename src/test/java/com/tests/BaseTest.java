package com.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;
import com.pageObjects.CartPage;
import com.pageObjects.CheckoutPage;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.OrderHistoryPage;
import com.pageObjects.ProductDetailsPage;
import com.pageObjects.RegistrationPage;

public class BaseTest {
	protected WebDriver driver;
	protected Properties prop;
	
	protected HomePage homePage;
	protected RegistrationPage registrationPage;
	protected MyAccountPage myAccountPage;
	protected LoginPage loginPage;
	protected ProductDetailsPage productPage;
	protected CartPage cartPage;
	protected CheckoutPage checkoutPage;
	protected OrderHistoryPage orderHistoryPage;
	protected Logger logger;
	
	Faker faker = new Faker();
	
	@Parameters({"os","browser"})
	@BeforeClass
	public void setUp(@Optional("Windows") String os, @Optional("chrome") String browser) {	
		
		//config file setup
		try {
			FileReader propFile = new FileReader("./src//test//resources//config.properties");
			prop = new Properties();
			prop.load(propFile);
			
		}catch(IOException ioe) {
			//change this println with logger exception msg later
			System.out.println("Failed to load config.properties file.."+ioe.getMessage());
		}

		//driver instance check
		if(driver==null) {
				WebDriverOptionsSetup optionSetup = new WebDriverOptionsSetup();
				
				switch(browser.trim().toLowerCase()) {
					case "chrome":
//						WebDriverManager.chromedriver().setup();
						driver = new ChromeDriver(optionSetup.initChromeOptions());
						break;
					case "edge":
//						WebDriverManager.edgedriver().setup();
						driver = new EdgeDriver(optionSetup.initEdgeOptions());
						break;
					case "firefox":
//						WebDriverManager.firefoxdriver().setup();
						driver = new FirefoxDriver();
						break;
					default : System.out.println("Invalid browser..");return;    //change with logger
				}       
				
				//logger setup
				logger = LogManager.getLogger(BaseTest.class);
				
				//delete cookies, add implicit wait, maximize the window
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				
				//get app url form properties file and goto app
				driver.get(prop.getProperty("appUrl"));
		}
	}
	
	@AfterClass
	public void tearDown() {
		if(driver!= null) {
		driver.quit();
		}
	}
	
//	@AfterClass
	public void tearDownQuitOnlyCurrentTab() {
		if(driver!=null) {
		driver.close();
		}
	}
	
	//faker lib methods
	String fname;
	String lname;
	String email;
	String email1;
	String pwd;
	
	public String fnameGenerator() {
		fname = faker.name().firstName();
		System.out.println("Firstname: "+fname);
		return fname;
	}
	
	public String lnameGenerator() {
		lname = faker.name().lastName();
		System.out.println("Lastname: "+lname);
		return lname;
	}
	
	public String emailGenerator() {
		email = fname + lname +"@myorg.com";
		System.out.println("Email: "+email);
		return email;
	}
	
	public String emailGenerator1() {
		email1 = faker.internet().emailAddress();
		return email1;
	}
	
	public String pwdGenerator() {
		pwd = faker.internet().password();
		System.out.println("Password: "+pwd);
		return pwd;
	}
	
	//refresh method
	protected void pageRefresh() {
		driver.navigate().refresh();
	}
	
	
	//screenshot setup
	public String captureScreen(String tname) {
		if (driver == null) {
	        System.out.println("Driver is null. Cannot capture screenshot.");
	        return null;
	    }
	    try {
	        String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	        File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
	        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + currentDateTimeStamp + ".png";
	        File targetFile = new File(targetFilePath);
	        sourceFile.renameTo(targetFile);
	        return targetFilePath;
	    } catch (Exception e) {
	        System.out.println("Failed to capture screenshot: " + e.getMessage());
	        return null;
	    }
	}
}
