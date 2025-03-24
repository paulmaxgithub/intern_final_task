package com.amazon.tests;

import com.amazon.base.BaseTest;
import com.amazon.utilities.ProductData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.amazon.utilities.DataProviders.testDataStream;
import static org.junit.jupiter.api.Assertions.*;

public class ProductInCartTest extends BaseTest {

    // PRECONDITION
    public ProductInCartTest() { isAuthRequired = true; }

    @ParameterizedTest
    @MethodSource(testDataStream)
    public void addedProductInCartPageDisplayed(ProductData data) {

        // PRECONDITION
        var productResultsPage = searchForProduct(data.productName());
        var productPage = productResultsPage.selectProductToOpenProductPageWith();
        productPage.eraseShoppingCartIfNeeded();

        ///todo: set USA region (e.g. 10001) if needed, when button for selected product is not available ⚠️
        productPage.clickAddToCartButton();

        // STEP 1
        var cartPage = productPage.clickCartIconAndMoveToCartPage();

        // VERIFICATION
        assertTrue(cartPage.isCartPageOpen());
        assertTrue(cartPage.CartPageShowsSameProductName());
    }
}
