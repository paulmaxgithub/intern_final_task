package com.amazon.tests;

import com.amazon.base.BaseTest;
import config.user.UserConfigService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class SearchProductsTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"iPhone"})
    public void test_SearchedProductIsFound(String value) {

        // PRECONDITION
        runSessionAsAuthorizedUser();

        var actualResult = homePage.getAccountListText();
        var expectedResult = UserConfigService.getUserName();
        assertTrue(actualResult.contains(expectedResult));

        //STEP 1
        homePage.enterValueInSearchField();

        //STEP 2
        homePage.clickSearchButton();

        ///TODO: Test must check a list of products, which were requested ⚠️
        // VERIFYING
        var actualElementTitle = homePage.getSearchedProductTitle();
        assertTrue(actualElementTitle.contains(value), "Searched products (iPhone) are NOT on the screen ❌");
    }
}
