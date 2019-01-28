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
    public void whenSettingNewBuyGetSpecialForCatalogItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        float price = 2.49f;
        catalog.addItem(item, price);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        assertEquals(price, catalog.getItemsPrice(item, 2), 0);
    }

    @Test
    public void whenSettingNewBuyForSpecialForCatalogItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "mozzi sticks";
        float price = 5.99f;
        catalog.addItem(item, price);
        catalog.setItemSpecial(item, 3f, 10.99f);
        assertEquals(21.98f, catalog.getItemsPrice(item, 6f), 0);
    }

    @Test
    public void whenUpdatingASpecialFromCatalogItemThePriceReflectsNewSpecial() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        float price = 2.49f;
        catalog.addItem(item, price);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        catalog.setItemSpecial(item, 1f, 1f, 0.5f);
        assertEquals(3.74f, catalog.getItemsPrice(item, 2), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyGetSpecialOfAnItemOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecial("fake item", 1f, 1f, 1f);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyForSpecialOfAnItemOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecial("fake item", 1f, 1f);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyGetSpecialWithLimitOfAnItemOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecialWithLimit("fake item", 1f, 1f, 1f, 1f);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyForSpecialWithLimitOfAnItemOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecialWithLimit("fake item", 1f, 1f, 1);
    }

    @Test
    public void whenRemovingASpecialFromCatalogItemThePriceReflectsRegularPrice() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        float price = 2.49f;
        catalog.addItem(item, price);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        catalog.removeItemSpecial(item);
        assertEquals(4.98f, catalog.getItemsPrice(item, 2), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingSpecialOfAnItemOnNonExistentObjectFromCatalogExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.removeItemSpecial("fake item");
    }

}