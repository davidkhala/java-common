import davidkhala.common.FileTool;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.junit.Test;

public class FileTest {
  @Test
  public void create_delete() throws IOException {
    String fileName = "dummy.txt";
    File file = new File(fileName);
    String extension = FileTool.getExtensionName(fileName);
    assert extension.equals("txt");
    FileTool.write(file, "txt");
    file.deleteOnExit();
  }

  @Test
  public void createDir() throws IOException {
    File file = new File("temp");
    assert file.mkdirs();
    file.deleteOnExit();
  }

  @Test
  public void fromURITest() throws IOException {
    String uriStr = "file:/C:/Users/david/Downloads/";
    URI uri = URI.create(uriStr);
    File file = new File(uri);
    assert file.toURI().toString().equals(uriStr);
  }
}
