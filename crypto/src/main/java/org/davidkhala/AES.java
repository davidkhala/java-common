package org.davidkhala;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static byte[] encrypt(byte[] key, byte[] inputValue)
            throws NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec sKeyS = new SecretKeySpec(key, "AES");

        return encrypt(sKeyS, inputValue);
    }

    public static byte[] encrypt(Key key, byte[] inputValue)
            throws NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {


        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(inputValue);
    }

    public static byte[] decrypt(byte[] key, byte[] encryptedData)
            throws NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec sKeyS = new SecretKeySpec(key, "AES");

        return decrypt(sKeyS, encryptedData);
    }

    public static byte[] decrypt(Key key, byte[] encryptedData)
            throws NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {


        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(encryptedData);
    }

    public static Key generateKey(int keysize, @javax.annotation.Nonnull String password) throws NoSuchAlgorithmException {
        return generateKey(keysize, "AES", password);
    }

    public static Key generateKey(int keysize, String algorithm, @javax.annotation.Nonnull String password) throws NoSuchAlgorithmException {
        SecureRandom e = SecureRandom.getInstance("SHA1PRNG");
        byte[] hash = Hash.SHA2_256(password.getBytes(Charset.forName("UTF-8")));
        e.setSeed(hash);
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
        keyGen.init(keysize, e);
        SecretKey secret = keyGen.generateKey();

        SecretKeySpec keySpec = new SecretKeySpec(secret.getEncoded(), algorithm);
        return keySpec;
    }

    public static Key wrapKey(byte[] keyEncoded) {
        return new SecretKeySpec(keyEncoded, "AES");
    }
}
