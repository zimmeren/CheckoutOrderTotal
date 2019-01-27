package com.zimmeren.checkout;

public class Item {

    public String name;
    public float price;

    private Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public static Item of(String name, float price) {
        return new Item(name, price);
    }
}
