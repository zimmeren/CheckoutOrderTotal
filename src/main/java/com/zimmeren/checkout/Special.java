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
        float price = 0f;
        if (quantity - buy > 0) {
            price += buy * stickerPrice;
            quantity -= buy;
        } else {
            price += quantity * stickerPrice;
            return price;
        }
        if (quantity - get > 0) {
            price += get * (stickerPrice - (off * stickerPrice));
        } else {
            price += quantity * (stickerPrice - (off * stickerPrice));
        }
        return price;
    }
}
