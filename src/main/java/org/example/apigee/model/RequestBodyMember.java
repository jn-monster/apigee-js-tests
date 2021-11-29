package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.Scriptable;

public class RequestBodyMember extends BaseScriptableObject {
  @Override
  public Object get(String name, Scriptable start) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
