package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

		public ProductDetailsPage(WebDriver driver) {
			super(driver);
		}
		
	
		
	@FindBy(css = "div.swatch-attribute-options[aria-labelledby='option-label-size-144'] div")
	private List<WebElement> sizeList; 

	@FindBy(css = "div.swatch-attribute-options[aria-labelledby='option-label-color-93'] div")
	private List<WebElement> colorList; 
	
	@FindBy(css = "#qty")
	private WebElement quantityInputBox;

	@FindBy(id = "product-addtocart-button")
	private WebElement addToCartBtn;

	@FindBy(css = "a.showcart")
	private WebElement showcartLnk;

	@FindBy(css = "#top-cart-btn-checkout")
	private WebElement productCheckoutBtnEle;

	@FindBy(css = "div[data-bind='html: $parent.prepareMessageForHtml(message.text)']")
	private WebElement addCartSuccessMsg;

	@FindBy(css = "a[title='Remove item']")
	private WebElement clearCartBeforeUse;

	@FindBy(css = ".items-total")
	private List<WebElement> isTheirItemsInCartCheckEle;

	@FindBy(id = "option-label-color-93")
	private WebElement colorTitleEle;

	@FindBy(xpath = "//span[text()='View and Edit Cart']")
	private WebElement viewcartLnk;
	
	@FindBy(css = ".subtitle")
	private WebElement noProductInCartMsg;
	
	
	
	public void chooseSizeForProduct(String givenSize) {
		scrollIntoViewElement(colorTitleEle);
		for(WebElement sizeEle : sizeList) {
			if(sizeEle.getText().equalsIgnoreCase(givenSize)) {
				sizeEle.click();
				break;
			}
		}
	}

	public void chooseColorForProduct(String givenColor) {
		for(WebElement colorEle : colorList) {
			scrollIntoViewElement(colorEle);
			if(colorEle.getAttribute("aria-label").contains(givenColor)) {
				colorEle.click();
				break;
			}
		}
	}
	
	public void enterQuantity(String quantity) {
		fillInputBox(quantityInputBox, quantity);
	}
	
	public void clickAddToCart() {
		waitForClicable(addToCartBtn);
		clickOnElement(addToCartBtn);
	}
	
	public void openShowCartPopup() {
		scrollIntoViewElement(showcartLnk);
		clickOnElement(showcartLnk);
	}
	
	public void clearCartBeforeTest() {
		openShowCartPopup();
		if(!noProductInCartMsg.isDisplayed()) {
			if(!isTheirItemsInCartCheckEle.isEmpty()) {
			waitForClicable(clearCartBeforeUse);
			clickOnElement(clearCartBeforeUse);
			}
		}
	}
	
	public void gotoCartPage() {
		waitForClicable(viewcartLnk);
		clickOnElement(viewcartLnk);
	}
	
	public Boolean isAddToCartSuccessMsgDisplayed() {
		return waitForVisibility(addCartSuccessMsg).isDisplayed();
	}
	
	public void clickOnPopUpCheckoutBtn() {
		waitForClicable(productCheckoutBtnEle);
		clickOnElement(productCheckoutBtnEle);
	}
}
