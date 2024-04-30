import davidkhala.common.FileTool;
import davidkhala.common.Resource;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    public void createDir() {
        File file = new File("temp");
        assert file.mkdirs();
        file.deleteOnExit();
    }

    @Test
    public void fromURITest() {
        String uriStr = "file:/C:/Users/";
        URI uri = URI.create(uriStr);
        File file = new File(uri);
        assert file.toURI().toString().equals(uriStr);
    }

    @Test
    public void getTestResourceFile() throws URISyntaxException, IOException {
        String notFound = "fixture";
        try {
            Resource.getFile(notFound);
        } catch (java.lang.IllegalArgumentException e) {
            assert e.getMessage().equals("resource %s not found.".formatted(notFound));
        }
        String name = ".env";
        File file = Resource.getFile(".env");
        assert name.equals(file.getName());
        String content= FileTool.read(file);
        assert content.equals("module=test");

    }
}
