package secure;

import org.davidkhala.common.secure.X509CertTool;
import org.junit.Test;
import org.spongycastle.asn1.x500.X500Name;

import javax.security.auth.x500.X500Principal;
import java.io.IOException;

public class x509Cert {
	@Test
	public void getX500Cert() throws IOException {
		X500Name x500Name = X509CertTool.CsrHelper.getX500Name("david", "mediconcen");
		System.out.println(x500Name.toString());
		X500Principal x500Principal = X509CertTool.CsrHelper.getX500Principal("david");
		System.out.println(x500Principal.toString());
	}
}
