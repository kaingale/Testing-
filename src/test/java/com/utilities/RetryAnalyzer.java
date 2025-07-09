package com.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        Throwable cause = result.getThrowable();

        System.out.println("Retry attempt #" + (retryCount + 1) + " for test: " + result.getName());

        if (cause != null) {
            if (isRetryableException(cause)) {
                if (retryCount < maxRetryCount) {
                    retryCount++;
                    System.out.println("Retrying test '" + result.getName() + "' due to exception: " + cause.getClass().getSimpleName() + " - " + cause.getMessage());
                    return true;
                }
            } else {
                System.out.println("Not retrying test '" + result.getName() + "' because exception is not retryable: " + cause.getClass().getSimpleName());
                return false;
            }
        } else {
            
            if (retryCount < maxRetryCount) {
                retryCount++;
                System.out.println("Retrying test '" + result.getName() + "' (no exception detected)");
                return true;
            }
        }
        return false;
    }

    private boolean isRetryableException(Throwable cause) {
        String message = cause.getMessage() != null ? cause.getMessage().toLowerCase() : "";

        if (cause instanceof TimeoutException
                || cause instanceof WebDriverException
                || cause instanceof StaleElementReferenceException 
                || cause instanceof NoSuchElementException
                || message.contains("stale element reference")
                || message.contains("timeout")
                || message.contains("connection reset")
                || message.contains("connection refused")
                || message.contains("network error")
                || message.contains("unable to connect")
                || message.contains("read timed out")
                || message.contains("failed to establish a new connection")
                || message.contains("element not interactable")
                || message.contains("element has zero size")) {
            return true;
        }
        return false;
    }
}
