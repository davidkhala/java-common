package davidkhala.common;

import com.google.common.io.Resources;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Resource {
    public static File getFile(String name) throws URISyntaxException {
        URL url = Resources.getResource(name);
        return new File(url.toURI());
    }
}
