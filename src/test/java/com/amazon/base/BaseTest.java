package com.amazon.base;

import com.amazon.pages.HomePage;
import com.amazon.pages.ProductResultsPage;
import com.amazon.utility.SessionStorage;
import config.user.UserConfigService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    private static WebDriver driver;
    private BasePage basePage;
    protected HomePage homePage;

    private static String AMAZON_URL;

    protected boolean isAuthRequired = false;

    @BeforeAll
    protected static void setUp() {
        setAMAZON_URL();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    protected void loadApp() {
        driver.get(AMAZON_URL);

        basePage = new BasePage();
        basePage.setDriver(driver);
        homePage = new HomePage();

        if (isAuthRequired) {
            runSessionAsAuthorizedUser();
        }
    }

    @AfterAll
    protected static void tearDown() {
        driver.quit();
    }

    // SETUP

    ///
    private static void setAMAZON_URL() {
        AMAZON_URL = UserConfigService.getBaseURL();
    }

    // PRECONDITION

    /// Provide tests as authorized user using stored session token
    private void runSessionAsAuthorizedUser() {
        SessionStorage.applyAuthorizedSession(driver);
    }

    ///
    protected ProductResultsPage searchForProduct(String value) {
        homePage.enterValueInSearchField(value);
        homePage.clickSearchButton();
        return new ProductResultsPage();
    }
}
