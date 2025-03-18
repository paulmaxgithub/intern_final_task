package com.amazon.pages;

import com.amazon.base.BasePage;
import com.amazon.utility.ElementWaitUtility;
import config.user.UserConfigService;
import org.openqa.selenium.By;

public class SignInPage extends BasePage {

    private enum ButtonType { CONTINUE, SIGH_IN }
    private ButtonType currentButtonState = ButtonType.CONTINUE;

    private final By emailFieldLocator      = By.cssSelector("#ap_email");
    private final By continueButtonLocator  = By.cssSelector("#continue");
    private final By passwordFieldLocator   = By.cssSelector("#ap_password");
    private final By signInButtonLocator    = By.cssSelector("#signInSubmit");

    /// Method to enter valid email
    public void enterValidEmail() {
        var userEmail = UserConfigService.getUserEmail();
        var emailField = ElementWaitUtility.getClickableElement(driver, emailFieldLocator);
        emailField.clear();
        emailField.sendKeys(userEmail);
    }

    ///
    public void enterValidPassword() {
        var validPassword = UserConfigService.getUserPassword();
        var passwordField = ElementWaitUtility.getClickableElement(driver, passwordFieldLocator);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
    }

    ///
    public void clickButtonToSubmit() {
        if (currentButtonState == ButtonType.CONTINUE) {
            ElementWaitUtility.getClickableElement(driver, continueButtonLocator).click();
            currentButtonState = ButtonType.SIGH_IN;
        } else if (currentButtonState == ButtonType.SIGH_IN) {
            ElementWaitUtility.getClickableElement(driver, signInButtonLocator).click();
        } else {
            //TODO: Provide Unexpected Behaviour ⚠️
        }
    }
}
