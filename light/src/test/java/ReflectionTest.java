import org.davidkhala.Reflection;
import org.junit.jupiter.api.Test;

public class ReflectionTest {
  @Test
  public void selfLift() {
    Class<Reflection> clazz = Reflection.class;
    assert clazz.getName().equals("org.davidkhala.Reflection");
    assert clazz.getPackageName().equals("org.davidkhala");
    assert clazz.getSimpleName().equals("Reflection");
    assert clazz.getCanonicalName().equals("org.davidkhala.Reflection");
    assert clazz.getTypeName().equals("org.davidkhala.Reflection");
  }
}
