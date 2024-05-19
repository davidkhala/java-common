package org.davidkhala;

import com.google.common.io.BaseEncoding;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Format {
    /**
     * Hex encoding used throughout the framework. Use with HEX.encode(byte[]) or HEX.decode(CharSequence).
     * reference from org.bitcoinj.core.Utils#HEX
     */
    public static BaseEncoding HEX = BaseEncoding.base16().lowerCase();

    public static abstract class DATE {
        abstract String getPattern();

        public String getString(java.util.Date date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(getPattern());
            return dateFormat.format(date);
        }

        public java.util.Date formatDate(String dateString) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat(getPattern());
            return dateFormat.parse(dateString);
        }
    }
}
