package org.example.apigee.model;

import java.util.List;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;

public class HeaderValues extends ScriptableObject {

  private List<Object> headerValues;

  @JSGetter
  public List<Object> getHeaderValues() {
    return headerValues;
  }

  public void setHeaderValues(List<Object> headerValues) {
    this.headerValues = headerValues;
  }

  @JSFunction
  public Integer length() {
    return headerValues.size();
  }

  @Override
  public Object get(int index, Scriptable start) {
    return headerValues.get(index);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return headerValues.get(0);
  }

  @Override
  public String toString() {
    return getDefaultValue(String.class).toString();
  }

  @Override
  public String getClassName() {
    return "HeaderValues";
  }
}
