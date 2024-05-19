package org.davidkhala;

import java.io.IOException;

public class CLI {
  public static void exec(String... command) throws IOException, InterruptedException {
    Process p = Runtime.getRuntime().exec(command);
    p.getInputStream().transferTo(System.out);
    p.getErrorStream().transferTo(System.err);
    p.waitFor();
  }
}
