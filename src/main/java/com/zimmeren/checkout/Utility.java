package com.zimmeren.checkout;

import java.math.BigDecimal;

public class Utility {

    public static float roundUpToCent(float unrounded) {
        //Floating point can cause very small calculation error.
        //to avoid remove decimal values at 4th and back
        //in application this would mean we only round up 10ths of cents else they are rounded down
        //in future us BigDecimal to avoid this completely
        float floatingErrorCorrection = (float)Math.floor(unrounded * 1000f) / 1000f;
        return (float)Math.ceil(floatingErrorCorrection * 100f) / 100f;
    }

}
