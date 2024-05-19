import org.davidkhala.X509CertTool;
import javax.security.auth.x500.X500Principal;

import org.junit.jupiter.api.Test;
import org.spongycastle.asn1.x500.X500Name;

public class X509CertTest {
	@Test
	public void getX500Cert()  {
		X500Name x500Name = X509CertTool.CsrHelper.getX500Name("david", "mediconcen");
		assert x500Name.toString().equals("CN=david,OU=mediconcen,O=,L=,ST=,C=");

		X500Principal x500Principal = X509CertTool.CsrHelper.getX500Principal("david");
		assert x500Principal.toString().equals("CN=david, OU=, O=, L=, ST=, C=");
	}
}
