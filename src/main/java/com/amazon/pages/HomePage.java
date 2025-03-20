package com.amazon.pages;

import com.amazon.base.BasePage;
import com.amazon.utility.ElementWaitUtility;

import com.amazon.utility.SessionStorage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    private final By accountAndListLocator      = By.cssSelector("#nav-link-accountList");
    private final By signInButtonLocator        = By.cssSelector("#nav-flyout-ya-signin span");
    private final By greetingsTextLabelLocator  = By.cssSelector("#nav-link-accountList-nav-line-1");

    private final By searchInputFieldLocator    = By.cssSelector("input[type='text']");
    private final By searchInputButtonLocator   = By.cssSelector("input[type='submit']");
    private final By firstFoundElementLocator   = By.xpath("(//*[@role=\"listitem\"]//h2//span[contains(text(), 'iPhone')])[1]");


    // TEST CASE #1 ⚙️

    /// Method to hover over 'Account & Lists' in header.
    /// HomePage can display advertising info & not contain a login menu,
    /// so the page can be reloaded ⚠️
    public WebElement getAccountListInHeader() {
        try {
            var element = ElementWaitUtility.getVisibleElement(driver, accountAndListLocator);
            new Actions(driver).moveToElement(element).perform();
            return element;
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("PROMOTION PAGE IS DISPLAYED 👻");    //TODO: Provide notification
            driver.navigate().refresh();        // reload page
            return getAccountListInHeader();  // recall method
        }
    }

    /// Method to click on the 'Sign-In' button
    public SignInPage clickSignInButtonInAccountList() {
        if (getAccountListInHeader().isDisplayed()) {
            ElementWaitUtility.getClickableElement(driver, signInButtonLocator).click();
            return new SignInPage();
        } else {
            return null;                                            //TODO: Provide a notification (e.g. Reporters) ⚠️
        }
    }

    /// Get string from text to check username in this text or get text with non-auth name
    public String getAccountListText() {
        var element = ElementWaitUtility.getVisibleElement(driver, greetingsTextLabelLocator);

        SessionStorage.captureAuthorizedSession(driver);            //TODO: Provide a trigger to call this method in another place

        return element.getText();
    }

    // TEST CASE #2 ⚙️

    public void enterValueInSearchField() {
        var element = ElementWaitUtility.getVisibleElement(driver, searchInputFieldLocator);
        element.clear();
        element.sendKeys("iPhone");
    }

    public void clickSearchButton() {
        var element = ElementWaitUtility.getClickableElement(driver, searchInputButtonLocator);
        element.click();
    }

    public String getSearchedProductTitle() {
        var element = ElementWaitUtility.getVisibleElement(driver, firstFoundElementLocator);
        return element.getText();
    }
}