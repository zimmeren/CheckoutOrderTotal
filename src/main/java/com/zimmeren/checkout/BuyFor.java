package com.zimmeren.checkout;

public class BuyFor extends Special{

    public float buy;
    public float forTotal;
    public float limit;

    private BuyFor(float buy, float forTotal, float limit) {
        this.buy = buy;
        this.forTotal = forTotal;
        this.limit = limit;
    }

    public static BuyFor of(float buy, float forTotal, float limit) {
        return new BuyFor(buy, forTotal, limit);
    }

    public float priceOf(float quantity, float stickerPrice) {
        float price = 0f;
        while (quantity > 0f) {
            if (quantity < buy) {
                price += quantity * stickerPrice;
                break;
            } else {
                price += forTotal;
                quantity -= buy;
            }
        }
        return Utility.roundUpToCent(price);
    }
}