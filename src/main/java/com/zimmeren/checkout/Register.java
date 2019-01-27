package com.zimmeren.checkout;

public class Register {

    private float total;

    public Catalog catalog;

    public Register() {
        total = 0.0f;
        catalog = new Catalog();
    }

    public float getTotal() {
        return total;
    }
}
