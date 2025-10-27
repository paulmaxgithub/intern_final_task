package com.amazon.utilities;

import java.util.stream.Stream;

public class DataProviders {

    // A constant holding the reference to the data stream, used to access the data provider.
    public final static String testDataStream = "com.amazon.utilities.DataProviders#dataStream";

    /**
     * Provides a stream of product data for testing purposes.
     * This method returns a stream containing a single ProductData object.
     *
     * @return Stream<ProductData> Stream of product data
     */
    public static Stream<ProductData> dataStream() {
        return Stream.of(
                new ProductData("iPhone", 1, "Added to cart", 1)
        );
    }
}
