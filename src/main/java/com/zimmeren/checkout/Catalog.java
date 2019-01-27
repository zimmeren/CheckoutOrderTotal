package com.zimmeren.checkout;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Catalog {
    private Map<String, Item> items;

    public Catalog() {
        items = new HashMap<>();
    }

    public void addItem(String name, float price) {
        Item item = Item.of(name, price);
        items.put(name, item);
    }

    public float getItemPrice(String name) throws NoSuchElementException {
        if (items.containsKey(name)) {
            return items.get(name).calculatePrice();
        } else {
            throw new NoSuchElementException("item does not exist in the items");
        }
    }

    public void removeItem(String name) throws NoSuchElementException {
        if (items.containsKey(name)) {
            items.remove(name);
        } else {
            throw new NoSuchElementException("item does not exist in the items");
        }
    }

    public void setItemPrice(String name, float price) throws NoSuchElementException {
        if (items.containsKey(name)) {
            items.get(name).price = price;
        } else {
            throw new NoSuchElementException("item does not exist in the items");
        }
    }

    public void setItemMarkdown(String name, float markdown) throws NoSuchElementException {
        if (items.containsKey(name)) {
            items.get(name).markdown = markdown;
        } else {
            throw new NoSuchElementException("item does not exist in the items");
        }
    }
}
