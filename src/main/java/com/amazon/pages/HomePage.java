package com.amazon.pages;

import com.amazon.base.BasePage;
import com.amazon.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    private final By accountAndListLocator  = By.cssSelector("#nav-link-accountList");
    private final By signInButtonLocator    = By.cssSelector("#nav-flyout-ya-signin span");
    private final By greetingsLocator       = By.cssSelector("#nav-link-accountList-nav-line-1");


    /// Method to hover over 'Account & Lists' in header.
    /// HomePage can display advertising info & not contain a login menu,
    /// so the page can be reloaded ⚠️
    public WebElement hoverOverAccountAndLists() {
        try {
            var element = WaitUtils.getVisibleElement(driver, accountAndListLocator);
            new Actions(driver).moveToElement(element).perform();
            return element;
        } catch (NoSuchElementException | TimeoutException e) {
            driver.navigate().refresh();

            System.out.println("advertising info 👻"); //TODO: Provide notification

            var element = WaitUtils.getVisibleElement(driver, accountAndListLocator);
            new Actions(driver).moveToElement(element).perform();
            return element;
        }
    }

    /// Method to click on the 'Sign-In' button
    public SignInPage clickSignInButton() {
        if (hoverOverAccountAndLists().isDisplayed()) {
            WaitUtils.getClickableElement(driver, signInButtonLocator).click();
            return new SignInPage();
        } else {
            return null; ///TODO: Provide a notification (e.g. Reporters) ⚠️
        }
    }

    ///
    public String getGreetingsTextForAuthenticatedUser() {
        var element = WaitUtils.getVisibleElement(driver, greetingsLocator);
        return element.getText();
    }
}
