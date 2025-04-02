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
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    private static WebDriver driver;
    private BasePage basePage;
    protected HomePage homePage;

    private static String AMAZON_URL;

    protected boolean isAuthRequired = false;

    @BeforeAll
    protected static void setUp() {
        setAMAZON_URL();

        // Check for the environment variable to determine headless mode
        String headlessMode = System.getenv("HEADLESS_MODE");

        ChromeOptions options = new ChromeOptions();

        // If headless mode is enabled, add the corresponding options
        if ("true".equalsIgnoreCase(headlessMode)) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);

        // Maximize the window if not in headless mode
        if (!"true".equalsIgnoreCase(headlessMode)) {
            driver.manage().window().maximize();
        }
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
