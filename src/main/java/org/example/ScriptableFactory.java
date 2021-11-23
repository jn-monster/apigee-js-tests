package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.example.apigee.model.BaseScriptableObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class ScriptableFactory {
  private final Context context;
  private final ScriptableObject scope;
  private final List<String> definedClasses = new ArrayList<>();

  public ScriptableFactory(Context context, ScriptableObject scope) {
    this.context = context;
    this.scope = scope;
  }

  public <T> String getClassName(Class<T> c) {
    return c.getName();
  }

  public <T extends BaseScriptableObject> T newObject(Class<T> c) {
    String className = getClassName(c);
    if (!definedClasses.contains(className)) {
      try {
        ScriptableObject.defineClass(this.scope, c);
      } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
        throw new IllegalStateException(e);
      }
      definedClasses.add(className);
    }

    return (T) this.context.newObject(this.scope, className);
  }
}
