package org.example.apigee.model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.example.ApigeeUtils;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Headers extends ScriptableObject {

  private Map<String, HeaderValues> headers = new LinkedHashMap<>();

  public Headers() {
    var headerValues = ApigeeUtils.createScriptableObject(this, HeaderValues.class);
    var headerValues2 = ApigeeUtils.createScriptableObject(this, HeaderValues.class);

    headerValues.setHeaderValues(Arrays.asList("bar"));
    headerValues2.setHeaderValues(Arrays.asList("bar", "baz"));
    this.headers.put("foo", headerValues);
    this.headers.put("foo2", headerValues2);
  }

  @JSGetter
  public Map<String, HeaderValues> getHeaders() {
    return headers;
  }

  @JSSetter
  public void setHeaders(Map<String, HeaderValues> headers) {
    this.headers = headers;
  }

  @Override
  public Object get(String name, Scriptable start) {
    if (name.equals("HeaderValues")) return super.get(name, start);

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
