package com.imooc.utils;

public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;
    public static boolean equals(Double d1, Double d2) {
        if (Math.abs(d1 - d2) < MONEY_RANGE)
            return true;
        return false;
    }
}
