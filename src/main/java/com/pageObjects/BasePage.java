package com.pageObjects;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait webDriverWait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
//		PageFactory.initElements(driver,this);
		this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
	}

	
	
	// utility action methods
	protected void clickOnElement(WebElement ele) {
		ele.click();
	}

	protected void fillInputBox(WebElement ele, String inputText) {
		ele.clear();
		ele.sendKeys(inputText);
	}

	protected String getTextFromElement(WebElement ele) {
		return ele.getText();
	}

	// utility validation methods
	protected Boolean isWebElementVisible(WebElement ele) {
		return ele.isDisplayed();
	}

	
	
	// ALL WAIT METHODS
	// wait till element is visible - takes webelement
	protected WebElement waitForVisibility(WebElement ele) {
		return webDriverWait.until(ExpectedConditions.visibilityOf(ele));
	}

	// wait till element is clickable
	protected WebElement waitForClicable(WebElement ele) {
		return webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
	}


	// wait till element text is visible - webelement
	protected Boolean waitForTextToBePresent(WebElement ele, String givenText) {
		return webDriverWait.until(ExpectedConditions.textToBePresentInElement(ele, givenText));
	}

	
	
	
	// wait till element is visible - takes locator
	protected WebElement waitForVisibilityLocator(By locator) {
		return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//wait till element invisible
	protected boolean waitForInVisiblityOfLocator(By locator) {
		return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	//wait till element is present in the dom
	protected WebElement waitForClic(By locator) {
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	//wait till element is clickable -  locator
	protected WebElement waitForClickableLocator(By locator) {
		return webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// wait till element text is visible - locator
	protected Boolean waitForTextToBePresentLocator(By locator, String txt) {
		return webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(locator, txt));
	}

	
	
	// FLUENTWAIT method for any condition
	protected <T> T fluentWait(Function<WebDriver, T> condition, int timeoutSeconds, int pollingMillis) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofMillis(pollingMillis)).ignoring(NoSuchElementException.class);

		return wait.until(condition);
	}

	// Wait for element to be visible using FluentWait - webelement
	protected WebElement waitForElementVisibleFluent(WebElement element, int timeoutSeconds, int pollingMillis) {
		return fluentWait(drv -> {
			if (element.isDisplayed()) {
				return element;
			} else {
				return null;
			}
		}, timeoutSeconds, pollingMillis);
	}

	// Wait for text to be present using FluentWait - element
	protected boolean waitForTextToBePresentFluent(WebElement element, String text, int timeoutSeconds,
			int pollingMillis) {
		return fluentWait(drv -> element.getText().contains(text), timeoutSeconds, pollingMillis);
	}

	// fluentwait using - locator
	public boolean waitForTextToBePresentFluent(By locator, String text, int timeoutSeconds, int pollingMillis) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofMillis(pollingMillis)).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class);

		return wait.until(driver -> {
			WebElement element = driver.findElement(locator);
			return element.getText().contains(text);
		});
	}
	
	
	//java script executer
	public void scrollIntoViewElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	}
	
	public void jseClickElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", element);
	}
}
