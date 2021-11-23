package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class ResponseContent extends BaseScriptableObject {

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
}
