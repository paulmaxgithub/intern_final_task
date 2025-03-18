package com.amazon.utility;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class SessionStorage {

    /// Apply existing token to load page as authorized user if session token exists
    /// TODO: Move to ConfigService.
    public static void applyAuthorizedSession(WebDriver driver) {
        driver.manage().addCookie(new Cookie("session-id", "131-8651272-6999057"));
        driver.manage().addCookie(new Cookie("session-token", "3UYCW6duKeCRqvm4n7laDGrXhMJ3baIISkukhZyRK98ls1+jasewqel/QMKkG2+fwod7bvozhaKobxnVcCvQIFbDbh7DVbkpbXHOf/wg7GmO1CUjN7OIBfUQ/UT0HYz79PjqZujG9CjT+mTQ6WUw89wdkyhBs3TlyS5H7i5kjk6G6qjUBHnu+TJ6fHXytV0vEZ+PQgbHiBpn3wMcA1nGND57Ch/7KLgegjBejrJE+clXNVqlA65p1AG8Wbk5g8NQ+0Y+qJ4qS1UKzbm/DCdS/qeOHlb5MGJ/hpJAcL/OszzPfzoINVKAUbmJtp7JQHpQ+23xhy12O9crRouWcsSZN25FnwUnZk5WXuTHcGZ3pqc3I4nhjH4UNWvlmfBTu+8R"));
        driver.manage().addCookie(new Cookie("session-id-time", "2082787201l"));
        driver.manage().addCookie(new Cookie("ubid-main", "130-2798820-0432449"));
        driver.manage().addCookie(new Cookie("x-main", "\\\"TjRL3uNO@EgGCupz8dQrJzjSUPMmgDlmcZVDT1SxCbxSbhmPLlPl0@M1g3u0h6Qz\\\""));
        driver.navigate().refresh();
    }

    /// Capture current session token (Test Case #1 must be completed ⚠️)
    /// TODO: Add Logic to capture session params to store in aws_tokes.json
    public static void captureAuthorizedSession(WebDriver driver) {
        // get current cookie
//        Cookie sessionCookie = driver.manage().getCookieNamed("session-token");
    }
}