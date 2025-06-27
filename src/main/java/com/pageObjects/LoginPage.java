package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
		
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	//locators 
	@FindBy(css = "#email")
	WebElement emailInputBox;
	@FindBy(xpath = "//fieldset[@class='fieldset login']//input[@id='pass']")
	WebElement pwdInputBox;
	@FindBy(xpath = "//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
	WebElement signInBtn;
	@FindBy(xpath = "//div[@role='alert']/div/div")
	WebElement signInIncorectErrorMsg;
	
	
	//action methods
	public void enterEmail(String email) {
		fillInputBox(emailInputBox, email);
	}
	
	public void enterPassword(String pwd) {
		fillInputBox(pwdInputBox, pwd);
	}
	
	public void clickOnSignInBtn() {
		clickOnElement(signInBtn);
	}
	
	
	public Boolean isSignInErrorMsgDisplayed() {
		waitForVisibility(signInIncorectErrorMsg);
		return isWebElementVisible(signInIncorectErrorMsg);
	}
}
