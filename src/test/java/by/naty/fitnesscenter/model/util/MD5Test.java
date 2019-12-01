package by.naty.fitnesscenter.model.util;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.testng.Assert.assertEquals;

public class MD5Test {

    private final static String string = "administrator";
    private final static String MD_5 = "MD5";
    private final static String ZERO = "0";
    private final static Integer HASH_SIZE_IN_BYTES = 16;
    private final static Integer NUMBER_OF_HEXADECIMAL_DIGITS = 32;

    @Test
    public void testEncrypt() {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        StringBuilder actualPassword = new StringBuilder();

        try {
            messageDigest = MessageDigest.getInstance(MD_5);
            messageDigest.reset();
            messageDigest.update(string.getBytes(StandardCharsets.UTF_8));
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        String temp = bigInteger.toString(HASH_SIZE_IN_BYTES);
        int length = temp.length();
        while (length < NUMBER_OF_HEXADECIMAL_DIGITS) {
            actualPassword.append(ZERO);
            length++;
        }
        actualPassword.append(temp);

        StringBuilder expectedPassword = new StringBuilder("200ceb26807d6bf99fd6f4f0d1ca54d4");
        assertEquals(actualPassword, expectedPassword);
    }
}