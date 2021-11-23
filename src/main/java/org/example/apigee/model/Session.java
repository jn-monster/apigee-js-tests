package org.example.apigee.model;

import java.util.LinkedHashMap;
import java.util.Map;
import org.mozilla.javascript.Scriptable;

public class Session extends BaseScriptableObject {
  private Map<String, Object> variables = new LinkedHashMap<>();

  public Session() {
    variables.put("foo", "bar");
    variables.put("foo2", 123);
  }

  @Override
  public Object get(String name, Scriptable start) {
    return variables.get(name);
  }

  @Override
  public void put(String name, Scriptable start, Object value) {
    this.variables.put(name, value);
  }
}
