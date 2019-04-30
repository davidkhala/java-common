package org.hyperledger.secure;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by davidliu on 11/4/2016.
 */

public class KeyStoreTool {

    KeyStore keyStore;

    public KeyStoreTool(KeyStore keyStore) {
        this.keyStore = keyStore;
    }


    public KeyManager[] getKeyManagers(@Nullable String keyStorePwd) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {

        String algo= KeyManagerFactory.getDefaultAlgorithm();//Android:"PKIX",Java:"SunX509"
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(algo);
        keyManagerFactory.init(keyStore,keyStorePwd==null?null:keyStorePwd.toCharArray());
        KeyManager[] result = keyManagerFactory.getKeyManagers();//Android:"com.android.org.conscrypt.KeyManagerImpl"
        assert result.length ==1;

        return result;
    }

    public TrustManager[] genTrustedManagers() throws NoSuchAlgorithmException, KeyStoreException {
        String algo = TrustManagerFactory.getDefaultAlgorithm();//Android:"PKIX", Java: "SunX509"
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(algo);
        tmf.init(keyStore);
        TrustManager[] trustManagers = tmf.getTrustManagers();//length == 1, Android: com.android.org.conscrypt.TrustManagerImpl
        return trustManagers;
    }


    public Map<String, KeyStore.Entry> aliases(boolean printLog) throws KeyStoreException {


        Map<String, KeyStore.Entry> keyAliases = new HashMap<>();

        Enumeration<String> aliases = keyStore.aliases();
        if(printLog)System.out.println("ALIAS:" + "size:" + keyStore.size());
        while (aliases.hasMoreElements()) {
            String aliasString = aliases.nextElement();
            try {
                if(printLog)System.out.println("ALIAS:" + aliasString);
                KeyStore.Entry entry = keyStore.getEntry(aliasString, null);
                if(printLog)System.out.println("ALIAS entry:" + " -->" + entry);
                keyAliases.put(aliasString, entry);

            } catch (NoSuchAlgorithmException | UnrecoverableEntryException e) {
                e.printStackTrace();
            }
        }
        return keyAliases;

    }

    public void clear() throws KeyStoreException {
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String aliasString = aliases.nextElement();
            System.out.println("ALIAS: delete " + aliasString);
            keyStore.deleteEntry(aliasString);

        }
    }
}
