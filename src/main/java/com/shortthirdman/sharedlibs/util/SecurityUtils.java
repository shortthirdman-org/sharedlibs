package com.shortthirdman.sharedlibs.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtils {

    /**
     * @param input
     * @param algorithm
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param hash
     * @return String
     */
    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    /**
     * @param digest
     * @return String
     */
    public static String toBase64String(byte[] digest) {
        return Base64.getEncoder().encodeToString(digest);
    }
}
