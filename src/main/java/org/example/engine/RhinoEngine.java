package org.example.engine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.example.engine.model.BaseScriptableObject;
import org.example.engine.model.Tuple;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;

public class RhinoEngine implements ScriptableFactory {

  private final List<String> definedClasses = new ArrayList<>();

  private final Context context = Context.enter();
  private final ScriptableObject scope = new Global(context);

  public void eval(String script) {
    context.evaluateString(scope, script, "inlineScript", 1, null);
  }

  public void eval(File file) {
    try {
      context.evaluateReader(scope, new FileReader(file), file.getName(), 1, null);
    } catch (IOException e) {
      throw new IllegalStateException("File not found!");
    }
  }

  public void registerGlobalScopeObjects(List<Tuple<String, Scriptable>> globalScopeObjects) {
    globalScopeObjects.forEach(globalScopeObject -> scope.put(globalScopeObject.key, scope, globalScopeObject.value));
  }

  @Override
  public <T> String getClassName(Class<T> c) {
    return c.getName();
  }

  @Override
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

  public ScriptableObject getScope() {
    return scope;
  }
}
