package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	
	
	@FindBy(css = "tr.item-info")
	private List<WebElement> cartProductListRows;
	
	@FindBy(css = "tr.grand span.price")
	private WebElement finalPriceEle;

	@FindBy(xpath = "//button[text()='Proceed to Checkout']")
	private WebElement proceedToChkoutBtn;
	
	@FindBy(xpath = "//th[text()='Tax']/following::td[@class='amount'][@data-th='Tax']")
	private WebElement taxAmountEle;
	
	
	public boolean isCorrectProductAddedToCart(String givenProduct, String givenPrice) {
//		System.out.println(cartProductListRows.size());
		for (WebElement row : cartProductListRows) {
			waitForVisibility(row); 
			String productName = row.findElement(By.cssSelector(" strong a")).getText(); 
			String productPrice = row.findElement(By.cssSelector(" td.subtotal span.price")).getText();
			if (productName.contains(givenProduct) && productPrice.contains(givenPrice)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isFinalPriceRight(String givenFinalPrice) {
		return waitForVisibility(finalPriceEle).getText().contains(givenFinalPrice);
	}
	
	public void gotoCheckoutPage() {
		waitForVisibility(taxAmountEle);
		waitForClicable(proceedToChkoutBtn);
		scrollIntoViewElement(proceedToChkoutBtn);
		clickOnElement(proceedToChkoutBtn);
	}
}
