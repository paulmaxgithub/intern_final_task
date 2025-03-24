package com.amazon.tests;

import com.amazon.base.BaseTest;
import com.amazon.utilities.ProductData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.amazon.utilities.DataProviders.testDataStream;
import static org.junit.jupiter.api.Assertions.*;

public class AddProductToCartTest extends BaseTest {

    // PRECONDITION
    public AddProductToCartTest() { isAuthRequired = true; }

    @ParameterizedTest
    @MethodSource(testDataStream)
    public void test_ProductInCart(ProductData data) {

        // PRECONDITION
        var productResultsPage = searchForProduct(data.productName());
        var productPage = productResultsPage.selectProductToOpenProductPageWith();
        productPage.eraseShoppingCartIfNeeded();

        // STEP 1
        ///todo: set USA region (e.g. 10001) if needed, when button for selected product is not available ⚠️
        productPage.clickAddToCartButton();

        //VERIFICATION
        assertTrue(productPage.greenMarkIconIsDisplayed());

        var text = productPage.getAddedToCartTextElement().getText().toLowerCase();
        var expectedResult = data.cartStatusText().toLowerCase();

        assertTrue(text.contains(expectedResult));
        assertEquals(productPage.getCartProductCount(), data.count());
    }
}