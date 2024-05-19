import org.davidkhala.CLI;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CLITest {
    @Test
    public void jVersion() throws IOException, InterruptedException {
        CLI.exec("java", "-version");
    }
}
