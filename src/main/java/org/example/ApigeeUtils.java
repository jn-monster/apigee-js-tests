package org.example;

import java.lang.reflect.InvocationTargetException;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class ApigeeUtils {

  public static <T extends Scriptable> T createScriptableObject(Scriptable scope, Class<T> c) {
    String classSimpleName = c.getSimpleName();
    if (!ScriptableObject.hasProperty(scope, classSimpleName)) {
      try {
        ScriptableObject.defineClass(scope, c);
      } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
        throw new IllegalStateException(e);
      }
    }

    return (T) Context.getCurrentContext().newObject(scope, classSimpleName);
  }
}
