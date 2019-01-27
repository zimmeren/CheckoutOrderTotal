package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void whenRegisterPurchasesAreEmptyThenTotalIsZero() {
        Register register = new Register();
        assertEquals(0.0f, register.getTotal(), 0);
    }

}