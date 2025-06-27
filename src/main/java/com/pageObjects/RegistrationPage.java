package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * fname  inputbox : #firstname
 *  lname inptbox : #lastname
 *  email inpbox : #email_address
 *  pwd inpbox : #password
 *  cfm pwd inpbox : #password-confirmation
 *  creataccBtn : #send2
 */

public class RegistrationPage extends BasePage {

	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(css = "#firstname")
	WebElement fnameInputBox;
	@FindBy(css = "#lastname")
	WebElement lnameInputBox;
	@FindBy(css = "#email_address")
	WebElement emailInputBox;
	@FindBy(css = "#password")
	WebElement pwdInputBox;
	@FindBy(css = "#password-confirmation")
	WebElement confirmPwdInputBox;
	@FindBy(xpath = "(//button[@id='send2'])[1]")
	WebElement createAccBtn;
	@FindBy(xpath = "//div[@role='alert']/div/div")
	WebElement emailAlreadyExistErrorMsg;
	
	
	//action methods
	public void enterFname(String fname) {
		fillInputBox(fnameInputBox, fname);
	}
	
	public void enterLname(String lname) {
		fillInputBox(lnameInputBox, lname);
	}
	
	public void enterEmail(String email) {
		fillInputBox(emailInputBox, email);
	}
	
	public void enterPassword(String pwd) {
		fillInputBox(pwdInputBox, pwd);
	}
	
	public void reEnterPwd(String pwd) {
		fillInputBox(confirmPwdInputBox, pwd);
	}
	
	public void clickOnCreateAccBtn() {
		clickOnElement(createAccBtn);
	}
	
	public Boolean isEmailExitErrorMsgDisplayed() {
		waitForVisibility(confirmPwdInputBox);
		return isWebElementVisible(emailAlreadyExistErrorMsg);
	}
}
