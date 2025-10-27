package com.amazon.pages;

import com.amazon.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.amazon.utility.ElementFinder.*;

public class ProductResultsPage extends BasePage {

    // ELEMENT LOCATORS ⚙️
    private final By productImageContainerLocator = By.cssSelector("#main-image-container");

    // TEST CASE #2 ⚙️
    public String getProductTitleTextFromResultsByValue(int value) {
        return getProductTitleLabelFromResultsByValue(value).getText();
    }

    // TEST CASE #3 ⚙️
    /// TRANSITION TO PRODUCT
    public ProductPage selectProductToOpenProductPageWith() {
        getProductTitleThatAvailableToPurchase().click();

        // Wait until selected product's image container loads
        waitForPageToLoad(driver, productImageContainerLocator);
        return new ProductPage();
    }

    // PRIVATE ⚙️

    /// GET ANY TITLE LABEL
    private WebElement getProductTitleLabelFromResultsByValue(int value) {
        String xpath = String.format("(//*[@role='listitem']//h2//span[contains(text(), 'iPhone')])[%d]", value);
        return getClickableElement(driver, By.xpath(xpath));
    }

    ///
    private WebElement getProductTitleThatAvailableToPurchase() {
        int index = findContainerIndex();
        String xpath = String.format("(//*[@role='listitem']//h2//span[contains(text(), 'iPhone')])[%d]", index);
        return getClickableElement(driver, By.xpath(xpath));
    }

    /// FIND PRODUCT THAT HAS "Add to cart" BUTTON
    public int findContainerIndex() {
        // Find all the elements with the first container class
        List<WebElement> containerList = driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));

        // Using Stream to find the first container that contains the inner container
        return containerList.stream()
                .map(container -> container.findElements(By.xpath(".//div[@class='puis-atcb-container']"))

                        .isEmpty() ? -1 : containerList.indexOf(container) + 1) // If inner container found, return 1-based index
                .filter(index -> index != -1)                                   // Filter out -1 results (no match)
                .findFirst()                                                    // Get the first result
                .orElse(-1);                                              // If no result, return -1
    }
}
