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

}