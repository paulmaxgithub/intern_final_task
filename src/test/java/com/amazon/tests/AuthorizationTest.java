package com.amazon.tests;

import com.amazon.base.BaseTest;
import config.user.UserConfigService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationTest extends BaseTest {

    @Test
    public void test_SuccessfulAuthorization() {

        //STEP 1
        homePage.getAccountListInHeader();

        //STEP 2
        var signInPage = homePage.clickSignInButtonInAccountList();

        //STEP 3
        signInPage.enterValidEmail();
        signInPage.clickButtonToSubmit();

        //STEP 4
        signInPage.enterValidPassword();
        signInPage.clickButtonToSubmit();

        // VERIFYING
        var actualValue = homePage.getAccountListText();
        var expectedValue = UserConfigService.getUserName();
        assertTrue(actualValue.contains(expectedValue), "Greetings does NOT contain username ⚠️");
    }
}
