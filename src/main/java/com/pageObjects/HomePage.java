package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.RetryUtil;

/*
 * css locators =>
 *  create account lnk : #idJ6O6YF6i 
 */

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="(//a[contains(text(),'Create an Account')])[1]")
	WebElement createAccountLnk;	
	@FindBy(xpath="//div[@class='panel header']//a[contains(text(),'Sign In')]")
	WebElement signInLnk;
	@FindBy(css = "div.header li.greet span")
	WebElement welcomeUserTxtEle;
	@FindBy(css = "div.header li.customer-welcome span.customer-name")
	WebElement drpdownArrowEle;
	@FindBy(css = "div.header div.customer-menu li.authorization-link")
	WebElement signOutSelectMenuOpt;

	
	
	//action methods
	public void clickOnCreateAccLnk() {
		clickOnElement(createAccountLnk);
	}
	
	public void clickOnSignInLnk() {
		clickOnElement(signInLnk);
	}
	
	public String getWelcomeUserText() {
		String welcomeMsg = getTextFromElement(welcomeUserTxtEle);
		System.out.println(welcomeMsg);
		return welcomeMsg;
	}
	
	public boolean isWelcomeMessageCorrect(String userName) {
		WebElement msgTxtEle = waitForVisibilityLocator(By.cssSelector("div.header li.greet span"));
        try {
            String welcomeMsg = RetryUtil.retry(() -> { 
                return msgTxtEle.getText();
            }, 3, 2000); 								// 3 attempts, 2 sec apart

            return welcomeMsg.contains(userName);
        } catch (Exception e) {
            return false;
        }
    }
	
	public Boolean isWelcomeMsgCorrect1(String userName) {
//		waitForTextToBePresentFluent(welcomeUserTxtEle, userName, 15, 500);
		Boolean isTxtPresent = waitForTextToBePresentFluent(
			    By.cssSelector("div.header li.greet span"),
			    userName,
			    15,
			    500
			);
		if(isTxtPresent) {
			return true;
		}
		return false;
	}
		
	public void clickOnDrpDwnArrowNextToWelecomMsg() {
//		WebElement drpdown1 = waitForClickableLocator(By.cssSelector("div.header li.customer-welcome span.customer-name"));
		waitForVisibility(drpdownArrowEle);
		clickOnElement(drpdownArrowEle);
	}

	public Boolean isSignOutOptVisible() {
//		WebElement signoutOptEle = waitForVisibilityLocator(By.cssSelector("div.header div.customer-menu li.authorization-link"));
		waitForVisibility(signOutSelectMenuOpt);
		return isWebElementVisible(signOutSelectMenuOpt);
	}
	
	public void clickOnSignOut() {
//		WebElement signoutOptEle = waitForVisibilityLocator(By.cssSelector("div.header div.customer-menu li.authorization-link"));
		clickOnElement(signOutSelectMenuOpt);
	}

}
