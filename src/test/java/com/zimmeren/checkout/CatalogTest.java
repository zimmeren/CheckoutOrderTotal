package com.zimmeren.checkout;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CatalogTest {

    @Test
    public void whenAddingItemToCatalogItsPriceCanBeFound() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        float price = 3.50f;
        catalog.addItem(item, price);
        assertEquals(price, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingItemFromCatalogItsPriceCannotBeFound() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        float price = 3.50f;
        catalog.addItem(item, price);
        assertEquals(price, catalog.getItemPrice(item), 0);
        catalog.removeItem(item);
        catalog.getItemPrice(item);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.removeItem("fake item");
    }

    @Test
    public void whenUpdatingItemInCatalogItsPriceIsUpdated() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        float price = 3.50f;
        catalog.addItem(item, price);
        assertEquals(price, catalog.getItemPrice(item), 0);
        float updatedPrice = 3.00f;
        catalog.updateItem(item, updatedPrice);
        assertEquals(updatedPrice, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUpdatingNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.updateItem("fake item", 0.0f);
    }

}