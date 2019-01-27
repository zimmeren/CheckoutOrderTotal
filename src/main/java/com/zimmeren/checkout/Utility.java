package com.zimmeren.checkout;

public class Utility {

    public static float roundUpToCent(float unrounded) {
        return (float)Math.ceil(unrounded * 100f) / 100f;
    }

}
