package com.utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tests.BaseTest;

public class ExtentReportManager implements ITestListener,ISuiteListener {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static String reportName;

 // Initialize ONCE per suite
    @Override
    public void onStart(ISuite suite) {
        if (extent == null) {
            String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            reportName = "Test-Report" + currentDateTimeStamp + ".html";
            sparkReporter = new ExtentSparkReporter(".\\test-reports\\" + reportName);

            sparkReporter.config().setDocumentTitle("WebAutomation Test Framework Reports");
            sparkReporter.config().setReportName("Web Ui Testing");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "Ecommerce WebApp");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Username", System.getProperty("user.name"));
        }
    }
    
    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
            String pathOfExtentReport = System.getProperty("user.dir") + "\\test-reports\\" + reportName;
            File extentReport = new File(pathOfExtentReport);

            try {
                Desktop.getDesktop().browse(extentReport.toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(
            result.getTestClass().getName() + " :: " + result.getMethod().getMethodName()
        );
        extentTest.assignCategory(result.getMethod().getGroups());
        result.setAttribute("extentTest", extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = (ExtentTest) result.getAttribute("extentTest");
        if (extentTest != null) {
            extentTest.log(Status.PASS, result.getName() + " got successfully executed..");
            if (result.getThrowable() != null) {
                extentTest.log(Status.INFO, result.getThrowable().getMessage());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = (ExtentTest) result.getAttribute("extentTest");
        if (extentTest != null) {
            extentTest.log(Status.FAIL, result.getName() + " got failed..");
            if (result.getThrowable() != null) {
                extentTest.log(Status.INFO, result.getThrowable().getMessage());
            }
            try {
                Object currentTest = result.getInstance();
                if (currentTest instanceof BaseTest) {
                    BaseTest baseTest = (BaseTest) currentTest;
                    String imgPath = baseTest.captureScreen(result.getName());
                    if (imgPath != null) {
                        extentTest.addScreenCaptureFromPath(imgPath);
                    }
                } else {
                    System.out.println("Test instance is not of type BaseTest, cannot capture screenshot.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = (ExtentTest) result.getAttribute("extentTest");
        if (extentTest != null) {
            extentTest.log(Status.SKIP, result.getName() + " got skipped..");
            if (result.getThrowable() != null) {
                extentTest.log(Status.INFO, result.getThrowable().getMessage());
            }
        }
    }
    
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}

}
