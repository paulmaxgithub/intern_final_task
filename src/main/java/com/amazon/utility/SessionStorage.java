package com.amazon.utility;

import config.AWS.services.CookieGetterService;
import config.AWS.services.CookieSetterService;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SessionStorage {

    /// Apply existing token to load page as authorized user if session token exists
    public static void applyAuthorizedSession(WebDriver driver) {
        CookieGetterService.getAWSCookieList().forEach(driver.manage()::addCookie);
        driver.navigate().refresh();
    }

    /// Capture current session token (Test Case #1 must be completed ⚠️)
    public static void captureAuthorizedSession(WebDriver driver) {

        // List of cookie names to retrieve
        List<String> cookieNames = Arrays.asList("session-id", "session-token", "session-id-time", "ubid-main", "x-main");

        // Loop through the cookie names and fetch the cookies
        List<Cookie> cookies = cookieNames.stream()
                .map(cookieName -> driver.manage().getCookieNamed(cookieName))
                .filter(cookie -> cookie != null) // Filter out any null cookies
                .collect(Collectors.toList());

        // Update the cookies in the AWS file
        try {
            CookieSetterService.updateAWSCookieList(cookies);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}