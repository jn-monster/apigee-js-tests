package org.example.apigee.model;

import java.util.ArrayList;
import java.util.List;
import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.annotations.JSFunction;

public class JsMapValues extends BaseScriptableObject {

  private List<Object> values = new ArrayList<>();

  public void setValues(List<Object> values) {
    this.values = values;
  }

  @JSFunction
  public Integer length() {
    return values.size();
  }

  @Override
  public Object get(int index, Scriptable start) {
    return values.get(index);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return values.get(0);
  }

  @Override
  public String toString() {
    return getDefaultValue(String.class).toString();
  }
}
