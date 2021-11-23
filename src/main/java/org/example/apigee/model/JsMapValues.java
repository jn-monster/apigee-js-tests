package org.example.apigee.model;

import java.util.List;
import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;

public class JsMapValues extends BaseScriptableObject {

  private List<Object> values;

  @JSGetter
  public List<Object> getValues() {
    return values;
  }

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
