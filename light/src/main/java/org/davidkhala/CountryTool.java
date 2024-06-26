package org.davidkhala;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryTool {

    public static ArrayList<String> getNames() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (!country.isEmpty() && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        return countries;
    }
    public static boolean isHKIDValid(String hkid) {
        return trimHKID(hkid,false)!=null;
    }
    public static String trimBrackets(String string){
        return string.replaceAll("[()]","");
    }
    public static String trimHKID(String hkid,boolean isStrict){
        String HKID = hkid.toUpperCase();
        int length = HKID.length();
        if(isStrict){
            if (HKID.length() < 10||HKID.length()>11) return null;
        }else {
            if (length < 8||length>11) return null;
        }
        if (HKID.charAt(length - 3) == '(' && HKID.charAt(length - 1) == ')') {
            HKID = HKID.substring(0, length - 3) + HKID.charAt(length - 2);
        }else {
            if(isStrict)return  null;
        }

        String pattern = "^([A-Z{1,2}])([0-9]{6})([A0-9])$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(HKID);


        if (!m.find()) return null;
        String prefix = m.group(1);
        String serial = m.group(2);
        String checkDigit = m.group(3);
        long value = 0;
        if (prefix.length() == 2) {
            value += (prefix.charAt(0) - 55) * 9 + (prefix.charAt(1) - 55) * 8;
        } else if (prefix.length() == 1) {
            value += 36 * 9 + (prefix.charAt(0) - 55) * 8;
        }
            for (int i = 0; i < 6; i++) {
                value += (long) Integer.parseInt(serial.substring(i, i + 1)) * (7 - i);
            }
            long reminder = value % 11;
            long valid_checkDigit = 11 - reminder;
            if ("A".equalsIgnoreCase(checkDigit) && valid_checkDigit == 10) {
                return HKID;
            }
            if (valid_checkDigit == Integer.parseInt(checkDigit)) {
                return HKID;
            }

        return null;
    }

}
