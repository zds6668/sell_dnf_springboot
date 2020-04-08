package com.imooc.utils;

import java.util.Random;

public class KeyUtil {
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 100000) + System.currentTimeMillis();
    }
}
