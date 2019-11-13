package by.naty.fitnesscenter.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    private static final Logger LOG = LogManager.getLogger();

    private static String MD_5 = "MD5";
    private static String UTF_8 = "utf-8";
    private static String ZERO = "0";

    public static String encrypt(String string) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        StringBuilder result = new StringBuilder();

        try {
            messageDigest = MessageDigest.getInstance(MD_5);
            messageDigest.reset();
            messageDigest.update(string.getBytes(UTF_8));
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOG.error("Algorithm name not passed to encryption in getInstance. ", e);
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        //hash contains 128 bits (16 bytes)
        String temp = bigInteger.toString(16);
        int length = temp.length();
        //usually a hash of 16 bytes is represented as a sequence of 32 hexadecimal digits
        while (length < 32) {
            result.append(ZERO);
            length++;
        }
        result.append(temp);
        return result.toString();
    }
}
