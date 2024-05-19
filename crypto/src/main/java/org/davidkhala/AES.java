package org.davidkhala;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
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
    public static byte[] hash(byte[] data) {
        try {
            MessageDigest e = MessageDigest.getInstance("SHA-256");
            e.update(data);
            return e.digest();
        } catch (NoSuchAlgorithmException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(byte[] key, byte[] inputValue)
            throws NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec sKeyS = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, sKeyS);

        return cipher.doFinal(inputValue);
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

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, sKeyS);

        return cipher.doFinal(encryptedData);
    }

    public static byte[] decrypt(Key key, byte[] encryptedData)
            throws NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {


        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(encryptedData);
    }

    public static Key generateKey(int keysize,  String password)
    {
        return generateKey(keysize,"AES",password);
    }
    public static Key wrapKey(byte[] keyEncoded)
    {
        return new SecretKeySpec(keyEncoded, "AES");
    }

    public static Key generateKey(int keysize, String algorithm, String password) {
        if(password != null) {
            try {
                SecureRandom e = SecureRandom.getInstance("SHA1PRNG");
                byte[] hash = hash(password.getBytes(Charset.forName("UTF-8")));
                e.setSeed(hash);
                KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
                keyGen.init(keysize, e);
                SecretKey secret = keyGen.generateKey();

                SecretKeySpec keySpec = new SecretKeySpec(secret.getEncoded(), algorithm);
                return keySpec;
            } catch (NoSuchAlgorithmException var7) {
                var7.printStackTrace();
            }
        }

        return null;
    }
}
