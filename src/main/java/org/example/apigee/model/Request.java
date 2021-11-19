package org.example.apigee.model;

import org.example.ApigeeUtils;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Request extends ScriptableObject {

  private String url;
  private Headers headers;
  private String method;
//  private RequestBody body;

  public Request() {
    this.headers = ApigeeUtils.createScriptableObject(this, Headers.class);
    this.url = "urlValue";
    this.method = "GET";
  }

  @JSGetter
  public String getUrl() {
    return url;
  }

  @JSSetter
  public void setUrl(String url) {
    this.url = url;
  }

  @JSGetter
  public Headers getHeaders() {
    return headers;
  }

  @JSSetter
  public void setHeaders(Headers headers) {
    this.headers = headers;
  }

  @JSGetter
  public String getMethod() {
    return method;
  }

  @JSSetter
  public void setMethod(String method) {
    this.method = method;
  }

  @Override
  public String getClassName() {
    return "Request";
  }
}
