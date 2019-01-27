package com.zimmeren.checkout;

public class Item {

    public String name;
    public float price;
    public float markdown;

    private Item(String name, float price) {
        this.name = name;
        this.price = price;
        this.markdown = 0.0f;
    }

    public static Item of(String name, float price) {
        return new Item(name, price);
    }

    public float calculatePrice() {
        return calculatePriceOf(1);
    }

    public float calculatePriceOf(float quantity) {
        return (price - markdown) * quantity;
    }
}
