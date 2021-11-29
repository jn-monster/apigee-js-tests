package org.example.engine;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.ScriptableObject;

public interface ScriptableFactory {
  <T> String getClassName(Class<T> c);

  <T extends BaseScriptableObject> T newObject(Class<T> c);

  ScriptableObject getScope();
}
