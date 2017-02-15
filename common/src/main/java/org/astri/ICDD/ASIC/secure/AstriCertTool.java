package org.astri.ICDD.ASIC.secure;

import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.pkcs.PKCS10CertificationRequest;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.x500.X500Principal;

/**
 * Created by davidliu on 11/4/2016.
 */

public class AstriCertTool {

    public final static String CN_PATTERN = "CN=%s, OU=ICDD, O=ASTRI, L=Hong Kong Science Park, ST=Hong Kong, C=HK";
    public static String CN_REGEX = "(?<=CN\\s{0,4}=\\s{0,4})[^,=]+(?!=\\s*,)";


    public static String extractCertificateIdentity(X509Certificate certificate) {
        String identity = null;

        String name = certificate.getSubjectDN().getName();
        System.out.println("Subject DN:" + name);
        Pattern pattern = Pattern.compile(CN_REGEX);
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            identity = matcher.group();
            System.out.println("Subject CN:" + identity);
        } else {
            System.err.println("CN name not found in Subject DN:" + name);
        }

        return identity;
    }

    /**
     * Created by davidliu on 11/8/2016.
     */

    public static class CsrHelper {


        public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, String cn) throws IOException, NoSuchAlgorithmException, OperatorCreationException {

            return CSRTool.generateCSR(keyPair, getX500Principal(cn));
        }


        public static X500Name getX500Name(String cn) {
            return new X500Name(String.format(CN_PATTERN, cn));
        }

        public static X500Principal getX500Principal(String cn) {
            return new X500Principal(String.format(CN_PATTERN, cn));
        }
    }
}
