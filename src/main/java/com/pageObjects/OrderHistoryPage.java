package com.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHistoryPage extends BasePage {
		
		public OrderHistoryPage(WebDriver driver) {
			super(driver);
		}
		
		
	@FindBy(css = "table#my-orders-table tbody tr")
	private List<WebElement> ordersList;
	
	
	public void searchOrderIdInListAndGotoOrderDetails(String orderId, String orderTotal) {}
	
	
}
