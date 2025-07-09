package com.utilities;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionLogger {

    private WebDriver driver;
    private ExtentTest extentTest;

    public ActionLogger(WebDriver driver, ExtentTest extentTest) {
        this.driver = driver;
        this.extentTest = extentTest;
    }

    public void logStep(String message) {
        try {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
            String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
            new File(screenshotDir).mkdirs();
            String screenshotPath = screenshotDir + message.replaceAll("\\s+", "_") + "_" + timestamp + ".png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            src.renameTo(dest);

            extentTest.info(message).addScreenCaptureFromPath(screenshotPath, message);

        } catch (Exception e) {
            extentTest.warning("Failed to capture screenshot: " + e.getMessage());
        }
    }
}

