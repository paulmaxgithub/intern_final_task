package com.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementFinder {

    /// ELEMENT LOCATORS
    private final static By greetingsTextLabelLocator  = By.cssSelector("#nav-link-accountList-nav-line-1");

    private static final Duration TIMEOUT_MIN = Duration.ofMillis(2000);

    /// PUBLIC
    public static WebElement getGreetingsTextLabel(WebDriver driver) {
        return getVisibleElement(driver, greetingsTextLabelLocator);
    }

    ///
    public static WebElement getVisibleElement(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIMEOUT_MIN)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    ///
    public static WebElement getClickableElement(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIMEOUT_MIN)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}

