package org.example.apigee.model;

import org.example.ApigeeUtils;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Response extends BaseScriptableObject {

  private JsMap headers;
  private ResponseStatus status;
  private ResponseContent content;

  public Response() {
    this.headers = ApigeeUtils.createScriptableObject(JsMap.class);
    this.content = ApigeeUtils.createScriptableObject(ResponseContent.class);
    this.status = ApigeeUtils.createScriptableObject(ResponseStatus.class);
  }

  @JSGetter
  public JsMap getHeaders() {
    return headers;
  }

  @JSGetter
  public ResponseStatus getStatus() {
    return status;
  }

  @JSGetter
  public ResponseContent getContent() {
    return content;
  }

  @JSSetter
  public void setHeaders(JsMap headers) {
    this.headers = headers;
  }
}
