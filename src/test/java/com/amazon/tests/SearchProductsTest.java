package com.amazon.tests;

import com.amazon.base.BaseTest;
import config.user.UserConfigService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class SearchProductsTest extends BaseTest {

    // PRECONDITION
    public SearchProductsTest() { isAuthRequired = true; }

    @ParameterizedTest
    @MethodSource("productProvider")
    public void test_SearchedProductIsFound(String value, int orderNumber) {

        // PRECONDITION TEST ???
        var actualResult = homePage.getAccountListText();
        var expectedResult = UserConfigService.getUserName();
        assertTrue(actualResult.contains(expectedResult));

        //STEP 1, 2 (enter value, click button, get product results)
        var productResultsPage = searchForProduct(value);

        // VERIFYING
        var actualElementTitle = productResultsPage.getProductTitleTextFromResultsByValue(orderNumber);
        assertTrue(actualElementTitle.contains(value), "Searched products (iPhone) are NOT on the screen ❌");
    }

    ///
    private static Stream<Arguments> productProvider() {
        return Stream.of(Arguments.of("iPhone", 1));
    }
}