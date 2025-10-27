package com.amazon.utility;

import com.amazon.base.BasePage;
import config.user.ConfigFileManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthSessionManager extends BasePage {

    /// thread-safe SINGLETON implementation using volatile
    private static volatile AuthSessionManager instance;
    private final WebDriver driver;
    private AuthSessionManager(WebDriver driver) { this.driver = driver; }

    /// double-checked locking to ensure only one instance is created
    public static AuthSessionManager getInstance(WebDriver driver) {
        if (instance == null) {
            synchronized (AuthSessionManager.class) {
                if (instance == null) {
                    instance = new AuthSessionManager(driver);
                }
            }
        }
        return instance;
    }

    // PUBLIC ⚙️

    /// COMMENT ⚠️
    public void validateRegistration(By locator) {
        try {
            var text = ElementFinder.getVisibleElement(driver, locator).getText();
            var username = ConfigFileManager.loadUserConfig().getUsername();
            if (text.contains(username)) {
                SessionStorage.captureAuthorizedSession(driver);
            }
        } catch (Exception e) {
            // Handle the case where login validation fails
        }
    }
}
