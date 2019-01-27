package com.zimmeren.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Register {

    private float total;
    private Map<String, Float> purchasedAmount;

    public Catalog catalog;

    public Register() {
        total = 0.0f;
        purchasedAmount = new HashMap<>();
        catalog = new Catalog();
    }

    public float getTotal() {
        return total;
    }

    public void purchase(String item) throws NoSuchElementException {
        if (purchasedAmount.containsKey(item)) {
            total -= catalog.getItemsPrice(item, purchasedAmount.get(item));
            purchasedAmount.replace(item, purchasedAmount.get(item) + 1f);
        } else {
            purchasedAmount.put(item, 1f);
        }
        total += catalog.getItemsPrice(item, purchasedAmount.get(item));
    }

    public void remove(String item) throws NoSuchElementException {
        total -= catalog.getItemPrice(item);
    }
}
