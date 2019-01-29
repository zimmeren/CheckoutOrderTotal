package com.zimmeren.checkout;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void whenThereHaveBeenNoPurchasesThenTotalIsZero() {
        Register register = new Register();
        assertEquals(0.00f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingPurchaseTotalIncreases() {
        Register register = new Register();
        String item = "cookies";
        register.catalog.addItem(item, 3.50f);
        register.purchaseEaches(item);
        assertEquals(3.50f, register.getTotal(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenAddingPurchaseThatDoesNotExistAnExceptionIsThrown() {
        Register register = new Register();
        register.purchaseEaches("fake item");
    }

    @Test
    public void whenRemovingPurchaseTotalDecreases() {
        Register register = new Register();
        String item = "cookies";
        register.catalog.addItem(item, 3.50f);
        register.purchaseEaches(item);
        register.removeEaches(item);
        assertEquals(0.00f, register.getTotal(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingPurchaseThatDoesNotExistAnExceptionIsThrown() {
        Register register = new Register();
        register.removeEaches("fake item");
    }

    @Test
    public void whenPurchasingMarkedDownItemTheTotalPriceUsesTheMarkdownPrice() {
        Register register = new Register();
        String item = "cookies";
        register.catalog.addItem(item, 3.50f);
        register.catalog.setItemMarkdown(item, 0.75f);
        register.purchaseEaches(item);
        assertEquals(2.75f, register.getTotal(), 0);
    }

    @Test
    public void whenPurchasingMultipleOfAnItemTheTotalPriceIsForThatQuantity() {
        Register register = new Register();
        String item = "cookies";
        register.catalog.addItem(item, 3.50f);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        assertEquals(17.50f, register.getTotal(), 0);
    }

    @Test
    public void whenMultipleItemsHaveBeenPurchasedSomeCanBeRemovedAndTheTotalPriceIsForThatQuantity() {
        Register register = new Register();
        String item = "cookies";
        register.catalog.addItem(item, 3.50f);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.removeEaches(item);
        register.removeEaches(item);
        assertEquals(10.50f, register.getTotal(), 0);
    }

    @Test
    public void whenPurchasingAWeightedItemTheTotalPriceIsForThatWeight() {
        Register register = new Register();
        String item = "chicken fingers";
        register.catalog.addItem(item, 2.98f);
        register.purchaseWeighted(item, 0.47f);
        assertEquals(1.40f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingWeightedItemTheTotalPriceReturnsToZero() {
        Register register = new Register();
        String item = "chicken fingers";
        register.catalog.addItem(item, 2.98f);
        register.purchaseWeighted(item, 0.47f);
        register.removeWeighted(item, 0.47f);
        assertEquals(0.00f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingPartialWeightedItemTheTotalPriceReturnsThePartialAmountLeft() {
        Register register = new Register();
        String item = "chicken fingers";
        register.catalog.addItem(item, 2.98f);
        register.purchaseWeighted(item, 0.47f);
        register.removeWeighted(item, 0.12f);
        assertEquals(1.05f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingMoreWeightedItemThanPurchasedItWillProtectItselfAndReturnZero() {
        Register register = new Register();
        String item = "chicken fingers";
        register.catalog.addItem(item,  2.98f);
        register.purchaseWeighted(item, 0.47f);
        register.removeWeighted(item, 0.72f);
        assertEquals(0.00f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyGetToEachesItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "doritos";
        register.catalog.addItem(item, 1.67f);
        register.catalog.setItemSpecial(item, 1f, 1f, 1f);
        register.purchaseEaches(item);
        assertEquals(1.67f, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(1.67f, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(3.34f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyGetWithLimitToEachesItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "doritos";
        register.catalog.addItem(item, 1.67f);
        register.catalog.setItemSpecialWithLimit(item, 1f, 1f, 1f, 1);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        assertEquals(5.01f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyGetToWeightedItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "ground beef";
        register.catalog.addItem(item, 1.49f);
        register.catalog.setItemSpecial(item, 0.5f, 0.25f, 0.25f);
        register.purchaseWeighted(item, 0.49f);
        assertEquals(0.73f, register.getTotal(), 0);
        register.purchaseWeighted(item, 0.52f);
        assertEquals(1.42f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyGetWithLimitToWeightedItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "ground beef";
        register.catalog.addItem(item, 1.49f);
        register.catalog.setItemSpecialWithLimit(item, 0.5f, 0.25f, 0.25f, 1);
        register.purchaseWeighted(item, 0.67f);
        register.purchaseWeighted(item, 0.52f);
        assertEquals(1.68f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyForToEachesItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "doritos";
        register.catalog.addItem(item, 1.67f);
        register.catalog.setItemSpecial(item, 3f, 3f);
        register.purchaseEaches(item);
        assertEquals(1.67f, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(3.34f, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(3.00f, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(4.67f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyForWithLimitToEachesItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "doritos";
        register.catalog.addItem(item, 1.67f);
        register.catalog.setItemSpecialWithLimit(item, 2f, 3f, 1);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        assertEquals(6.34f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyForToWeightedItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "ground beef";
        register.catalog.addItem(item, 1.49f);
        register.catalog.setItemSpecial(item, 2.5f, 3.02f);
        register.purchaseWeighted(item, 2.02f);
        assertEquals(3.01f, register.getTotal(), 0);
        register.purchaseWeighted(item, 1.02f);
        assertEquals(3.83f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyForWithLimitToWeightedItemTheSpecialPriceWillBeInTotalPriceOnceQualified() {
        Register register = new Register();
        String item = "ground beef";
        register.catalog.addItem(item, 1.49f);
        register.catalog.setItemSpecialWithLimit(item, 2.5f, 3.02f, 1);
        register.purchaseWeighted(item, 3f);
        register.purchaseWeighted(item, 3f);
        assertEquals(8.24f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingAPurchasedEachesItemTheBuyForSpecialIsInvalidated() {
        Register register = new Register();
        String item = "chaco taco";
        register.catalog.addItem(item, 0.99f);
        register.catalog.setItemSpecial(item, 3f, 2.49f);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        assertEquals(2.49f, register.getTotal(), 0);
        register.removeEaches(item);
        assertEquals(1.98f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingAPurchasedWeightedItemTheBuyGetSpecialIsInvalidated() {
        Register register = new Register();
        String item = "roasted chicken";
        register.catalog.addItem(item, 3.99f);
        register.catalog.setItemSpecial(item, 1.5f, 0.5f, 1f);
        register.purchaseWeighted(item, 2f);
        assertEquals(5.99f, register.getTotal(), 0);
        register.removeWeighted(item, .75f);
        assertEquals(4.99f, register.getTotal(), 0);
    }

    @Test
    public void whenShoppingForABunchOfDifferentItemsWithMarkdownsAndSpecialsTheCorrectTotalPriceIsCalculated() {
        Register register = new Register();
        register.catalog.addItem("roasted chicken", 3.99f);
        register.catalog.addItem("chaco taco", 0.99f);
        register.catalog.addItem("ground beef", 1.49f);
        register.catalog.addItem("doritos", 1.67f);
        register.catalog.addItem("chicken fingers", 2.98f);
        register.catalog.addItem("cookies", 3.50f);
        register.catalog.setItemMarkdown("doritos", 0.65f);
        register.catalog.setItemSpecial("ground beef", 2f, 2.00f);
        register.catalog.setItemSpecial("chaco taco", 3f, 2.49f);
        register.catalog.setItemSpecialWithLimit("cookies", 2f, 1f, 1f,  1);
        register.purchaseEaches("chaco taco");
        register.purchaseEaches("chaco taco");
        register.purchaseEaches("chaco taco");
        register.purchaseEaches("chaco taco");
        register.purchaseEaches("doritos");
        register.purchaseEaches("doritos");
        register.purchaseWeighted("roasted chicken", 1.81f);
        register.removeWeighted("roasted chicken", 0.13f);
        register.purchaseWeighted("ground beef", 3.02f);
        register.purchaseEaches("cookies");
        register.purchaseEaches("cookies");
        register.purchaseEaches("cookies");
        register.purchaseEaches("cookies");
        register.purchaseEaches("cookies");
        register.purchaseEaches("cookies");
        register.removeEaches("doritos");
        register.purchaseWeighted("chicken fingers", 1f);
        assertEquals(35.21f, register.getTotal(), 0);
    }

}