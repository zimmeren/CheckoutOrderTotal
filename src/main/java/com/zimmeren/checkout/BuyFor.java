package com.zimmeren.checkout;

public class BuyFor extends Special{

    public float buy;
    public float forTotal;

    private BuyFor(float buy, float forTotal) {
        this.buy = buy;
        this.forTotal = forTotal;
    }

    public static BuyFor of(float buy, float forTotal) {
        return new BuyFor(buy, forTotal);
    }

    public float priceOf(float quantity, float stickerPrice) {
        float price = 0f;
        price = quantity * stickerPrice;
        return Utility.roundUpToCent(price);
    }
}