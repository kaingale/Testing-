package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
		
	public LoginPage(WebDriver driver) {
		super(driver);
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

	@FindBy(css = "div[data-bind='html: $parent.prepareMessageForHtml(message.text)']") 
	private WebElement signInIncorectErrorMsg;
	
	
	//action methods
	public void enterEmail(String email) {
		fillInputBox(emailInputBox, email);
	}
	
	public void enterPassword(String pwd) {
		fillInputBox(pwdInputBox, pwd);
	}
	
	public void clickOnSignInBtn() {
		waitForClicable(signInBtn);
		clickOnElement(signInBtn);
	}
	
	public boolean isSignInErrorMsgDisplayed() {
		waitForVisibility(signInIncorectErrorMsg);
		return isWebElementVisible(signInIncorectErrorMsg);
	}
	
	public boolean isAccUpdateSuccessMsgDisplayed() {
		waitForVisibility(accDetailsUpdateSuccessMsg);
		return isWebElementVisible(accDetailsUpdateSuccessMsg);
	}
}
