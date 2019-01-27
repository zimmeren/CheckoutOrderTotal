package com.zimmeren.checkout;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Register {

    private float total;
    private Map<String, Float> catalog;

    public Register() {
        total = 0.0f;
        catalog = new HashMap<>();
    }

    public float getTotal() {
        return total;
    }

    public void addItemToCatalog(String item, float price) {
        catalog.put(item, price);
    }

    public float getItemPriceFromCatalog(String item) throws NoSuchElementException {
        if (catalog.containsKey(item)) {
            return catalog.get(item);
        } else {
            throw new NoSuchElementException("item does not exist in the catalog");
        }
    }

    public void removeItemFromCatalog(String item) {
        catalog.remove(item);
    }

    public void updateItemInCatalog(String item, float price) throws NoSuchElementException {
        if (catalog.containsKey(item)) {
            catalog.replace(item, price);
        } else {
            throw new NoSuchElementException("item does not exist in the catalog");
        }
    }
}
