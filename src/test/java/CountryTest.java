import davidkhala.common.CountryTool;
import org.junit.Test;

import java.util.ArrayList;

public class CountryTest {
    @Test
    public void getNames() {
        ArrayList<String> names = CountryTool.getNames();
        assert names.contains("Hong Kong SAR China");
        assert names.size() == 252;
    }
    @Test
    public void validateHKID() {
        String id = "D788888(1)";
        assert CountryTool.isHKIDValid(id);
    }

}
