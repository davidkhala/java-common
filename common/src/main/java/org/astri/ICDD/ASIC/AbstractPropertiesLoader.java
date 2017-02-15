package org.astri.ICDD.ASIC;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by davidliu on 11/4/2016.
 */

public abstract class AbstractPropertiesLoader {
    protected abstract InputStream propertiesProvider() throws IOException;

    Properties properties;

    /*
    lazy load
     */
    public Properties getProperties() throws IOException {
        if (properties == null) {
            InputStream inputStream = propertiesProvider();
            properties = new Properties();
            properties.load(inputStream);
        }
        return properties;

    }

}
