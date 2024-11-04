package com.poll.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashUtils {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encodePassword(String rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
