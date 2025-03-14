package com.amazon.pages;

import com.amazon.base.BasePage;
import com.amazon.utils.WaitUtils;
import config.ConfigReader;
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
        var emailField = WaitUtils.getClickableAndEmptyField(driver, emailFieldLocator);
        var userEmail = ConfigReader.getUserEmailFromConfigJSON();
        emailField.sendKeys(userEmail);
    }

    ///
    public void enterValidPassword() {
        var passwordField = WaitUtils.getClickableAndEmptyField(driver, passwordFieldLocator);
        var validPassword = ConfigReader.getUserPasswordFromConfigJSON();
        passwordField.sendKeys(validPassword);
    }

    ///
    public void clickButtonToSubmit() {
        if (currentButtonState == ButtonType.CONTINUE) {
            WaitUtils.getClickableElement(driver, continueButtonLocator).click();
            currentButtonState = ButtonType.SIGH_IN;
        } else if (currentButtonState == ButtonType.SIGH_IN) {
            WaitUtils.getClickableElement(driver, signInButtonLocator).click();
        } else {
            //TODO: Provide Unexpected Behaviour ⚠️
        }
    }
}
