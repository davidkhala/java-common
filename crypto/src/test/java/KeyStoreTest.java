import org.davidkhala.KeyStoreTool;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public class KeyStoreTest {
    @Test
    protected void sanCheck() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableEntryException {

        String type = KeyStore.getDefaultType();
        assert type.equals("pkcs12");
        KeyStore keystore = KeyStore.getInstance(type);
        String password = "password";
        keystore.load(null, password.toCharArray());
        KeyStoreTool keyStoreTool = new KeyStoreTool(keystore);
        System.out.println(keyStoreTool.aliases());
    }
}
