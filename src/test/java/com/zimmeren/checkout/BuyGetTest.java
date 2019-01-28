package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyGetTest {

    @Test
    public void whenCreatingNewBuyGetVerifyPropertiesMatchThePassedParameters() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        float limit = 1f;
        BuyGet special = BuyGet.of(buy, get, off, limit);
        assertEquals(buy, special.buy, 0);
        assertEquals(get, special.get, 0);
        assertEquals(off, special.off, 0);
        assertEquals(limit, special.limit, 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyGetThatDoesNotMeetBuyRequirementStickPriceIsCharged() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        BuyGet special = BuyGet.of(buy, get, off, 0f);
        float quantity = 2f;
        float price = 2.99f;
        assertEquals(5.98f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyGetThatGetsOneDiscountItemVerifyTheWholeDiscountIsApplied() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        BuyGet special = BuyGet.of(buy, get, off, 0f);
        float quantity = 4f;
        float price = 2.99f;
        assertEquals(8.97f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyGetThatGetsTwoDiscountItemsVerifyTheWholeDiscountIsApplied() {
        float buy = 3f;
        float get = 2f;
        float off = 1f;
        BuyGet special = BuyGet.of(buy, get, off, 0f);
        float quantity = 5f;
        float price = 2.99f;
        assertEquals(8.97f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyGetThatGetsOneDiscountItemVerifyThePartialDiscountIsApplied() {
        float buy = 3f;
        float get = 1f;
        float off = 0.88f;
        BuyGet special = BuyGet.of(buy, get, off, 0f);
        float quantity = 4f;
        float price = 2.99f;
        assertEquals(9.33f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfBuyGetThatQualifiesTwiceVerifyDiscountIsApplied() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        BuyGet special = BuyGet.of(buy, get, off, 0f);
        float quantity = 8f;
        float price = 2.99f;
        assertEquals(17.94f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfPartialBuyAndGetAmountsVerifyDiscountIsApplied() {
        float buy = 2.3333f;
        float get = .6666f;
        float off = .7333f;
        BuyGet special = BuyGet.of(buy, get, off, 0f);
        float quantity = 8f;
        float price = 1.65f;
        assertEquals(11.59f, special.priceOf(quantity, price), 0);
    }

}