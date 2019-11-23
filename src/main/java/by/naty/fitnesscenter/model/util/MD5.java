package by.naty.fitnesscenter.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final Logger LOG = LogManager.getLogger();

    private final static String MD_5 = "MD5";
    private final static String ZERO = "0";
    private final static Integer HASH_SIZE_IN_BYTES = 16;
    private final static Integer NUMBER_OF_HEXADECIMAL_DIGITS = 32;

    public static String encrypt(String string) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        StringBuilder result = new StringBuilder();

        try {
            messageDigest = MessageDigest.getInstance(MD_5);
            messageDigest.reset();
            messageDigest.update(string.getBytes(StandardCharsets.UTF_8));
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Algorithm name not passed to encryption in getInstance. ", e);
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        String temp = bigInteger.toString(HASH_SIZE_IN_BYTES);
        int length = temp.length();
        while (length < NUMBER_OF_HEXADECIMAL_DIGITS) {
            result.append(ZERO);
            length++;
        }
        result.append(temp);
        return result.toString();
    }
}
