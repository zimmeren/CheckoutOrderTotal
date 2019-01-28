package com.zimmeren.checkout;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UtilityTest {

    @Test
    public void whenGivenValueWithMoreThanTwoDecimalPlacesItIsRoundedToTwoDecimalPlaces() {
        float unrounded = 23.333333333333333f;
        assertEquals(23.34f, Utility.roundUpToCent(unrounded), 0);
    }

    @Test
    public void whenGivenValuesThatResultInFloatingPointErrorItCanBeCorrected() {
        float floatingError = 11.96f + (6f * 2.99f);
        float result = Utility.roundUpToCent(floatingError);
        assertEquals(29.9f, result, 0);
    }

}