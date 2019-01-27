package com.zimmeren.checkout;

import java.util.Map;
import java.util.HashMap;

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

    public float getItemPriceFromCatalog(String item) {
        return catalog.get(item);
    }
}
