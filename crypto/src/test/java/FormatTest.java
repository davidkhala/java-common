import org.davidkhala.Hash;
import org.davidkhala.Hex;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class FormatTest {
    @Test
    void testMD5() throws NoSuchAlgorithmException {
        String message = "p4ssw0rd";
        assertEquals("2a9d119df47ff993b662a8ef36f9ea20", Hex.encode(Hash.MD5(message.getBytes())));
    }

    @Test
    void testHex() {
        String message = "p4ssw0rd";
        assertEquals(message, Hex.decode(Hex.encode(message.getBytes())));
    }
}
