package com.example.practice1.util;

public final class AuthTokenUtils {

    private static final String BEARER_PREFIX = "Bearer ";

    private AuthTokenUtils() {
    }

    public static boolean isValidBearerToken(String bearerToken) {
        return bearerToken != null
                && bearerToken.startsWith(BEARER_PREFIX)
                && bearerToken.length() > BEARER_PREFIX.length();
    }

    public static String parseBearerToken(String bearerToken) {
        return bearerToken.substring(BEARER_PREFIX.length());
    }
}