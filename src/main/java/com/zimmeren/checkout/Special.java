package com.zimmeren.checkout;

public class Special {

    public float buy;
    public float get;
    public float off;

    private Special(float buy, float get, float off) {
        this.buy = buy;
        this.get = get;
        this.off = off;
    }

    public static Special of(float buy, float get, float off) {
        return new Special(buy, get, off);
    }

    public float priceOf(float quantity, float stickerPrice) {
        return quantity * stickerPrice;
    }
}
