package com.amazon.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementFinder {

    private static final Duration TIMEOUT_MIN = Duration.ofMillis(3000);

    // PUBLIC ⚙️

    ///
    public static WebElement getVisibleElement(WebDriver driver, By locator) {
        return new WebDriverWait(driver, TIMEOUT_MIN)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    ///
    public static WebElement getClickableElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_MIN);
        var element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    ///
    public static void waitForPageToLoad(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}