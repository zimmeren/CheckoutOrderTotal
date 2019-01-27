package com.zimmeren.checkout;

import java.util.Optional;

public class Item {

    public String name;
    public float price;
    public float markdown;
    public Optional<Special> special;

    private Item(String name, float price) {
        this.name = name;
        this.price = price;
        this.markdown = 0.0f;
        this.special = Optional.empty();
    }

    public static Item of(String name, float price) {
        return new Item(name, price);
    }

    public float calculatePrice() {
        return calculatePriceOf(1);
    }

    public float calculatePriceOf(float quantity) {
        if (special.isPresent()) {
            return special.get().priceOf(quantity, price);
        } else {
            float priceWithMarkdown = (price - markdown) * quantity;
            return Utility.roundUpToCent(priceWithMarkdown);
        }
    }
}
