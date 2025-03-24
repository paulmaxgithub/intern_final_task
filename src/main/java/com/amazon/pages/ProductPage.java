package com.amazon.pages;

import com.amazon.base.BasePage;
import com.amazon.utility.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    // LOCATORS ⚙️
    private final By cartProductCountLocator    = By.xpath("//*[@id='nav-cart-count']");
    private final By addToCartButtonLocator     = By.cssSelector("input#add-to-cart-button");
    private final By greenMarkIconLocator       = By.cssSelector("#NATC_SMART_WAGON_CONF_MSG_SUCCESS > div");
    private final By addedToCartTextLocator     = By.xpath("//*[@id='NATC_SMART_WAGON_CONF_MSG_SUCCESS']/h1");
    private final By deleteProductButtonLocator = By.cssSelector(".sc-quantity-stepper button:nth-child(2)");
    private final By cartIconLocator            = By.cssSelector("#nav-cart-count-container");
    private final By productNameLocator         = By.xpath("//h1//span[@id='productTitle']");

    private String selectedProductName;

    ///
    public void eraseShoppingCartIfNeeded() {
        var text = ElementFinder.getVisibleElement(driver, cartProductCountLocator).getText();
        var count = Integer.parseInt(text);

        if (count > 0) {
            ElementFinder.getClickableElement(driver, deleteProductButtonLocator).click();
        }
    }

    /// Store selected product name which can bew used in future tests
    public void clickAddToCartButton() {
        selectedProductName = ElementFinder.getVisibleElement(driver, productNameLocator).getText();
        ElementFinder.getClickableElement(driver, addToCartButtonLocator).click();
    }

    public boolean greenMarkIconIsDisplayed() {
        var icon = ElementFinder.getVisibleElement(driver, greenMarkIconLocator);
        return icon.isDisplayed();
    }

    public WebElement getAddedToCartTextElement() {
        return ElementFinder.getVisibleElement(driver, addedToCartTextLocator);
    }

    public int getCartProductCount() {
        var counter = ElementFinder.getVisibleElement(driver, cartProductCountLocator);
        return Integer.parseInt(counter.getText());
    }

    // TEST CASE #4
    public CartPage clickCartIconAndMoveToCartPage() {
        ElementFinder.getClickableElement(driver, cartIconLocator).click();
        return new CartPage(selectedProductName);
    }
}
