package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.ActionLogger;


public class RegistrationPage extends BasePage {
	
	ActionLogger actionLogger;

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	public RegistrationPage(WebDriver driver, ActionLogger actionLogger) {
		super(driver);
		this.actionLogger = actionLogger;
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

	@FindBy(xpath = "//div[contains(text(),'There is already an account with this email address. If you are sure that it is your email address, ')]")
	private WebElement emailAlreadyExistErrorMsg;
	
	
	//action methods
	public void enterFname(String fname) {
		waitForClicable(fnameInputBox);
		fillInputBox(fnameInputBox, fname);
		actionLogger.logStep("Entered firstname");
	}
	
	public void enterLname(String lname) {
		waitForClicable(lnameInputBox);
		fillInputBox(lnameInputBox, lname);
		actionLogger.logStep("Entered lastname");
	}
	
	public void enterEmail(String email) {
		waitForClicable(emailInputBox);
		fillInputBox(emailInputBox, email);
		actionLogger.logStep("Entered email");
	}
	
	public void enterPassword(String pwd) {
		scrollIntoViewElement(pwdInputBox);
		waitForClicable(pwdInputBox);
		fillInputBox(pwdInputBox, pwd);
		actionLogger.logStep("Entered password");
	}
	
	public void reEnterPwd(String pwd) {
		waitForClicable(confirmPwdInputBox);
		fillInputBox(confirmPwdInputBox, pwd);
		actionLogger.logStep("Re-entered password");
	}
	
	public void clickOnCreateAccBtn() {
		waitForClicable(createAccBtn);
		clickOnElement(createAccBtn);
	}
	
	public Boolean isEmailExitErrorMsgDisplayed() {
		waitForVisibility(emailAlreadyExistErrorMsg);
		actionLogger.logStep("Email already exists err msg display check");
		return isWebElementVisible(emailAlreadyExistErrorMsg);
	}
}
