package org.hyperledger.secure;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Created by davidliu on 11/9/2016.
 */

public abstract class AbstractHTTPS {
    public static SSLSocketFactory getSSLSocketFactory(@Nullable KeyStore keyStore, @Nullable String keyStorePwd, KeyStore trustKeyStore) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        TrustManager[] trustManagers = new KeyStoreTool(trustKeyStore).genTrustedManagers();
        KeyManager[] keyManagers = keyStore == null ? null : new KeyStoreTool(keyStore).getKeyManagers(keyStorePwd);
        return getSSLSocketFactory(keyManagers,trustManagers);
    }


    public static SSLSocketFactory getSSLSocketFactory(@Nullable KeyManager[] keyManagers,TrustManager[] trustManagers) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagers, trustManagers, new SecureRandom());

        SSLSocketFactory factory = sslContext.getSocketFactory();
        //Android SSLContext:provider: AndroidOpenSSL version 1.0 factory: class: com.android.org.conscrypt.OpenSSLSocketFactoryImpl

        return factory;
    }


    public static HttpsURLConnection setSSLSocketFactory(HttpsURLConnection connection, SSLSocketFactory sslSocketFactory) {
        connection.setSSLSocketFactory(sslSocketFactory);
        connection.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return connection;
    }
}
