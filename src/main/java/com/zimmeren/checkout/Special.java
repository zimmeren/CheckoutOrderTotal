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
        while (quantity > 0) {
            if (quantity - buy > 0) {
                price += buy * stickerPrice;
                quantity -= buy;
            } else {
                price += quantity * stickerPrice;
                break;
            }
            if (quantity - get > 0) {
                price += get * (stickerPrice - (off * stickerPrice));
                quantity -= get;
            } else {
                price += quantity * (stickerPrice - (off * stickerPrice));
                break;
            }
        }
        return Utility.roundUpToCent(price);
    }
}
