package com.zimmeren.checkout;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void whenCreatingNewItemTheNameAndPriceMatchThePassedParameters() {
        String name = "Cookies";
        float price = 3.50f;
        Item cookies = Item.of(name, price);
        assertEquals(name, cookies.name);
        assertEquals(price, cookies.price, 0);
    }

    @Test
    public void whenNoDiscountsAreAppliedCalculateItemPriceReturnsTheRegularPrice() {
        String name = "Cookies";
        float price = 3.50f;
        Item cookies = Item.of(name, price);
        assertEquals(price, cookies.calculatePrice(), 0);
    }

    @Test
    public void whenSettingAMarkdownTheCalculatePriceItemReturnsThePriceMinusMarkdown() {
        String name = "Cookies";
        float price = 3.50f;
        float markdown = 0.75f;
        Item cookies = Item.of(name, price);
        cookies.markdown = markdown;
        assertEquals(price - markdown, cookies.calculatePrice(), 0);
    }

    @Test
    public void whenMultipleQuantityOfAnItemHasItsPriceCalculatedItReturnsTheMultipliedCost() {
        String name = "Cookies";
        float price = 3.50f;
        Item cookies = Item.of(name, price);
        float quantity = 5f;
        assertEquals(price * quantity, cookies.calculatePriceOf(quantity), 0);
    }

    @Test
    public void whenAFractionQuantityOfAnItemHasItsPriceCalculatedItReturnsTheMultipliedCost() {
        String name = "Fish Sticks";
        float price = 2.49f;
        Item fishSticks = Item.of(name, price);
        float quantity = 0.79f;
        assertEquals(1.97f, fishSticks.calculatePriceOf(quantity), 0);
    }

    @Test
    public void whenTryingToAccessAnOptionalSpecialThatDoesNotExistPresentReturnsFalse() {
        String name = "Fish Sticks";
        float price = 2.49f;
        Item fishSticks = Item.of(name, price);
        assertFalse(fishSticks.special.isPresent());
    }

    @Test
    public void whenSpecialAndMarkdownBothExistTheSpecialPriceIsReturned() {
        String name = "Cookies";
        float price = 3.50f;
        float markdown = 0.75f;
        Item cookies = Item.of(name, price);
        cookies.markdown = markdown;
        Special special = BuyGet.of(1f, 1f, 1f, 0f);
        cookies.special = Optional.of(special);
        assertEquals(price, cookies.calculatePriceOf(2), 0);
    }
}