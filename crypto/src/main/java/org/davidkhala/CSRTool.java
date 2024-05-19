package org.davidkhala;

import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.operator.ContentSigner;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.jcajce.JcaContentSignerBuilder;
import org.spongycastle.pkcs.PKCS10CertificationRequest;
import org.spongycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.spongycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Base64;

import javax.security.auth.x500.X500Principal;

public class CSRTool {
  public static String wrapPublicReq(PKCS10CertificationRequest csr) throws IOException {
    byte[] CSRder;
    CSRder = csr.getEncoded();

    String publicreq = Base64.getEncoder().encodeToString(CSRder);
    publicreq =
        "-----BEGIN NEW CERTIFICATE REQUEST-----\n"
            + publicreq
            + "-----END NEW CERTIFICATE REQUEST-----";
    return publicreq;
  }

  public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, X500Principal x500Principal)
      throws OperatorCreationException {
    PKCS10CertificationRequestBuilder p10Builder =
        new JcaPKCS10CertificationRequestBuilder(x500Principal, keyPair.getPublic());

    JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
    ContentSigner signer = csBuilder.build(keyPair.getPrivate());

      return p10Builder.build(signer);
  }

  public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, X500Name x500Name)
      throws OperatorCreationException {
    PKCS10CertificationRequestBuilder p10Builder =
        new JcaPKCS10CertificationRequestBuilder(x500Name, keyPair.getPublic());

    JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
    ContentSigner signer = csBuilder.build(keyPair.getPrivate());

    return p10Builder.build(signer);
  }
}
