package org.example.apigee.model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Headers extends ScriptableObject {

  private Map<String, HeaderValues> headers = new LinkedHashMap<>();

  public Headers() {
    var headerValues = new HeaderValues();
    headerValues.setHeaderValues(Arrays.asList("bar"));
    var headerValues2 = new HeaderValues();
    headerValues2.setHeaderValues(Arrays.asList("bar", "baz"));

    this.headers.put("foo", headerValues);
    this.headers.put("foo2", headerValues2);
  }

  public long length() {
    return 321;
  }

  public long jsFunction_length() {
    return 123;
  }

  @Override
  public Object get(String name, Scriptable start) {
    if (name.equals("length")) return super.get(name, start);

    return headers.get(name);
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return headers;
  }

  @Override
  public String getClassName() {
    return "Headers";
  }
}
