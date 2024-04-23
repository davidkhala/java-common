package davidkhala.common.secure;

import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.pkcs.PKCS10CertificationRequest;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.x500.X500Principal;

public class X509CertTool {

	public final static String CN_PATTERN = "CN=%s, OU=%s, O=%s, L=%s, ST=%s, C=%s";
	public static String CN_REGEX = "(?<=CN\\s{0,4}=\\s{0,4})[^,=]+(?!=\\s*,)";


	public static String extractSubjectCN(X509Certificate certificate) {
		String identity = null;

		String name = certificate.getSubjectX500Principal().getName();
		Pattern pattern = Pattern.compile(CN_REGEX);
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			identity = matcher.group();
		} else {
			System.err.println("CN name not found in Subject DN:" + name);
		}

		return identity;
	}

	public static String extractIssuerCN(X509Certificate certificate) {
		String identity = null;

		String name = certificate.getIssuerX500Principal().getName();
		Pattern pattern = Pattern.compile(CN_REGEX);
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			identity = matcher.group();
		} else {
			System.err.println("CN name not found in Subject DN:" + name);
		}

		return identity;
	}

	public static class CsrHelper {


		public static PKCS10CertificationRequest generateCSR(KeyPair keyPair, String cn) throws IOException, OperatorCreationException {

			return CSRTool.generateCSR(keyPair, getX500Principal(cn));
		}


		public static X500Name getX500Name(String... cn) {
			ArrayList<String> lists = new ArrayList<>(Arrays.asList(cn));
			while (lists.size() < 6) {
				lists.add("");
			}
			return new X500Name(String.format(CN_PATTERN, lists.toArray()));
		}

		public static X500Principal getX500Principal(String... cn) {
			ArrayList<String> lists = new ArrayList<>(Arrays.asList(cn));
			while (lists.size() < 6) {
				lists.add("");
			}
			return new X500Principal(String.format(CN_PATTERN, lists.toArray()));
		}
	}
}
