package davidkhala.common.secure;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * Created by davidliu on 1/23/2017.
 */

public class CertTool {
	static String defaultCertType = "X.509";
	static String defaultProvider = "BC";

	public static Certificate get(byte[] certString) throws CertificateException, IOException, NoSuchProviderException {
		InputStream is = new ByteArrayInputStream(certString);

		return get(is, null, null);
	}

	public static Certificate get(File certFile) throws CertificateException, IOException, NoSuchProviderException {
		InputStream is = new FileInputStream(certFile);

		return get(is, null, null);
	}

	public static Certificate get(InputStream caInput) throws CertificateException, NoSuchProviderException, IOException {
		return get(caInput, null, null);
	}

	public static Certificate get(InputStream caInput, String certType, String certProvider) throws CertificateException, NoSuchProviderException, IOException {

		String type = certType == null ? defaultCertType : certType;
		String provider = certProvider == null ? defaultProvider : certProvider;
		CertificateFactory cf = CertificateFactory.getInstance(type, provider);
		Certificate ca = cf.generateCertificate(caInput);
		caInput.close();
		return ca;
	}
}
