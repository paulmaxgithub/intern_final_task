package com.amazon.pages;

import com.amazon.base.BasePage;
import com.amazon.utility.ElementFinder;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    String productName;
    public CartPage(String name) { productName = name; }

    // LOCATORS ⚙️
    private final By shoppingCartTitleLocator   = By.cssSelector("#sc-active-items-header");
    private final By productNameLocator         = By.xpath("//h4//span[@class='a-truncate-cut']");

    ///
    public boolean isCartPageOpen() {
        return ElementFinder.getVisibleElement(driver, shoppingCartTitleLocator).isDisplayed();
    }

    public boolean CartPageShowsSameProductName() {
        var productNameElement = ElementFinder.getVisibleElement(driver, productNameLocator);
        var stringValueFromElement = productNameElement.getText();
        return stringValueFromElement.equals(productName);
    }
}
