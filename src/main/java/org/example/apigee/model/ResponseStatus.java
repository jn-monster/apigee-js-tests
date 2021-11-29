package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class ResponseStatus extends BaseScriptableObject {

  private String code;
  private String message;

  @JSGetter
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @JSGetter
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
