package davidkhala.common.secure;

import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyPair {
  public static KeyPair get() {

      try {
          return get(null);
      } catch (NoSuchAlgorithmException |NoSuchProviderException e) {
          return null;
      }

  }

  public static RSAPublicKey recoverPubKey(byte[] pubBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
    return (RSAPublicKey)
        KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubBytes));
  }

  public static RSAPublicKey recoverPubKey(RSAPrivateCrtKey privateCrtKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
      return (RSAPublicKey)
          KeyFactory.getInstance("RSA")
              .generatePublic(
                  new RSAPublicKeySpec(
                      privateCrtKey.getModulus(), privateCrtKey.getPublicExponent()));
  }

  public static KeyPair get(String provider) throws NoSuchAlgorithmException, NoSuchProviderException {

      KeyPairGenerator generator =
          provider == null
              ? KeyPairGenerator.getInstance("RSA")
              : KeyPairGenerator.getInstance("RSA", provider); // "BC", "AndroidOpenSSL"
      if (provider == null)
        System.out.println("RSAKeypair generator default provider: " + generator.getProvider());
      generator.initialize(2048);
      return generator.generateKeyPair();

  }

  public static RSAPrivateKey recoverPrivKey(byte[] privateBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {

      return (RSAPrivateKey)
          KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateBytes));

  }

  public static KeyPair recover(byte[] privateBytes, byte[] pubBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {

    RSAPrivateKey privateKey = recoverPrivKey(privateBytes);
    RSAPublicKey publicKey = recoverPubKey(pubBytes);

    return new KeyPair(publicKey, privateKey);
  }
}
