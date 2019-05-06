package org.hyperledger.secure;

import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.operator.ContentSigner;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.jcajce.JcaContentSignerBuilder;
import org.spongycastle.pkcs.PKCS10CertificationRequest;
import org.spongycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.spongycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import java.io.IOException;
import java.security.KeyPair;

import javax.security.auth.x500.X500Principal;

/**
 * Created by davidliu on 1/23/2017.
 */

public class CSRTool {
	public static String wrapPublicReq(PKCS10CertificationRequest csr) throws IOException {
		byte[] CSRder;
		CSRder = csr.getEncoded();
		String publicreq = new String(Base64.encode(CSRder, Base64.DEFAULT));
		publicreq = "-----BEGIN NEW CERTIFICATE REQUEST-----\n" + publicreq + "-----END NEW CERTIFICATE REQUEST-----";
		return publicreq;
	}

	public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, X500Principal x500Principal) throws IOException,
			OperatorCreationException {
		PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(
				x500Principal, keyPair.getPublic());

		JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
		ContentSigner signer = csBuilder.build(keyPair.getPrivate());
		PKCS10CertificationRequest csr = p10Builder.build(signer);

		return csr;
	}

	public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, X500Name x500Name) throws IOException,
			OperatorCreationException {
		PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(
				x500Name, keyPair.getPublic());

		JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
		ContentSigner signer = csBuilder.build(keyPair.getPrivate());
		PKCS10CertificationRequest csr = p10Builder.build(signer);

		return csr;
	}
}
