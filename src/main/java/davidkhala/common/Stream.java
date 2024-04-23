package davidkhala.common;

import java.io.IOException;
import java.io.InputStream;

public class Stream {
    public static String read(InputStream is) throws IOException {
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer);
    }
}
