package org.astri.ICDD.ASIC.secure;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by davidliu on 11/9/2016.
 */

public class RSAKeyPair {
    public static KeyPair get() {
        return get(null);
    }

    public static KeyPair get(@Nullable String provider) {
        try {
            KeyPairGenerator generator = provider == null ? KeyPairGenerator.getInstance("RSA") : KeyPairGenerator.getInstance("RSA", provider);//"BC", "AndroidOpenSSL"
            if (provider == null)
                System.out.println("RSAKeypair generator default provider: " + generator.getProvider());
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RSAPublicKey recoverPubKey(byte[] pubBytes) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubBytes));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RSAPublicKey recoverPubKey(RSAPrivateCrtKey privateCrtKey) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(privateCrtKey.getModulus(), privateCrtKey.getPublicExponent()));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RSAPrivateKey recoverPrivKey(byte[] privateBytes) {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static KeyPair recover(@Nonnull byte[] privateBytes, @Nonnull byte[] pubBytes) {

        RSAPrivateKey privateKey = recoverPrivKey(privateBytes);
        RSAPublicKey publicKey = recoverPubKey(pubBytes);

        return new KeyPair(publicKey, privateKey);
    }
}
