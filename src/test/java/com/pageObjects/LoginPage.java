package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.ActionLogger;

public class LoginPage extends BasePage {
	
	ActionLogger actionLogger;
		
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage(WebDriver driver, ActionLogger actionLogger) {
		super(driver);
		this.actionLogger =  actionLogger;
	}
	
	
	//locators 
	@FindBy(css = "#email")
	private WebElement emailInputBox;
	
	@FindBy(xpath = "//fieldset[@class='fieldset login']//input[@id='pass']")
	private WebElement pwdInputBox;
	
	@FindBy(xpath = "//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")
	private WebElement signInBtn;

	@FindBy(xpath = "//div[contains(text(),'You saved the account information.')]")
	private WebElement accDetailsUpdateSuccessMsg;

//	@FindBy(css = "div[data-bind='html: $parent.prepareMessageForHtml(message.text)']") 
	@FindBy(xpath = "//div[contains(text(),'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.')]") 
	private WebElement signInIncorectErrorMsg;
	
	
	//action methods
	public void enterEmail(String email) {
		fillInputBox(emailInputBox, email);
		actionLogger.logStep("Entered email");
	}
	
	public void enterPassword(String pwd) {
		fillInputBox(pwdInputBox, pwd);
		actionLogger.logStep("Entered password");
	}
	
	public void clickOnSignInBtn() {
		waitForClicable(signInBtn);
		clickOnElement(signInBtn);
		
	}
	
	public boolean isSignInErrorMsgDisplayed() {
		waitForVisibility(signInIncorectErrorMsg);
		actionLogger.logStep("Error msg display checked");
		return isWebElementVisible(signInIncorectErrorMsg);
	}
	
	public boolean isAccUpdateSuccessMsgDisplayed() {
		waitForVisibility(accDetailsUpdateSuccessMsg);
		actionLogger.logStep("Updated success msg check");
		return isWebElementVisible(accDetailsUpdateSuccessMsg);
	}
}
