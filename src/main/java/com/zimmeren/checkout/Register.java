package com.zimmeren.checkout;

import java.util.NoSuchElementException;

public class Register {

    private float total;

    public Catalog catalog;

    public Register() {
        total = 0.0f;
        catalog = new Catalog();
    }

    public float getTotal() {
        return total;
    }

    public void purchase(String item) throws NoSuchElementException {
        total += catalog.getItemPrice(item);
    }

    public void remove(String item) throws NoSuchElementException {
        total -= catalog.getItemPrice(item);
    }
}
