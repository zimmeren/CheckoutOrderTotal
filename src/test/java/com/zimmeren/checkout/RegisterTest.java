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
        register.purchaseEaches(item);
        assertEquals(price, register.getTotal(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenAddingPurchaseToRegisterThatDoesNotExistAnExceptionIsThrown() {
        Register register = new Register();
        register.purchaseEaches("fake item");
    }

    @Test
    public void whenRemovingPurchaseFromRegisterTotalDecreases() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.catalog.addItem(item, price);
        register.purchaseEaches(item);
        register.removeEaches(item);
        assertEquals(0.0f, register.getTotal(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void whenRemovingPurchaseFromRegisterThatDoesNotExistAnExceptionIsThrown() {
        Register register = new Register();
        register.removeEaches("fake item");
    }

    @Test
    public void whenPurchasingMarkedDownItemOnRegisterTheMarkedDownPriceIsReflected() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        float markdown = 0.75f;
        register.catalog.addItem(item, price);
        register.catalog.setItemMarkdown(item, 0.75f);
        register.purchaseEaches(item);
        assertEquals(price - markdown, register.getTotal(), 0);
    }

    @Test
    public void whenPurchasingMultipleOfAnItemOnRegisterTheTotalPriceIsForThatQuantity() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.catalog.addItem(item, price);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        assertEquals(price * 5, register.getTotal(), 0);
    }

    @Test
    public void whenMultipleItemsHaveBeenPurchasedSomeCanBeRemovedAndOnRegisterTheTotalPriceIsForThatQuantity() {
        Register register = new Register();
        String item = "cookies";
        float price = 3.50f;
        register.catalog.addItem(item, price);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.purchaseEaches(item);
        register.removeEaches(item);
        register.removeEaches(item);
        assertEquals(price * 3, register.getTotal(), 0);
    }

    @Test
    public void whenPurchasingWeightedItemOnRegisterTheTotalPriceIsForThatWeight() {
        Register register = new Register();
        String item = "chicken fingers";
        float price = 2.98f;
        register.catalog.addItem(item, price);
        float weight = 0.47f;
        register.purchaseWeighted(item, weight);
        assertEquals(1.4f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingWeightedItemOnRegisterTheTotalPriceReturnsToZero() {
        Register register = new Register();
        String item = "chicken fingers";
        float price = 2.98f;
        register.catalog.addItem(item, price);
        float weight = 0.47f;
        register.purchaseWeighted(item, weight);
        register.removeWeighted(item, weight);
        assertEquals(0f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingPartialWeightedItemOnRegisterTheTotalPriceReturnsThePartialAmountLeft() {
        Register register = new Register();
        String item = "chicken fingers";
        float price = 2.98f;
        register.catalog.addItem(item, price);
        float weight = 0.47f;
        register.purchaseWeighted(item, weight);
        float removeWeight = 0.12f;
        register.removeWeighted(item, removeWeight);
        assertEquals(1.05f, register.getTotal(), 0);
    }

    @Test
    public void whenRemovingMoreWeightedItemThanPurchasedOnRegisterItWillProtectAndReturnZero() {
        Register register = new Register();
        String item = "chicken fingers";
        float price = 2.98f;
        register.catalog.addItem(item, price);
        float weight = 0.47f;
        register.purchaseWeighted(item, weight);
        float removeWeight = 0.72f;
        register.removeWeighted(item, removeWeight);
        assertEquals(0f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyGetToEachesItemInRegisterTheSpecialPriceWillBeInTotalWhenPurchasedOnceQualified() {
        Register register = new Register();
        String item = "doritos";
        float price = 1.67f;
        register.catalog.addItem(item, price);
        register.catalog.setItemSpecial(item, 1f, 1f, 1f);
        register.purchaseEaches(item);
        assertEquals(price, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(price, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(2*price, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyGetToWeightedItemInRegisterTheSpecialPriceWillBeInTotalWhenPurchasedOnceQualified() {
        Register register = new Register();
        String item = "ground beef";
        float price = 1.49f;
        register.catalog.addItem(item, price);
        register.catalog.setItemSpecial(item, 0.5f, 0.25f, 0.25f);
        register.purchaseWeighted(item, 0.49f);
        assertEquals(.73f, register.getTotal(), 0);
        register.purchaseWeighted(item, 0.52f);
        assertEquals(1.42f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyForToEachesItemInRegisterTheSpecialPriceWillBeInTotalWhenPurchasedOnceQualified() {
        Register register = new Register();
        String item = "doritos";
        float price = 1.67f;
        register.catalog.addItem(item, price);
        register.catalog.setItemSpecial(item, 3f, 3f);
        register.purchaseEaches(item);
        assertEquals(price, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(2*price, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(3f, register.getTotal(), 0);
        register.purchaseEaches(item);
        assertEquals(4.67f, register.getTotal(), 0);
    }

    @Test
    public void whenAddingBuyForToWeightedItemInRegisterTheSpecialPriceWillBeInTotalWhenPurchasedOnceQualified() {
        Register register = new Register();
        String item = "ground beef";
        float price = 1.49f;
        register.catalog.addItem(item, price);
        register.catalog.setItemSpecial(item, 2.5f, 3.02f);
        register.purchaseWeighted(item, 2.02f);
        assertEquals(3.01f, register.getTotal(), 0);
        register.purchaseWeighted(item, 1.02f);
        assertEquals(3.83f, register.getTotal(), 0);
    }

}