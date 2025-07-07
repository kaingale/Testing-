package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.ActionLogger;

/*
 *  name email validation : .box-information p
 *  title validation : .page-title span
 */

public class MyAccountPage extends BasePage {

	ActionLogger actionLogger;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	public MyAccountPage(WebDriver driver, ActionLogger actionLogger) {
		super(driver);
		this.actionLogger = actionLogger;
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
		actionLogger.logStep("My acc header display check");
		return isWebElementVisible(myAccPageHeaderEle);
	}
	
	public Boolean isNameAndEmailCorrect(String givenNameAndEmail) {
		if(getTextFromElement(nameEmailTextEle).equalsIgnoreCase(givenNameAndEmail)) return true;
		else return false;
	}
	
	public boolean isAccCreatedSuccessMsgDisplayed() {
		waitForVisibility(accCreateSuccessMsgEle);
		actionLogger.logStep("Acc creation success msg display checked");
		return isWebElementVisible(accCreateSuccessMsgEle);
	}
	
	public void clickOnEditDetails() {
		clickOnElement(editOpt);
		actionLogger.logStep("Clicked on edit details");
	}
	
	public void updateFirstname(String fname) {
		fillInputBox(fnameInputBox, fname);
		actionLogger.logStep("Entered firstname");
	}
	
	public void updateLastname(String lname) {		
		fillInputBox(lnameInputBox, lname);
		actionLogger.logStep("Entered lastname");
	}
	
	public void clickOnChangeEmailCheckBox() {
		clickOnElement(emailChangeChkBox);
	}

	public void clickOnChangePasswordCheckBox() {
		clickOnElement(pwdChangeChkBox);	
	}
	
	public void updateEmail(String email) {
		fillInputBox(emailInputBox, email);
		actionLogger.logStep("Entered email");
	}
	
	public void enterCurrentPassword(String currentPwd) {
		fillInputBox(currentPwdInputBox, currentPwd);
		actionLogger.logStep("Entered current password");
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
		waitForVisibility(myOrderListOpt);
		actionLogger.logStep("Clicked on my orderlist");
		clickOnElement(myOrderListOpt);
	}
}
