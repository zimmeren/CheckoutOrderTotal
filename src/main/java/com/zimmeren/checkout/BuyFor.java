package com.zimmeren.checkout;

public class BuyFor extends Special{

    public float buy;
    public float forTotal;
    public int limit;

    private BuyFor(float buy, float forTotal, int limit) {
        this.buy = buy;
        this.forTotal = forTotal;
        this.limit = limit;
    }

    public static BuyFor of(float buy, float forTotal, int limit) {
        return new BuyFor(buy, forTotal, limit);
    }

    public float priceOf(float quantity, float stickerPrice) {
        float price = 0f;
        int rounds = 0;
        while (quantity > 0f) {
            if (quantity < buy) {
                price += quantity * stickerPrice;
                break;
            } else {
                price += forTotal;
                quantity -= buy;
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