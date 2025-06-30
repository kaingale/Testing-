package com.pageObjects;

import java.util.List;
import java.util.Timer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath="(//a[contains(text(),'Create an Account')])[1]")
	private WebElement createAccountLnk;
	
	@FindBy(xpath="//div[@class='panel header']//a[contains(text(),'Sign In')]")
	private WebElement signInLnk;
	
	@FindBy(css = "div.header li.greet span")
	private WebElement welcomeUserTxtEle;
	
	@FindBy(css = "div.header li.customer-welcome span.customer-name")
	private WebElement drpdownArrowEle;
	
	@FindBy(css = "div.header div.customer-menu li.authorization-link")
	private WebElement signOutSelectMenuOpt;
	
	@FindBy(css = "#search")
	private WebElement searchBarBox;
	
	@FindBy(css = "button[title='Search']")
	private WebElement searchBtn;
	
	@FindBy(css = "ol.products li")
	private List<WebElement> searchProductList;

	@FindBy(css = "ul.header a#idcrzwKQlH")
	private List<WebElement> myAccDrpDwnOpt;
	

	
	
	//action methods
	public void clickOnCreateAccLnk() {
		clickOnElement(createAccountLnk);
	}
	
	public void clickOnSignInLnk() {
		clickOnElement(signInLnk);
	}
	
	public String getWelcomeUserText() {
		String welcomeMsg = getTextFromElement(welcomeUserTxtEle);
		System.out.println(welcomeMsg);
		return welcomeMsg;
	}
	
	public Boolean isWelcomeMsgCorrect1(String userName) throws InterruptedException {
//		waitForTextToBePresentFluent(welcomeUserTxtEle, userName, 15, 500);
//		Thread.sleep(30000);
		Boolean isTxtPresent = waitForTextToBePresentFluent(
			    By.cssSelector("div.header li.greet span"),
			    userName,
			    15,
			    500
			);
		if(isTxtPresent) {
			return true;
		}
		return false;
	}
	
	public Boolean isWelcomeMsgCorrect(String userName) {
		return waitForVisibility(welcomeUserTxtEle).getText().contains(userName);
	}
		
	public void clickOnDrpDwnArrowNextToWelecomMsg() {
//		WebElement drpdown1 = waitForClickableLocator(By.cssSelector("div.header li.customer-welcome span.customer-name"));
		waitForVisibility(drpdownArrowEle);
		clickOnElement(drpdownArrowEle);
	}

	public Boolean isSignOutOptVisible() {
//		WebElement signoutOptEle = waitForVisibilityLocator(By.cssSelector("div.header div.customer-menu li.authorization-link"));
		waitForVisibility(signOutSelectMenuOpt);
		return isWebElementVisible(signOutSelectMenuOpt);
	}
	
	public void clickOnSignOut() {
//		WebElement signoutOptEle = waitForVisibilityLocator(By.cssSelector("div.header div.customer-menu li.authorization-link"));
		clickOnElement(signOutSelectMenuOpt);
	}
	
	public void enterProductInSearchBox(String product) {
//		waitForClicable(searchBarBox);
		fillInputBox(searchBarBox, product);
	}

	public void clickSearchBtn() {
		clickOnElement(searchBtn);
	}
	
	public Boolean isSearchProductInList(String name, String price) {
		for(WebElement product: searchProductList) {
			scrollIntoViewElement(product);
			String productName =  product.findElement(By.cssSelector("a.product-item-link")).getText();
			String productPrice =  product.findElement(By.cssSelector("span.price")).getText();
			
			if(productName.contains(name) && productPrice.contains(price)) {
				return true;
			}
		}
		return false;
	}

	public void searchProductAndGotoProductDetailsPage(String name) {
		for(WebElement product: searchProductList) {
			scrollIntoViewElement(product);
			WebElement productName =  product.findElement(By.cssSelector("a.product-item-link"));		
			if(productName.getText().contains(name)) {
				clickOnElement(productName);
				return;
			}
		}
	}

}
