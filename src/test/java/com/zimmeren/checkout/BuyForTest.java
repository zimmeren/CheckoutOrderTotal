package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyForTest {

    @Test
    public void whenCreatingNewBuyForVerifyPropertiesMatchThePassedParameters() {
        BuyFor special = BuyFor.of(3f, 5f, 1);
        assertEquals(3f, special.buy, 0);
        assertEquals(5f, special.forTotal, 0);
        assertEquals(1, special.limit, 0);
    }

    @Test
    public void whenCalculatingPriceThatDoesNotMeetBuyRequirementStickerPriceIsCharged() {
        BuyFor special = BuyFor.of(3f, 5f, 0);
        assertEquals(5.98f, special.priceOf(2f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatGetsOneDiscountItemVerifyTheWholeDiscountIsApplied() {
        BuyFor special = BuyFor.of(3f, 5f, 0);
        assertEquals(5f, special.priceOf(3f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatGetsOneDiscountAndThenBuysMoreVerifyDiscountIsAppliedJustForDiscountedQuantity() {
        BuyFor special = BuyFor.of(3f, 5f, 0);
        assertEquals(7.99f, special.priceOf(4f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatGetsMultipleDiscountVerifyMultipleDiscountAreApplied() {
        BuyFor special = BuyFor.of(3f, 5f, 0);
        assertEquals(10f, special.priceOf(6f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingFractionalValuesVerifyDiscountIsApplied() {
        BuyFor special = BuyFor.of(2.333f, 2.03f, 0);
        assertEquals(4.38f, special.priceOf(4.977f, 1.03f), 0);
    }

    @Test
    public void whenCalculatingPriceWithLimitVerifyAfterLimitStickerPriceIsCharged() {
        BuyFor special = BuyFor.of(2f, 5f, 2);
        assertEquals(33.92f, special.priceOf(12f, 2.99f), 0);
    }

}