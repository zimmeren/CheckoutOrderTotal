package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyForTest {

    @Test
    public void whenCreatingNewBuyForVerifyPropertiesMatchThePassedParameters() {
        float buy = 3f;
        float forTotal = 5f;
        int limit = 1;
        BuyFor special = BuyFor.of(buy, forTotal, limit);
        assertEquals(buy, special.buy, 0);
        assertEquals(forTotal, special.forTotal, 0);
        assertEquals(limit, special.limit, 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyForThatDoesNotMeetBuyRequirementStickerPriceIsCharged() {
        float buy = 3f;
        float forTotal = 5f;
        BuyFor special = BuyFor.of(buy, forTotal, 0);
        float quantity = 2f;
        float price = 2.99f;
        assertEquals(5.98f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyForThatGetsOneDiscountItemVerifyTheWholeDiscountIsApplied() {
        float buy = 3f;
        float forTotal = 5f;
        BuyFor special = BuyFor.of(buy, forTotal, 0);
        float quantity = 3f;
        float price = 2.99f;
        assertEquals(5f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyForThatGetsOneDiscountAndThenBuysMoreVerifyDiscountIsAppliedPartially() {
        float buy = 3f;
        float forTotal = 5f;
        BuyFor special = BuyFor.of(buy, forTotal, 0);
        float quantity = 4f;
        float price = 2.99f;
        assertEquals(7.99f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyForThatGetsMultipleDiscountVerifyDiscountAreApplied() {
        float buy = 3f;
        float forTotal = 5f;
        BuyFor special = BuyFor.of(buy, forTotal, 0);
        float quantity = 6f;
        float price = 2.99f;
        assertEquals(10f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingFractionalValuesWithButForSpecialVerifyDiscountIsAppliedCorrectly() {
        float buy = 2.333f;
        float forTotal = 2.03f;
        BuyFor special = BuyFor.of(buy, forTotal, 0);
        float quantity = 4.977f;
        float price = 1.03f;
        assertEquals(4.38f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyForWithLimitVerifyAfterLimitStickerPriceIsCharged() {
        float buy = 2f;
        float forTotal = 5f;
        BuyFor special = BuyFor.of(buy, forTotal, 2);
        float quantity = 12f;
        float price = 2.99f;
        assertEquals(33.92f, special.priceOf(quantity, price), 0);
    }

}