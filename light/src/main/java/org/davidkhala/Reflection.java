package org.davidkhala;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Reflection {
  Object object;

  Class<?> clazz;

  public Reflection(Object object) {
    this.object = object;
    this.clazz = object.getClass();
  }

  public Reflection asSuperClass(Class<?> clazz) {
    if (clazz.isInstance(object)) {
      this.clazz = clazz;
    }
    return this;
  }

  private static final Map<Class<?>, Class<?>> WRAPPER_TYPES = new HashMap<>();

  static {
    WRAPPER_TYPES.put(Boolean.class, Boolean.TYPE);
    WRAPPER_TYPES.put(Character.class, Character.TYPE);
    WRAPPER_TYPES.put(Byte.class, Byte.TYPE);
    WRAPPER_TYPES.put(Short.class, Short.TYPE);
    WRAPPER_TYPES.put(Integer.class, Integer.TYPE);
    WRAPPER_TYPES.put(Long.class, Long.TYPE);
    WRAPPER_TYPES.put(Float.class, Float.TYPE);
    WRAPPER_TYPES.put(Double.class, Double.TYPE);
    WRAPPER_TYPES.put(Void.class, Void.TYPE);
  }

  public static boolean isWrapperType(Class<?> clazz) {
    return WRAPPER_TYPES.containsKey(clazz);
  }

  public Object method(String methodName, Object... args)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class<?>[] argClasses = new Class[args.length];
    for (int i = 0; i < args.length; i++) {
      Class<?> argClass = args[i].getClass();
      if (isWrapperType(argClass)) {
        argClasses[i] = WRAPPER_TYPES.get(argClass);
      } else {
        argClasses[i] = argClass;
      }
    }
    Method method = clazz.getDeclaredMethod(methodName, argClasses);
    method.setAccessible(true);
    return method.invoke(object, args);
  }

  public Object field(String fieldName) throws IllegalAccessException, NoSuchFieldException {
    Field field = clazz.getDeclaredField(fieldName);
    field.setAccessible(true);
    return field.get(object);
  }
}
