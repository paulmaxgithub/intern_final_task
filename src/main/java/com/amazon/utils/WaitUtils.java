package com.amazon.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration TIMEOUT_MIN = Duration.ofMillis(2000);

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

    /// Waiting for the Text Field to be Clickable & Empty
    public static WebElement getClickableAndEmptyField(WebDriver driver, By locator) {
            WebElement element = getClickableElement(driver, locator);
            element.clear();
            return element;
    }
}

