package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 *  name email validation : .box-information p
 *  title validation : .page-title span
 */

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(css = ".box-information p")
	private WebElement myAccPageHeaderEle;
	
	@FindBy(css = ".page-title span")
	private WebElement nameEmailTextEle;
	
	@FindBy(xpath = "//div[text()='Thank you for registering with Main Website Store.']")
	private WebElement accCreateSuccessMsgEle;

	@FindBy(xpath = "//a[text()='My Orders']")
	private WebElement myOrderListOpt;
	
	@FindBy(xpath = "//a[@class='action change-password']/preceding-sibling::a")
	private WebElement editOpt;

	@FindBy(id = "firstname")
	private WebElement fnameInputBox;

	@FindBy(id = "lastname")
	private WebElement lnameInputBox;
	
	@FindBy(id = "change-email")
	private WebElement emailChangeChkBox;

	@FindBy(id = "change-password")
	private WebElement pwdChangeChkBox;
	
	@FindBy(id = "email")
	private WebElement emailInputBox;
	
	@FindBy(id = "current-password")
	private WebElement currentPwdInputBox;
	
	@FindBy(id = "password")
	private WebElement newPwdInputBox;
	
	@FindBy(id = "password-confirmation")
	private WebElement confirmPwdInputBox;
	
	@FindBy(css = ".save")
	private WebElement saveBtn;
	
	
	
	//action methods
	public boolean isMyAccountHeaderDisplayed() {
		return isWebElementVisible(myAccPageHeaderEle);
	}
	
	public Boolean isNameAndEmailCorrect(String givenNameAndEmail) {
		if(getTextFromElement(nameEmailTextEle).equalsIgnoreCase(givenNameAndEmail)) return true;
		else return false;
	}
	
	public boolean isAccCreatedSuccessMsgDisplayed() {
		waitForVisibility(accCreateSuccessMsgEle);
		return isWebElementVisible(accCreateSuccessMsgEle);
	}
	
	public void clickOnEditDetails() {
		clickOnElement(editOpt);
	}
	
	public void updateFirstname(String fname) {
		fillInputBox(fnameInputBox, fname);
	}
	
	public void updateLastname(String lname) {		
		fillInputBox(lnameInputBox, lname);
	}
	
	public void clickOnChangeEmailCheckBox() {
		clickOnElement(emailChangeChkBox);
	}

	public void clickOnChangePasswordCheckBox() {
		clickOnElement(pwdChangeChkBox);	
	}
	
	public void updateEmail(String email) {
		fillInputBox(emailInputBox, email);
	}
	
	public void enterCurrentPassword(String currentPwd) {
		fillInputBox(currentPwdInputBox, currentPwd);
	}
	
	public void enterNewPassword(String newPwd) {
		fillInputBox(newPwdInputBox, newPwd);
	}

	public void enterNewPwdInCfmPassword(String newPwd) {
		fillInputBox(confirmPwdInputBox, newPwd);
	}
	
	public void clickOnSaveUpdatesBtn() {
		clickOnElement(saveBtn);
	}
	
	public void clickOnMyOrdersLnk() {
		clickOnElement(myOrderListOpt);
	}
}
