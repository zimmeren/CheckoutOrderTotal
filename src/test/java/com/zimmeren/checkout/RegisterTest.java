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
    public void whenAddingItemToCatalogItsPriceCanBeFound() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.addItemToCatalog(item, price);
        assertEquals(price, register.getItemPriceFromCatalog(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingItemFromCatalogItsPriceCannotBeFound() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.addItemToCatalog(item, price);
        assertEquals(price, register.getItemPriceFromCatalog(item), 0);
        register.removeItemFromCatalog(item);
        register.getItemPriceFromCatalog(item);
    }

    @Test
    public void whenUpdatingItemInCatalogItsPriceIsUpdated() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.addItemToCatalog(item, price);
        assertEquals(price, register.getItemPriceFromCatalog(item), 0);
        float updatedPrice = 3.00f;
        register.updateItemInCatalog(item, updatedPrice);
        assertEquals(updatedPrice, register.getItemPriceFromCatalog(item), 0);
    }
}