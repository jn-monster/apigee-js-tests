package org.example.apigee.model;

import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Response extends BaseScriptableObject {

  private JsMap headers;
  private ResponseStatus status;
  private ResponseContent content;

  @JSGetter
  public JsMap getHeaders() {
    if (headers == null) headers = newObject(JsMap.class);
    return headers;
  }

  @JSGetter
  public ResponseStatus getStatus() {
    if (status == null) status = newObject(ResponseStatus.class);
    return status;
  }

  @JSGetter
  public ResponseContent getContent() {
    if (content == null) content = newObject(ResponseContent.class);
    return content;
  }

  @JSSetter
  public void setHeaders(JsMap headers) {
    this.headers = headers;
  }
}
