package com.tests;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.RegistrationPage;

public class BaseTest {
	public static WebDriver driver;
	public Properties prop;
	
	protected HomePage homePage;
	protected RegistrationPage registrationPage;
	protected MyAccountPage myAccountPage;
	protected LoginPage loginPage;
	protected Logger logger;
	
	Faker faker = new Faker();
	
	@BeforeClass
	public void setUp() {	
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
				String browser = prop.getProperty("browser");
				switch(browser.trim().toLowerCase()) {
					case "chrome": driver = new ChromeDriver();break;
					case "edge": driver = new EdgeDriver();break;
					case "firefox": driver = new FirefoxDriver();break;
					default : System.out.println("Invalid browser..");return;    //change with logger
				}       
				
				//logger setup
				logger = LogManager.getLogger(BaseTest.class);
				
				//delete cookies, add implicit wait, maximize the window
				driver.manage().deleteAllCookies();
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
	
	public String pwdGenerator() {
		pwd = faker.internet().password();
		System.out.println("Password: "+pwd);
		return pwd;
	}
	
	//refresh method
	protected void pageRefresh() {
		driver.navigate().refresh();
	}
}
