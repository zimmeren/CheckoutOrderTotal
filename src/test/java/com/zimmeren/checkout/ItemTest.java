package com.zimmeren.checkout;

import org.junit.Test;

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

}