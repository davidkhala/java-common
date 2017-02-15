package org.astri.ICDD.ASIC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by davidliu on 2/4/2017.
 */

public class CountryTool {

    public ArrayList<String> getNames(){
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length()>0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        return countries;
    }
}
