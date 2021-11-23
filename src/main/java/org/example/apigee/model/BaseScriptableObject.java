package org.example.apigee.model;

import org.example.ScriptableFactory;
import org.mozilla.javascript.ScriptableObject;

public class BaseScriptableObject extends ScriptableObject {

  private ScriptableFactory factory;

  public static <T extends BaseScriptableObject> T newObject(Class<T> c, ScriptableFactory factory) {
    T object = factory.newObject(c);
    object.setFactory(factory);
    return object;
  }

  protected <T extends BaseScriptableObject> T newObject(Class<T> c) {
    return newObject(c, factory);
  }

  public ScriptableFactory getFactory() {
    return factory;
  }

  public void setFactory(ScriptableFactory factory) {
    this.factory = factory;
  }

  @Override
  public String getClassName() {
    return this.getClass().getName();
  }
}
