package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialTest {

    @Test
    public void whenCreatingNewSpecialTheBuyGetOffMatchThePassedParameters() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        Special special = Special.of(buy, get, off);
        assertEquals(buy, special.buy, 0);
        assertEquals(get, special.get, 0);
        assertEquals(off, special.off, 0);
    }

    @Test
    public void whenCalculatingPriceOfSpecialThatDoesNotMeetBuyRequirementStickPriceIsCharged() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        Special special = Special.of(buy, get, off);
        float quantity = 2f;
        float price = 2.99f;
        assertEquals(5.98f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfSpecialThatGetsOneDiscountItemVerifyTheWholeDiscountIsApplied() {
        float buy = 3f;
        float get = 1f;
        float off = 1f;
        Special special = Special.of(buy, get, off);
        float quantity = 4f;
        float price = 2.99f;
        assertEquals(8.97f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfSpecialThatGetsTwoDiscountItemsVerifyTheWholeDiscountIsApplied() {
        float buy = 3f;
        float get = 2f;
        float off = 1f;
        Special special = Special.of(buy, get, off);
        float quantity = 5f;
        float price = 2.99f;
        assertEquals(8.97f, special.priceOf(quantity, price), 0);
    }

    @Test
    public void whenCalculatingPriceOfSpecialThatGetsOneDiscountItemVerifyThePartialDiscountIsApplied() {
        float buy = 3f;
        float get = 1f;
        float off = 0.88f;
        Special special = Special.of(buy, get, off);
        float quantity = 4f;
        float price = 2.99f;
        assertEquals(9.33f, special.priceOf(quantity, price), 0);
    }


}