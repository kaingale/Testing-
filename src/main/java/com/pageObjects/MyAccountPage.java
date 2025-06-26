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
	WebElement myAccPageHeaderEle;
	@FindBy(css = ".page-title span")
	WebElement nameEmailTextEle;
	@FindBy(xpath = "//div[text()='Thank you for registering with Main Website Store.']")
	WebElement accCreateSuccessMsgEle;
	
	
	//action methods
	public boolean isMyAccountHeaderDisplayed() {
		return isWebElementVisible(myAccPageHeaderEle);
	}
	
	public Boolean isNameAndEmailCorrect(String givenNameAndEmail) {
		if(getTextFromElement(nameEmailTextEle).equalsIgnoreCase(givenNameAndEmail)) return true;
		else return false;
	}
	
	public boolean isAccCreatedSuccessMsgDisplayed() {
		return isWebElementVisible(accCreateSuccessMsgEle);
	}
}
