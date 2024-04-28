import davidkhala.common.CLI;
import org.junit.Test;

import java.io.IOException;

public class CLITest {
    @Test
    public void jVersion() throws IOException, InterruptedException {
        CLI.exec("java", "-version");
    }
}
