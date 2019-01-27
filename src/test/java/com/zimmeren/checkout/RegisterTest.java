package com.zimmeren.checkout;

import org.junit.Test;

import java.util.NoSuchElementException;

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

    @Test (expected = NoSuchElementException.class)
    public void whenAddingPurchaseToRegisterThatDoesNotExistAnExceptionIsThrown() {
        Register register = new Register();
        register.purchase("fake item");
    }

    @Test
    public void whenRemovingPurchaseFromRegisterTotalDecreases() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.catalog.addItem(item, price);
        register.purchase(item);
        register.remove(item);
        assertEquals(0.0f, register.getTotal(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingPurchaseFromRegisterThatDoesNotExistAnExceptionIsThrown() {
        Register register = new Register();
        register.remove("fake item");
    }

}