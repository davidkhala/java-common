import org.davidkhala.AbstractPropertiesLoader;
import org.davidkhala.Stream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoaderTest {
  @Test
  public void loadProperties() throws IOException {

    AbstractPropertiesLoader loader =
        new AbstractPropertiesLoader() {

          @Override
          protected InputStream propertiesProvider() {
            return Stream.from("a=b");
          }
        };
    Properties properties = loader.getProperties();
    System.out.println(properties);
    assert properties.get("a").equals("b");
  }
}
