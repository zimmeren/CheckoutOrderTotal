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

}