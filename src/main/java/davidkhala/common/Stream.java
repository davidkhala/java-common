package davidkhala.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stream {
    public static String read(InputStream is) throws IOException {
        int size = is.available();
        byte[] buffer = new byte[size];
        while (true) {
            if (is.read(buffer) < 0) {
                break;
            }
        }

        is.close();
        return new String(buffer);
    }

    public static InputStream from(String string) {
        return new ByteArrayInputStream(string.getBytes());
    }
}
