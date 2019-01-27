package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilityTest {

    @Test
    public void whenGivenValueWithMoreThanTwoDecimalPlacesItIsRoundedToTwoDecimalPlaces() {
        float unrounded = 23.333333333333333f;
        assertEquals(23.34f, Utility.roundUpToCent(unrounded), 0);
    }

}