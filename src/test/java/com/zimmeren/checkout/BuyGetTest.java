package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyGetTest {

    @Test
    public void whenCreatingNewBuyGetVerifyPropertiesMatchThePassedParameters() {
        BuyGet special = BuyGet.of(3f, 1f, 1f, 1);
        assertEquals(3f, special.buy, 0);
        assertEquals(1f, special.get, 0);
        assertEquals(1f, special.off, 0);
        assertEquals(1, special.limit, 0);
    }

    @Test
    public void whenCalculatingPriceThatDoesNotMeetBuyRequirementStickPriceIsCharged() {
        BuyGet special = BuyGet.of(3f, 1f, 1f, 0);
        assertEquals(5.98f, special.priceOf(2f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatGetsOneDiscountItemVerifyTheWholeDiscountIsApplied() {
        BuyGet special = BuyGet.of(3f, 1f, 1f, 0);
        assertEquals(8.97f, special.priceOf(4f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatGetsTwoDiscountItemsVerifyTheWholeDiscountIsAppliedToBoth() {
        BuyGet special = BuyGet.of(3f, 2f, 1f, 0);
        assertEquals(8.97f, special.priceOf(5f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatGetsOneDiscountItemVerifyThePartialDiscountIsApplied() {
        BuyGet special = BuyGet.of(3f, 1f, 0.88f, 0);
        assertEquals(9.33f, special.priceOf(4f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceThatQualifiesTwiceVerifyDiscountIsAppliedTwice() {
        BuyGet special = BuyGet.of(3f, 1f, 1f, 0);
        assertEquals(17.94f, special.priceOf(8f, 2.99f), 0);
    }

    @Test
    public void whenCalculatingPriceOfFractionalQuantifiersVerifyTheExpectedDiscountIsApplied() {
        BuyGet special = BuyGet.of(2.3333f, .6666f, .7333f, 0);
        assertEquals(11.59f, special.priceOf(8f, 1.65f), 0);
    }

    @Test
    public void whenCalculatingPriceWithLimitVerifyAfterLimitStickerPriceIsCharged() {
        BuyGet special = BuyGet.of(2f, 1f, 1f, 2);
        assertEquals(29.9f, special.priceOf(12f, 2.99f), 0);
    }

}