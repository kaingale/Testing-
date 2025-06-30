package com.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegistrationPage extends BasePage {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(css = "#firstname")
	private WebElement fnameInputBox;
	
	@FindBy(css = "#lastname")
	private WebElement lnameInputBox;
	
	@FindBy(css = "#email_address")
	private WebElement emailInputBox;
	
	@FindBy(css = "#password")
	private WebElement pwdInputBox;
	
	@FindBy(css = "#password-confirmation")
	private WebElement confirmPwdInputBox;
	
	@FindBy(xpath = "(//button[@id='send2'])[1]")
	private WebElement createAccBtn;

	@FindBy(xpath = "//div[@role='alert']/div/div")
	private WebElement emailAlreadyExistErrorMsg;
	
	
	//action methods
	public void enterFname(String fname) {
		waitForClicable(fnameInputBox);
		fillInputBox(fnameInputBox, fname);
	}
	
	public void enterLname(String lname) {
		waitForClicable(lnameInputBox);
		fillInputBox(lnameInputBox, lname);
	}
	
	public void enterEmail(String email) {
		waitForClicable(emailInputBox);
		fillInputBox(emailInputBox, email);
	}
	
	public void enterPassword(String pwd) {
		scrollIntoViewElement(pwdInputBox);
		waitForClicable(pwdInputBox);
		fillInputBox(pwdInputBox, pwd);
	}
	
	public void reEnterPwd(String pwd) {
		waitForClicable(confirmPwdInputBox);
		fillInputBox(confirmPwdInputBox, pwd);
	}
	
	public void clickOnCreateAccBtn() {
		waitForClicable(createAccBtn);
		clickOnElement(createAccBtn);
	}
	
	public Boolean isEmailExitErrorMsgDisplayed() {
		return isWebElementVisible(waitForVisibility(emailAlreadyExistErrorMsg));
	}
}
