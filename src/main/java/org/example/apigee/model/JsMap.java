package org.example.apigee.model;

import java.util.LinkedHashMap;
import java.util.Map;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class JsMap extends BaseScriptableObject {

  private Map<String, JsMapValues> values = new LinkedHashMap<>();

  @JSGetter
  public Map<String, JsMapValues> getValues() {
    return values;
  }

  @JSSetter
  public void setValues(Map<String, JsMapValues> values) {
    this.values = values;
  }

  @Override
  public Object get(String name, Scriptable start) {
    return values.get(name);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return values;
  }
}
