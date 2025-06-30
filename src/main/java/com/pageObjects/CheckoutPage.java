package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{
		
		public CheckoutPage(WebDriver driver) {
			super(driver);
		}
		
	
		
	@FindBy(css = "table.table-checkout-shipping-method tbody tr")
	private List<WebElement> shippingPriceList;
	
	@FindBy(css = "button[data-role='opc-continue']")
	private WebElement nextBtn;

	@FindBy(css = "div.details-qty span.value")
	private WebElement orderQuantityInSummary;
	
	@FindBy(css = "div.title")
	private WebElement summaryDrpDwnArrow;

	@FindBy(xpath = "//span[text()='View Details']")
	private WebElement summaryViewDetailsDrpDwn;
	
	@FindBy(xpath = "(//dd[@class='values'])[1]")
	private WebElement sizeDetailInSummary;

	@FindBy(xpath = "(//dd[@class='values'])[2]")
	private WebElement colorDetailInSummary;
	
	@FindBy(css = ".grand span.price")
	private WebElement finalPriceAfterShipping;

	@FindBy(css = "button[title='Place Order']")
	private WebElement placeOrderBtn;
	
	@FindBy(css = "h1.page-title span")
	private WebElement orderPlaceSuccessMsg;

	@FindBy(css = "div.checkout-success strong")
	private WebElement orderNumberGeneratedEle;

	@FindBy(className = "action primary continue")
	private WebElement continueShoppingBtnEle;
	
	
	
	public void chooseCorrectShippingPrice(String givenShippingWay) {
//		System.out.println(shippingPriceList.size());
		for (WebElement row : shippingPriceList) {
			waitForVisibility(row); 
			String shippingPrice = row.findElement(By.cssSelector(" td[id='label_method_flatrate_flatrate']")).getText(); 
			WebElement radioBtn = row.findElement(By.cssSelector(" input"));
			if (shippingPrice.contains(givenShippingWay) && !radioBtn.isSelected()) {
				radioBtn.click();
				break;
			}
		}
	}
	
	public void clickOnNextBtn() {
		scrollIntoViewElement(nextBtn);
		waitForClicable(nextBtn);
		clickOnElement(nextBtn);
	}
	

	public boolean isOrderSummeryDetailsCorrect(String givenQuantity, String givenColor, String givenSize) {
		waitForVisibility(orderQuantityInSummary);
		if(orderQuantityInSummary.getText().contains(givenQuantity)) {
			if(waitForInVisiblityOfLocator(By.id("checkout-loader")));
			waitForClicable(summaryViewDetailsDrpDwn);
			clickOnElement(summaryViewDetailsDrpDwn);
			
			if(sizeDetailInSummary.getText().contains(givenSize) &&
			  colorDetailInSummary.getText().contains(givenColor)) {
				  return true;
			}	 
		}	
		return false;
	}
	
	public boolean isFinalPriceAfterShippinRight(String givenFinalPrice) {
		waitForVisibility(finalPriceAfterShipping);
		return finalPriceAfterShipping.getText().contains(givenFinalPrice);
	}
	
	public void clickOnPlaceOrder() {
		waitForClicable(placeOrderBtn);
		clickOnElement(placeOrderBtn);
	}
	
	public boolean isOrderPlacedSuccessMsgDisplayed() {
		return isWebElementVisible(orderPlaceSuccessMsg);
	}
	
}
