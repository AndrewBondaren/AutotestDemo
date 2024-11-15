package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("PMD")
public final class PasswordUtils {

    private PasswordUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class!");
    }

    public static String userPasswordEncode(final String password) {
        MessageDigest md = null;
        try {
             md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        final byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        final StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

}
