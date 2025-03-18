package com.amazon.base;

import com.amazon.pages.HomePage;
import com.amazon.utility.SessionStorage;
import config.user.UserConfigService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    private static WebDriver driver;
    private BasePage basePage;
    protected HomePage homePage;

    private static String AMAZON_URL;

    @BeforeAll
    protected static void setUp() {
        setAMAZON_URL();

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    protected void loadApp() {
        driver.get(AMAZON_URL);

        basePage = new BasePage();
        basePage.setDriver(driver);
        homePage = new HomePage();

        //TODO: Resolve Issue with Tokens ⚠️
        driver.manage().addCookie(new Cookie("session-id", "131-8651272-6999057"));
        driver.manage().addCookie(new Cookie("session-token", "VFrJbRPvdyI2EvDHM7/0VYwUE7I8Fbd7awc9tL470rL0mpOrEiq62B2vlQbF4I8ueUtFP86i3is/HeyOasJRaaLfIBuWNnAsadkmwctLHQhmBr0bn3xAOI8vTVUlQt1DpDs5bY7hTlHiL6OAseWDL5Fy7PTyaNvZQNudRLjaa1Ot7ABujWkRL9xNlR0J4jWLmKi5HOvkVrCXE1E+ufqQCc4p05v7+5IRkyBz9JOfjHq6Aquqd3tjQaWYCqxjL092+ZnF4x4YwHQsiZiB1fZB5wXb/4rWJEpRrQiDdrwrtejwTcPkY5P+4u5C6i2acwPr3Hn+ve/VZfMVXygU4PPdmrhXlNf1M06kA4nuj0DoRYGh1azOJcfBAg=="));
        driver.manage().addCookie(new Cookie("session-id-time", "2082787201l"));
        driver.manage().addCookie(new Cookie("ubid-main", "130-2798820-0432449"));
        driver.manage().addCookie(new Cookie("x-main", "xGEzzLrS3bh4JkwBlozMdxDvRSozUQ3bnOzvGC9leCs78HFE09DzxlRVJ1059nH7"));
        driver.navigate().refresh();
    }

    @AfterAll
    protected static void tearDown() throws InterruptedException {
        Thread.sleep(5000); //todo: remove ⚠️
        driver.quit();
    }

    /// SETUP
    private static void setAMAZON_URL() {
        AMAZON_URL = UserConfigService.getBaseURL();
    }

    /// Provide tests as authorized user using stored session token
    protected void runSessionAsAuthorizedUser() {
        SessionStorage.applyAuthorizedSession(driver);
    }
}
