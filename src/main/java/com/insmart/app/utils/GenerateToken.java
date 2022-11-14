package com.insmart.app.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateToken {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateNewToken(int characterLength) {
        byte[] randomBytes = new byte[characterLength];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
