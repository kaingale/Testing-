package com.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAccountPage;
import com.pageObjects.RegistrationPage;

public class BaseTest2 {

 // ThreadLocal for WebDriver and other per-thread objects
 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
 private static ThreadLocal<Logger> logger = new ThreadLocal<>();
 private static ThreadLocal<Faker> faker = ThreadLocal.withInitial(Faker::new);

 protected Properties prop;

 // Page objects as ThreadLocal
 protected ThreadLocal<HomePage> homePage = new ThreadLocal<>();
 protected ThreadLocal<RegistrationPage> registrationPage = new ThreadLocal<>();
 protected ThreadLocal<MyAccountPage> myAccountPage = new ThreadLocal<>();
 protected ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();

 @Parameters({"os", "browser"})
 @BeforeClass
 public void setUp(@Optional("Windows") String os, @Optional("chrome") String browser) {

     // Config file setup
     try {
         FileReader propFile = new FileReader("./src//test//resources//config.properties");
         prop = new Properties();
         prop.load(propFile);
     } catch (IOException ioe) {
         getLogger().error("Failed to load config.properties file: " + ioe.getMessage());
     }

     // Driver instance per thread
     WebDriverOptionsSetup optionSetup = new WebDriverOptionsSetup();
     WebDriver webDriver = null;

     switch (browser.trim().toLowerCase()) {
         case "chrome":
             webDriver = new ChromeDriver(optionSetup.initChromeOptions());
             break;
         case "edge":
             webDriver = new EdgeDriver(optionSetup.initEdgeOptions());
             break;
         case "firefox":
             webDriver = new FirefoxDriver(optionSetup.initFirefoxOptions());
             break;
         default:
             getLogger().error("Invalid browser specified: " + browser);
             throw new RuntimeException("Invalid browser specified: " + browser);
     }
     driver.set(webDriver);

     // Logger setup per thread
     logger.set(LogManager.getLogger(BaseTest.class));

     	
//      homePage.set(new HomePage(getDriver()));
//      registrationPage.set(new RegistrationPage(getDriver()));
//      myAccountPage.set(new MyAccountPage(getDriver()));
//      loginPage.set(new LoginPage(getDriver()));

     // Browser setup
     getDriver().manage().deleteAllCookies();
     getDriver().manage().window().maximize();
     getDriver().get(prop.getProperty("appUrl"));
 }

 @AfterClass
 public void tearDown() {
     if (getDriver() != null) {
         getDriver().quit();
         driver.remove();
         logger.remove();
         faker.remove();
         homePage.remove();
         registrationPage.remove();
         myAccountPage.remove();
         loginPage.remove();
     }
 }

 // Utility getters for thread-local objects
 protected WebDriver getDriver() {
     return driver.get();
 }

 protected Logger getLogger() {
     return logger.get();
 }

 protected Faker getFaker() {
     return faker.get();
 }

 // Faker utility methods (thread-safe)
 public String fnameGenerator() {
     String fname = getFaker().name().firstName();
     getLogger().info("Firstname: " + fname);
     return fname;
 }

 public String lnameGenerator() {
     String lname = getFaker().name().lastName();
     getLogger().info("Lastname: " + lname);
     return lname;
 }

 public String emailGenerator() {
     String email = fnameGenerator() + lnameGenerator() + "@myorg.com";
     getLogger().info("Email: " + email);
     return email;
 }

 public String emailGenerator1() {
     String email1 = getFaker().internet().emailAddress();
     getLogger().info("Email1: " + email1);
     return email1;
 }

 public String pwdGenerator() {
     String pwd = getFaker().internet().password();
     getLogger().info("Password: " + pwd);
     return pwd;
 }

 // Refresh method
 protected void pageRefresh() {
     getDriver().navigate().refresh();
 }

 // Screenshot setup (thread-safe)
 public String captureScreen(String tname) {
     if (getDriver() == null) {
         getLogger().error("Driver is null. Cannot capture screenshot.");
         return null;
     }
     try {
         String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
         TakesScreenshot takeScreenshot = (TakesScreenshot) getDriver();
         File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
         String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname + "_" + currentDateTimeStamp + ".png";
         File targetFile = new File(targetFilePath);
         sourceFile.renameTo(targetFile);
         return targetFilePath;
     } catch (Exception e) {
         getLogger().error("Failed to capture screenshot: " + e.getMessage());
         return null;
     }
 }
}

