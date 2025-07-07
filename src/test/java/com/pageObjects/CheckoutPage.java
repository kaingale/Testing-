package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.ActionLogger;
import com.utilities.ScenarioContextForSavingDynamicValues;

public class CheckoutPage extends BasePage{
		
		ActionLogger actionLogger;
		ScenarioContextForSavingDynamicValues scenarioContext;
	
		public CheckoutPage(WebDriver driver) {
			super(driver);
		}
		
		public CheckoutPage(WebDriver driver, ActionLogger actionLogger) {
			super(driver);
			this.actionLogger = actionLogger;
		}

		public CheckoutPage(WebDriver driver, ActionLogger actionLogger, ScenarioContextForSavingDynamicValues scenarioContext) {
			super(driver);
			this.actionLogger = actionLogger;
			this.scenarioContext = scenarioContext;
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
	
	
	
	public void chooseCorrectShippingPrice(String givenShippingWay) {
//		System.out.println(shippingPriceList.size());
		for (WebElement row : shippingPriceList) {
			waitForVisibility(row); 
			String shippingPrice = row.findElement(By.cssSelector("td[id='label_method_flatrate_flatrate']")).getText(); 
			WebElement radioBtn = row.findElement(By.cssSelector("input"));
			if (shippingPrice.contains(givenShippingWay) && !radioBtn.isSelected()) {
				String shpPrice =  row.findElement(By.cssSelector("span.price")).getText();
				scenarioContext.setContext("shippingPriceOnCheckOutPage", shpPrice);
				radioBtn.click();
				actionLogger.logStep("Chose shipping price");
				break;
			}
		}
	}
	
	public void clickOnNextBtn() {
		scrollIntoViewElement(nextBtn);
		waitForClicable(nextBtn);
		clickOnElement(nextBtn);
		waitForInVisiblityOfLocator(By.className("loading-mask"));
	}
	

	public boolean isOrderSummeryDetailsCorrect(String givenQuantity, String givenColor, String givenSize) {
		waitForVisibility(orderQuantityInSummary);
		if(orderQuantityInSummary.getText().contains(givenQuantity)) {
			if(waitForInVisiblityOfLocator(By.id("checkout-loader")));
			waitForClicable(summaryViewDetailsDrpDwn);
			clickOnElement(summaryViewDetailsDrpDwn);
			
			if(sizeDetailInSummary.getText().contains(givenSize) &&
			  colorDetailInSummary.getText().contains(givenColor)) {
				actionLogger.logStep("Order summery checked");
				  return true;
			}	 
		}	
		actionLogger.logStep("Order summery checked");
		return false;
	}
	
	public boolean isFinalPriceAfterShippinRight(String givenFinalPrice) {
		waitForVisibility(finalPriceAfterShipping);
		actionLogger.logStep("Final price after shipping checked");
		return finalPriceAfterShipping.getText().contains(givenFinalPrice);
	}
	
	public void clickOnPlaceOrder() {
		waitForClicable(placeOrderBtn);
		clickOnElement(placeOrderBtn);
		waitForInVisiblityOfLocator(By.className("loading-mask"));
	}
	
	public boolean isOrderPlacedSuccessMsgDisplayed() {
		waitForVisibility(orderPlaceSuccessMsg);
		actionLogger.logStep("Order place msg checked");
		return isWebElementVisible(orderPlaceSuccessMsg);
	}
	
}
