package com.poll.utils;

import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random();

    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(RANDOM.nextInt(10));
        }
        return code.toString();
    }
}
