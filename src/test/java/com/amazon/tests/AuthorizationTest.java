package com.amazon.tests;

import com.amazon.base.BaseTest;
import config.ConfigReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorizationTest extends BaseTest {

    @Test
    public void test_SuccessfulAuthorization() {

        //STEP 1
        homePage.hoverOverAccountAndLists();

        //STEP 2
        var signInPage = homePage.clickSignInButton();

        //STEP 3
        signInPage.enterValidEmail();
        signInPage.clickButtonToSubmit();

        //STEP 4
        signInPage.enterValidPassword();
        signInPage.clickButtonToSubmit();

        // Verifying
        var actualValue = homePage.getGreetingsTextForAuthenticatedUser();
        var expectedValue = ConfigReader.getUserNameFromConfigJSON();
        Assertions.assertTrue(actualValue.contains(expectedValue), "Greetings does NOT contain username ⚠️");
    }
}
