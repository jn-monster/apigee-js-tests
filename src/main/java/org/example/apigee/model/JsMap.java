package org.example.apigee.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.Scriptable;

public class JsMap extends BaseScriptableObject {

  private Map<String, JsMapValues> mapValues = new LinkedHashMap<>();

  public void setValues(String key , List<Object> values) {
    var mapValue = newObject(JsMapValues.class);
    mapValue.setValues(values);
    this.mapValues.put(key, mapValue);
  }

  @Override
  public Object get(String name, Scriptable start) {
    return mapValues.get(name);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return mapValues;
  }
}
