package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.utilities.ActionLogger;

public class CartPage extends BasePage {

	ActionLogger actionLogger;
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	public CartPage(WebDriver driver, ActionLogger actionLogger) {
		super(driver);
		this.actionLogger = actionLogger;
	}

	
	
	@FindBy(css = "tr.item-info")
	private List<WebElement> cartProductListRows;
	
	@FindBy(css = "tr.grand span.price")
	private WebElement finalPriceEle;

	@FindBy(xpath = "//button[text()='Proceed to Checkout']")
	private WebElement proceedToChkoutBtn;
	
	@FindBy(xpath = "//th[text()='Tax']/following::td[@class='amount'][@data-th='Tax']")
	private WebElement taxAmountEle;
	
	@FindBy(className = "action-delete")
	private WebElement deleteCartItems;
	
	
	public boolean isCorrectProductAddedToCart(String givenProduct, String givenPrice) {
//		System.out.println(cartProductListRows.size());
		for (WebElement row : cartProductListRows) {
			waitForVisibility(row); 
			String productName = row.findElement(By.cssSelector(" strong a")).getText(); 
			String productPrice = row.findElement(By.cssSelector(" td.subtotal span.price")).getText();
			if (productName.contains(givenProduct) && productPrice.contains(givenPrice)) {
				actionLogger.logStep("Correct product checked");
				return true;
			}
		}
		actionLogger.logStep("Correct product checked");
		return false;
	}
	
	public boolean isFinalPriceRight(String givenFinalPrice) {
		waitForVisibility(finalPriceEle);
		actionLogger.logStep("Final priced checked");
		return	finalPriceEle.getText().contains(givenFinalPrice);
	}
	
	public void gotoCheckoutPage() {
		waitForVisibility(taxAmountEle);
		waitForClicable(proceedToChkoutBtn);
		scrollIntoViewElement(proceedToChkoutBtn);
		clickOnElement(proceedToChkoutBtn);
	}
	
	public void deleteCartItems() {
		waitForClicable(deleteCartItems);
		deleteCartItems.click();
	}
}
