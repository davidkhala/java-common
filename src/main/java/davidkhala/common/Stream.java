package davidkhala.common;

import java.io.*;

public class Stream {
  public interface InputCallback {
    void onData(int _byte);
  }

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

  public static void read(InputStream is, InputCallback callback) throws IOException {
    while (true) {
      int b = is.read();
      if (b == -1) {
        break;
      }
      callback.onData(b);
    }
    is.close();
  }

  public static InputStream from(String string) {
    return new ByteArrayInputStream(string.getBytes());
  }
}
