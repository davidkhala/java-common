import davidkhala.common.Reflection;
import org.junit.Test;

public class ReflectionTest {
  @Test
  public void selfLift() {
    Class<Reflection> clazz = Reflection.class;
    assert clazz.getName().equals("davidkhala.common.Reflection");
    assert clazz.getPackageName().equals("davidkhala.common");
    assert clazz.getSimpleName().equals("Reflection");
    assert clazz.getCanonicalName().equals("davidkhala.common.Reflection");
    assert clazz.getTypeName().equals("davidkhala.common.Reflection");
  }
}
