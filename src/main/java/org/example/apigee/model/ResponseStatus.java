package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class ResponseStatus extends BaseScriptableObject {

  private Integer code;
  private String message;

  @JSGetter
  public Integer getCode() {
    return code;
  }

  @JSGetter
  public String getMessage() {
    return message;
  }
}
