package org.example.apigee.model;

import org.mozilla.javascript.annotations.JSGetter;

public class RequestBody extends BaseScriptableObject {

  private String asXML;
  private String asJSON;
  private String asForm;

  @JSGetter
  public String getAsXML() {
    return asXML;
  }

  @JSGetter
  public String getAsJSON() {
    return asJSON;
  }

  @JSGetter
  public String getAsForm() {
    return asForm;
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    return super.getDefaultValue(typeHint);
  }
}
