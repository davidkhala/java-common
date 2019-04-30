package org.hyperledger.secure;

import com.google.common.io.BaseEncoding;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by davidliu on 2/1/2017.
 */

public class Utils {
    /**
     * Hex encoding used throughout the framework. Use with HEX.encode(byte[]) or HEX.decode(CharSequence).
     * reference from org.bitcoinj.core.Utils#HEX
     */
    public static BaseEncoding HEX = BaseEncoding.base16().lowerCase();
    public static class DATE {
        public static String getString(java.util.Date date) {
            String formatPattern = "dd-MM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
            return dateFormat.format(date);
        }

        public static java.util.Date formatDate(String dateString) throws ParseException {
            //"31-08-2016"
            String formatPattern = "dd-MM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
            return dateFormat.parse(dateString);
        }
    }
}
