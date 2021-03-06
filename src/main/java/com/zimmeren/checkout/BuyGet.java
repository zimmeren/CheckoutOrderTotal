package com.zimmeren.checkout;

public class BuyGet extends Special{

    public float buy;
    public float get;
    public float off;
    public int limit;

    private BuyGet(float buy, float get, float off, int limit) {
        this.buy = buy;
        this.get = get;
        this.off = off;
        this.limit = limit;
    }

    public static BuyGet of(float buy, float get, float off, int limit) {
        return new BuyGet(buy, get, off, limit);
    }

    public float priceOf(float quantity, float stickerPrice) {
        float price = 0f;
        int rounds = 0;
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
            rounds++;
            if (rounds == limit) {
                price += quantity * stickerPrice;
                break;
            }
        }
        return Utility.roundUpToCent(price);
    }
}
