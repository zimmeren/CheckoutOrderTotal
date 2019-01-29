package com.zimmeren.checkout;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class CatalogTest {

    @Test
    public void whenAddingItemItsPriceCanBeFound() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        catalog.addItem(item, 3.50f);
        assertEquals(3.50f, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingItemItsPriceCannotBeFound() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        catalog.addItem(item, 3.50f);
        catalog.removeItem(item);
        catalog.getItemPrice(item);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.removeItem("fake item");
    }

    @Test
    public void whenUpdatingItemItsPriceIsUpdated() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        catalog.addItem(item, 3.50f);
        catalog.setItemPrice(item, 3.00f);
        assertEquals(3.00f, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenUpdatingNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemPrice("fake item", 0.00f);
    }

    @Test
    public void whenSettingItemMarkdownItsPriceIsUpdated() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        catalog.addItem(item, 3.50f);
        float markdown = 0.75f;
        catalog.setItemMarkdown(item, markdown);
        assertEquals(2.75f, catalog.getItemPrice(item), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingMarkdownOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemMarkdown("fake item", 0.50f);
    }

    @Test
    public void whenGettingPriceOfMultipleOfAnItemReturnsTheMultipliedCost() {
        Catalog catalog = new Catalog();
        String item = "cookies";
        catalog.addItem(item, 3.50f);
        assertEquals(17.50f, catalog.getItemsPrice(item, 5f), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGettingPriceOfMultipleOfAnItemOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.getItemsPrice("fake item", 5f);
    }

    @Test
    public void whenSettingNewBuyGetSpecialForItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        catalog.addItem(item, 2.49f);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        assertEquals(2.49f, catalog.getItemsPrice(item, 2f), 0);
    }

    @Test
    public void whenSettingNewBuyGetWithLimitSpecialForItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        catalog.addItem(item, 2.49f);
        catalog.setItemSpecialWithLimit(item, 1f, 1f, 1f, 1);
        assertEquals(7.47f, catalog.getItemsPrice(item, 4f), 0);
    }

    @Test
    public void whenSettingNewBuyForSpecialForItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "mozzi sticks";
        catalog.addItem(item, 5.99f);
        catalog.setItemSpecial(item, 3f, 10.99f);
        assertEquals(21.98f, catalog.getItemsPrice(item, 6f), 0);
    }

    @Test
    public void whenSettingNewBuyForWithLimitSpecialForItemThePriceReflectsSpecial() {
        Catalog catalog = new Catalog();
        String item = "mozzi sticks";
        catalog.addItem(item, 5.99f);
        catalog.setItemSpecialWithLimit(item, 3f, 10.99f, 1);
        assertEquals(28.96f, catalog.getItemsPrice(item, 6f), 0);
    }

    @Test
    public void whenUpdatingASpecialOnAnItemThePriceReflectsNewSpecial() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        catalog.addItem(item, 2.49f);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        catalog.setItemSpecial(item, 1f, 1f, 0.5f);
        assertEquals(3.74f, catalog.getItemsPrice(item, 2f), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyGetSpecialOfAnItemOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecial("fake item", 1f, 1f, 1f);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyForSpecialOfAnItemOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecial("fake item", 1f, 1f);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyGetSpecialWithLimitOfAnItemOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecialWithLimit("fake item", 1f, 1f, 1f, 1);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSettingBuyForSpecialWithLimitOfAnItemOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.setItemSpecialWithLimit("fake item", 1f, 1f, 1);
    }

    @Test
    public void whenRemovingASpecialFromItemThePriceReflectsRegularPrice() {
        Catalog catalog = new Catalog();
        String item = "fish sticks";
        catalog.addItem(item, 2.49f);
        catalog.setItemSpecial(item, 1f, 1f, 1f);
        catalog.removeItemSpecial(item);
        assertEquals(4.98f, catalog.getItemsPrice(item, 2f), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingSpecialOfAnItemOnNonExistentItemExceptionIsThrown() {
        Catalog catalog = new Catalog();
        catalog.removeItemSpecial("fake item");
    }

}