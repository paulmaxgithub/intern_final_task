package com.amazon.pages;

import com.amazon.base.BasePage;
import config.user.UserConfigService;
import org.openqa.selenium.By;

import static com.amazon.utility.ElementFinder.getClickableElement;
import static com.amazon.utility.AuthSessionManager.getInstance;

public class SignInPage extends BasePage {

    private enum ButtonType { CONTINUE, SIGH_IN }
    private ButtonType currentButtonState = ButtonType.CONTINUE;

    private final By emailFieldLocator      = By.cssSelector("#ap_email");
    private final By continueButtonLocator  = By.cssSelector("#continue");
    private final By passwordFieldLocator   = By.cssSelector("#ap_password");
    private final By signInButtonLocator    = By.cssSelector("#signInSubmit");

    /// Method to enter valid email
    public void enterDefaultEmail() {
        var userEmail = UserConfigService.getUserEmail();
        var emailField = getClickableElement(driver, emailFieldLocator);
        emailField.clear();
        emailField.sendKeys(userEmail);
    }

    ///
    public void enterDefaultPassword() {
        var validPassword = UserConfigService.getUserPassword();
        var passwordField = getClickableElement(driver, passwordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
    }

    ///TODO: Refactor code (method) ⚠️
    public void handleButtonClickBasedOnState() {
        if (currentButtonState == ButtonType.CONTINUE) {
            getClickableElement(driver, continueButtonLocator).click();
            currentButtonState = ButtonType.SIGH_IN;
        } else if (currentButtonState == ButtonType.SIGH_IN) {
            getClickableElement(driver, signInButtonLocator).click();
            getInstance(driver).validateRegistration();
        } else {
            //TODO: Provide Unexpected Behaviour ⚠️
        }
    }
}
