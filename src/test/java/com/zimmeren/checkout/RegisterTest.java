package com.zimmeren.checkout;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void whenRegisterPurchasesAreEmptyThenTotalIsZero() {
        Register register = new Register();
        assertEquals(0.0f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingPurchaseToRegisterTotalIncreases() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.catalog.addItem(item, price);
        register.purchase(item);
        assertEquals(price, register.getTotal(), 0);
    }

}