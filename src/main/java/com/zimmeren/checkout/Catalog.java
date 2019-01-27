package com.zimmeren.checkout;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Catalog {
    private Map<String, Float> items;

    public Catalog() {
        items = new HashMap<>();
    }

    public void addItem(String item, float price) {
        items.put(item, price);
    }

    public float getItemPrice(String item) throws NoSuchElementException {
        if (items.containsKey(item)) {
            return items.get(item);
        } else {
            throw new NoSuchElementException("item does not exist in the items");
        }
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public void updateItem(String item, float price) throws NoSuchElementException {
        if (items.containsKey(item)) {
            items.replace(item, price);
        } else {
            throw new NoSuchElementException("item does not exist in the items");
        }
    }
}
