package org.davidkhala;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class KeyStoreTool {

    KeyStore keyStore;

    public KeyStoreTool(KeyStore keyStore) {
        this.keyStore = keyStore;
    }


    public KeyManager[] getKeyManagers(String keyStorePwd) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {

        String algo = KeyManagerFactory.getDefaultAlgorithm();//Android:"PKIX",Java:"SunX509"
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(algo);
        keyManagerFactory.init(keyStore, keyStorePwd == null ? null : keyStorePwd.toCharArray());
        KeyManager[] result = keyManagerFactory.getKeyManagers();//Android:"com.android.org.conscrypt.KeyManagerImpl"
        assert result.length == 1;

        return result;
    }

    public TrustManager[] genTrustedManagers() throws NoSuchAlgorithmException, KeyStoreException {
        String algo = TrustManagerFactory.getDefaultAlgorithm();//Android:"PKIX", Java: "SunX509"
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(algo);
        tmf.init(keyStore);
        return tmf.getTrustManagers();
    }


    public Map<String, KeyStore.Entry> aliases() throws KeyStoreException, UnrecoverableEntryException, NoSuchAlgorithmException {


        Map<String, KeyStore.Entry> keyAliases = new HashMap<>();

        Enumeration<String> aliases = keyStore.aliases();

        while (aliases.hasMoreElements()) {
            String aliasString = aliases.nextElement();
            KeyStore.Entry entry = keyStore.getEntry(aliasString, null);
            keyAliases.put(aliasString, entry);
        }
        return keyAliases;

    }

    public void clear() throws KeyStoreException {
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String aliasString = aliases.nextElement();
            keyStore.deleteEntry(aliasString);

        }
    }
}
