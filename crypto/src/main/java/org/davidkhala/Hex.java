package org.davidkhala;

public class Hex {
    public static String encode(byte[] bytes) {
        return new String(org.spongycastle.util.encoders.Hex.encode(bytes));
    }

    public static String decode(String string) {
        return new String(org.spongycastle.util.encoders.Hex.decode(string));
    }
}
