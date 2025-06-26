package com.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriverWait webDriverWait;
	
	public BasePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	//utility action methods
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
	
	//utility validation methods
	protected Boolean isWebElementVisible(WebElement ele) {
		return ele.isDisplayed();
	}
	
	//utility wait methods
	//wait till element is visible
	protected WebElement waitForVisibility(WebElement ele) {
		return webDriverWait.until(ExpectedConditions.visibilityOf(ele));
	}

	//wait till element is clickable
	protected WebElement waitForClicable(WebElement ele) {
		return webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	//wait till element text is visible
	protected Boolean waitForTextToBePresent(WebElement ele, String givenText) {
		return webDriverWait.until(ExpectedConditions.textToBePresentInElement(ele, givenText));
	}
	
}
