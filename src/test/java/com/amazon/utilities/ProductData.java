package com.amazon.utilities;

/**
 * A record to represent product data for testing purposes.
 * This class stores the details of a product, including:
 * - productName: The name of the product.
 * - orderNumber: A unique identifier for the product order.
 * - cartStatusText: The status text indicating the cart status (e.g., "Added to cart").
 * - count: The quantity of the product in the cart.
 */
public record ProductData(String productName, int orderNumber, String cartStatusText, int count) {}

