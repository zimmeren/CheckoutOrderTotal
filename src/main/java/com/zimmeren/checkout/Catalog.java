package com.zimmeren.checkout;

import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        checkItemExists(name);
        return items.get(name).calculatePrice();
    }

    public float getItemsPrice(String name, float quantity) throws NoSuchElementException {
        checkItemExists(name);
        return items.get(name).calculatePriceOf(quantity);
    }

    public void removeItem(String name) throws NoSuchElementException {
        checkItemExists(name);
        items.remove(name);
    }

    public void setItemPrice(String name, float price) throws NoSuchElementException {
        checkItemExists(name);
        items.get(name).price = price;
    }

    public void setItemMarkdown(String name, float markdown) throws NoSuchElementException {
        checkItemExists(name);
        items.get(name).markdown = markdown;
    }

    public void setItemSpecial(String name, float buy, float get, float off) throws NoSuchElementException {
        checkItemExists(name);
        Special special = BuyGet.of(buy, get, off);
        items.get(name).special = Optional.of(special);
    }

    public void setItemSpecial(String name, float buy, float forTotal) throws NoSuchElementException {
        checkItemExists(name);
        Special special = BuyFor.of(buy, forTotal);
        items.get(name).special = Optional.of(special);
    }

    public void removeItemSpecial(String name) throws NoSuchElementException {
        checkItemExists(name);
        items.get(name).special = Optional.empty();
    }

    private void checkItemExists(String name) throws NoSuchElementException {
        if (!items.containsKey(name)) {
            throw new NoSuchElementException("item does not exist in the catalog");
        }
    }
}
