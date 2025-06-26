package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * css locators =>
 *  create account lnk : #idJ6O6YF6i 
 */

public class LandingPage extends BasePage {
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="(//a[contains(text(),'Create an Account')])[1]")
	WebElement createAccountLnk;
	
	
	//action methods
	public void clickOnCreateAccLnk() {
		clickOnElement(createAccountLnk);
	}
}
