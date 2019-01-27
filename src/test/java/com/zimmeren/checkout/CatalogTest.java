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
        catalog.setItemPrice(item, updatedPrice);
        assertEquals(updatedPrice, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUpdatingNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemPrice("fake item", 0.0f);
    }

    @Test
    public void whenSettingItemMarkdownInCatalogItsPriceIsUpdated() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        float price = 3.50f;
        catalog.addItem(item, price);
        float markdown = 0.75f;
        catalog.setItemMarkdown(item, markdown);
        assertEquals(price - markdown, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingMarkdownOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemMarkdown("fake item", 0.5f);
    }

    @Test
    public void whenGettingPriceOfMultipleOfAnItemTheCatalogReturnsTheMultipliedCost() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        float price = 3.50f;
        catalog.addItem(item, price);
        float quantity = 5;
        assertEquals(price * quantity, catalog.getItemsPrice(item, quantity), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGettingPriceOfMultipleOfAnItemOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.getItemsPrice("fake item", 5);
    }

    @Test
    public void whenSettingNewSpecialForCatalogItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        float price = 2.49f;
        catalog.addItem(item, price);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        assertEquals(price, catalog.getItemsPrice(item, 2), 0);
    }

}