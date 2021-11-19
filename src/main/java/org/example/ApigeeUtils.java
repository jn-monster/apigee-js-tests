package org.example;

import java.lang.reflect.InvocationTargetException;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class ApigeeUtils {

  public static <T extends Scriptable> T createScriptableObject(Class<T> c) {
    String className = c.getName();
    if (!ScriptableObject.hasProperty(AbstractJsPolicyTest.scope, className)) {
      try {
        ScriptableObject.defineClass(AbstractJsPolicyTest.scope, c);
      } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
        throw new IllegalStateException(e);
      }
    }

    return (T) Context.getCurrentContext().newObject(AbstractJsPolicyTest.scope, className);
  }
}
