package com.amazon.base;

import com.amazon.pages.HomePage;
import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
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
    }

    @AfterAll
    protected static void tearDown() throws InterruptedException {
        Thread.sleep(3000); //todo: remove ⚠️
        driver.quit();
    }

    private static void setAMAZON_URL() {
        AMAZON_URL = ConfigReader.getBaseURLFromConfigJSON();
    }
}
