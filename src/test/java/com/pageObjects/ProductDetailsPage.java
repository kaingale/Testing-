package com.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.ActionLogger;
import com.utilities.ScenarioContextForSavingDynamicValues;

public class ProductDetailsPage extends BasePage {

		ActionLogger actionLogger;
		ScenarioContextForSavingDynamicValues scenarioContext;
		
		public ProductDetailsPage(WebDriver driver) {
			super(driver);
		}
		
		public ProductDetailsPage(WebDriver driver, ActionLogger actionLogger) {
			super(driver);
			this.actionLogger = actionLogger;
		}
	
		public ProductDetailsPage(WebDriver driver, ActionLogger actionLogger, ScenarioContextForSavingDynamicValues scenarioContext) {
			super(driver);
			this.actionLogger = actionLogger;
			this.scenarioContext = scenarioContext;
		}
		
	
		
	@FindBy(css = "div.swatch-attribute-options[aria-labelledby='option-label-size-144'] div")
	private List<WebElement> sizeList; 

	@FindBy(css = "div.swatch-attribute-options[aria-labelledby='option-label-color-93'] div")
	private List<WebElement> colorList; 
	
	@FindBy(css = "#qty")
	private WebElement quantityInputBox;

	@FindBy(css = "#product-addtocart-button")
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
	
	@FindBy(className = "loading-mask")
	private WebElement loadingMaskElement;
	
	
	
	public void chooseSizeForProduct(String givenSize) {
		scrollIntoViewElement(colorTitleEle);
		for(WebElement sizeEle : sizeList) {
			if(sizeEle.getText().equalsIgnoreCase(givenSize)) {
				sizeEle.click();
				actionLogger.logStep("Chose product size");
				break;
			}
		}
	}

	public void chooseColorForProduct(String givenColor) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(driver -> {
//		    List<WebElement> overlays = driver.findElements(By.cssSelector("div.modals-overlay"));
//		    return overlays.isEmpty() || overlays.stream().allMatch(e -> !e.isDisplayed());
//		});
		waitForInVisiblityOfLocator(By.className("loading-mask"));
		for(WebElement colorEle : colorList) {
			scrollIntoViewElement(colorEle);
			if(colorEle.getAttribute("aria-label").contains(givenColor)) {
				colorEle.click();
				actionLogger.logStep("Chose product color");
				waitForInVisiblityOfLocator(By.className("loading-mask"));
				break;
			}
		}
	}
	
	public void enterQuantity(String quantity) {
		fillInputBox(quantityInputBox, quantity);
		actionLogger.logStep("Entered quantity");
	}
	
	public void clickAddToCart() {
		waitForClicable(addToCartBtn);
		clickOnElement(addToCartBtn);
//		waitForVisibilityLocator(By.xpath("//button[@id='product-addtocart-button']/span[@text()='Added']"));
		waitForInVisiblityOfLocator(By.className("loading-mask"));
	}
	
	public void openShowCartPopup() {
		waitForClicable(showcartLnk);
		clickOnElement(showcartLnk);
		actionLogger.logStep("Clicked on show cart module");
	}
	
	public void clearCartBeforeTest() throws InterruptedException {
		openShowCartPopup();
		if(!noProductInCartMsg.isDisplayed()) {
			if(!isTheirItemsInCartCheckEle.isEmpty()) {
			waitForClicable(clearCartBeforeUse);
			clickOnElement(clearCartBeforeUse);
			actionLogger.logStep("Empty cart checked");
			}
		}
	}
	
	public void gotoCartPage() {
		waitForVisibility(viewcartLnk);
		waitForClicable(viewcartLnk);
		clickOnElement(viewcartLnk);
	}
	
	public Boolean isAddToCartSuccessMsgDisplayed() {
		waitForVisibility(addCartSuccessMsg);
		scrollIntoViewElement(addCartSuccessMsg);
		return isWebElementVisible(addCartSuccessMsg);
	}
	
	public void clickOnPopUpCheckoutBtn() {
		waitForClicable(productCheckoutBtnEle);
		clickOnElement(productCheckoutBtnEle);
		waitForInVisiblityOfLocator(By.className("loading-mask"));
	}
}
