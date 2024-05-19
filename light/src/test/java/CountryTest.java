import org.davidkhala.CountryTool;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CountryTest {
  @Test
  public void getNames() {
    ArrayList<String> names = CountryTool.getNames();
    assert names.size() == 252;
  }

  @Test
  public void validateHKID() {
    String id = "D788888(1)";
    assert CountryTool.isHKIDValid(id);
  }
}
