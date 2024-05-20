import org.davidkhala.Hash;
import org.davidkhala.Hex;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class HashTest {
    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        String message = "p4ssw0rd";
        assert Hex.encode(Hash.MD5(message.getBytes())).equals("2a9d119df47ff993b662a8ef36f9ea20");
    }
}
