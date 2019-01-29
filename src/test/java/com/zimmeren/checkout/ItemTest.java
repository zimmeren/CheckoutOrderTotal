package com.zimmeren.checkout;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void whenCreatingNewItemTheNameAndPriceMatchThePassedParameters() {
        String name = "Cookies";
        Item cookies = Item.of(name, 3.50f);
        assertEquals(name, cookies.name);
        assertEquals(3.50f, cookies.price, 0);
    }

    @Test
    public void whenNoDiscountsAreAppliedCalculatePriceReturnsTheRegularPrice() {
        String name = "Cookies";
        Item cookies = Item.of(name, 3.50f);
        assertEquals(3.50f, cookies.calculatePrice(), 0);
    }

    @Test
    public void whenSettingAMarkdownTheCalculatePriceReturnsThePriceMinusMarkdown() {
        String name = "Cookies";
        Item cookies = Item.of(name, 3.50f);
        cookies.markdown = 0.75f;
        assertEquals(2.75f, cookies.calculatePrice(), 0);
    }

    @Test
    public void whenMultipleQuantityOfAnItemHasItsPriceCalculatedItReturnsTheMultipliedCost() {
        String name = "Cookies";
        Item cookies = Item.of(name, 3.50f);
        assertEquals(17.50f, cookies.calculatePriceOf(5f), 0);
    }

    @Test
    public void whenAFractionalQuantityOfAnItemHasItsPriceCalculatedItReturnsTheMultipliedCost() {
        String name = "Fish Sticks";
        Item fishSticks = Item.of(name, 2.49f);
        assertEquals(1.97f, fishSticks.calculatePriceOf(0.79f), 0);
    }

    @Test
    public void whenTryingToAccessAnOptionalSpecialThatDoesNotExistIsPresentReturnsFalse() {
        String name = "Fish Sticks";
        Item fishSticks = Item.of(name, 2.49f);
        assertFalse(fishSticks.special.isPresent());
    }

    @Test
    public void whenSpecialAndMarkdownBothExistTheSpecialPriceIsReturned() {
        String name = "Cookies";
        Item cookies = Item.of(name, 3.50f);
        cookies.markdown = 0.75f;
        Special special = BuyGet.of(1f, 1f, 1f, 0);
        cookies.special = Optional.of(special);
        assertEquals(3.50f, cookies.calculatePriceOf(2f), 0);
    }
}