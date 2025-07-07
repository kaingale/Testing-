package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.ActionLogger;

public class OrderHistoryPage extends BasePage {
		
		ActionLogger actionLogger;
		
		public OrderHistoryPage(WebDriver driver) {
			super(driver);
		}
		
		public OrderHistoryPage(WebDriver driver, ActionLogger actionLogger) {
			super(driver);
			this.actionLogger = actionLogger;
		}
		
		
	@FindBy(css = "table#my-orders-table tbody tr")
	private List<WebElement> ordersList;

	@FindBy(css = "#order-item-row-1 strong")
	private WebElement orderHistoryProdName;
	
	@FindBy(xpath = "//dl[@class='item-options']/dd[1]")
	private WebElement orderHistoryProdSize;

	@FindBy(xpath = "//dl[@class='item-options']/dd[2]")
	private WebElement orderHistoryProdColor;
	
	@FindBy(css = "tr.grand_total span")
	private WebElement orderHistoryGrandTotalAmount;
	
	public void searchOrderIdInListAndGotoOrderDetails(String givenOrderId) {
		for(WebElement order : ordersList) {
			String orderId = order.findElement(By.cssSelector(" td.id")).getText();
			if(orderId.equalsIgnoreCase(givenOrderId)) {
				WebElement viewOrder = order.findElement(By.cssSelector(" td.actions a.view"));
				viewOrder.click();
				actionLogger.logStep("Order id checked in list");
				return;
			}
		}
	}
	
	public boolean areAllDetailsCorrect(String givenName, String giveSize, String givenColor, String givenGrandTotal) {
		scrollIntoViewElement(orderHistoryGrandTotalAmount);
		String prodName = orderHistoryProdName.getText();
		String prodSize =  orderHistoryProdSize.getText();
		String prodColor = orderHistoryProdColor.getText();
		String grandTotal = orderHistoryGrandTotalAmount.getText();
		
		if(prodName.contains(givenName) && prodSize.contains(giveSize) && prodColor.contains(givenColor) && grandTotal.contains(givenGrandTotal)) {
			actionLogger.logStep("Order details check");
			return true;
		}		
		actionLogger.logStep("Order details check");
		return false;
	}
	
}
