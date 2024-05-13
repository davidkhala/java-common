package davidkhala.http;

import davidkhala.common.secure.KeyStoreTool;

import javax.net.ssl.*;
import java.security.*;

public abstract class AbstractHTTPS {
	public static SSLSocketFactory getSSLSocketFactory(KeyStore keyStore, String keyStorePwd, KeyStore trustKeyStore) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
		TrustManager[] trustManagers = new KeyStoreTool(trustKeyStore).genTrustedManagers();
		KeyManager[] keyManagers = keyStore == null ? null : new KeyStoreTool(keyStore).getKeyManagers(keyStorePwd);
		return getSSLSocketFactory(keyManagers, trustManagers);
	}


	public static SSLSocketFactory getSSLSocketFactory(KeyManager[] keyManagers, TrustManager[] trustManagers) throws NoSuchAlgorithmException, KeyManagementException {
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
