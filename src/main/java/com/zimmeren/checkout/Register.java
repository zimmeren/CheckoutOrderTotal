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

    public void purchaseEaches(String item) throws NoSuchElementException {
        purchase(item, 1f);
    }

    public void purchaseWeighted(String item, float weight) {
        purchase(item, weight);
    }

    private void purchase(String item, float quantity) {
        if (purchasedAmount.containsKey(item)) {
            total -= catalog.getItemsPrice(item, purchasedAmount.get(item));
            purchasedAmount.replace(item, purchasedAmount.get(item) + quantity);
        } else {
            purchasedAmount.put(item, quantity);
        }
        total += catalog.getItemsPrice(item, purchasedAmount.get(item));
    }

    public void removeEaches(String item) throws NoSuchElementException {
        remove(item, 1f);
    }

    public void removeWeighted(String item, float weight) throws NoSuchElementException {
        remove(item, weight);
    }

    private void remove(String item, float quantity) throws NoSuchElementException {
        if (!purchasedAmount.containsKey(item)) {
            throw new NoSuchElementException("item does not exist in the purchased items so it cannot be removed");
        }
        total -= catalog.getItemsPrice(item, purchasedAmount.get(item));
        if (purchasedAmount.get(item) - quantity > 0f) {
            purchasedAmount.replace(item, purchasedAmount.get(item) - quantity);
            total += catalog.getItemsPrice(item, purchasedAmount.get(item));
        } else {
            purchasedAmount.remove(item);
        }
    }
}
