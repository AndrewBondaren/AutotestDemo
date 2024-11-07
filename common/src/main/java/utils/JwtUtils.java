package ru.beeline.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.*;

public final class JwtUtils {

    private JwtUtils() {
    }

    public static Map<String, String> decodeJwt(final String token) {
        final Map<String, String> value = new HashMap<>();
        final List<String> splitToken = splitToken(token);
        value.put("header", decode(splitToken.get(0)));
        value.put("body", decode(splitToken.get(1)));
        value.put("signature", decode(splitToken.get(2)));
        return value;
    }

    //Header, body, encodedSignature.
    private static List<String> splitToken(final String token) {
        final String[] splitToken = token.split("\\.");
        return Arrays.asList(splitToken[0], splitToken[1], splitToken[2]);
    }

    private static String decode(final String decodeItem) {
        return new String(Base64.getUrlDecoder().decode(decodeItem), StandardCharsets.UTF_8);
    }
}
