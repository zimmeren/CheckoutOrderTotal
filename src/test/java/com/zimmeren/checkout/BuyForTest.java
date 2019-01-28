package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyForTest {

    @Test
    public void whenCreatingNewBuyGetVerifyPropertiesMatchThePassedParameters() {
        float buy = 3f;
        float forTotal = 5f;
        BuyFor special = BuyFor.of(buy, forTotal);
        assertEquals(buy, special.buy, 0);
        assertEquals(forTotal, special.forTotal, 0);
    }

}