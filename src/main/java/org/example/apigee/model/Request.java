package org.example.apigee.model;

import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Request extends BaseScriptableObject {

  private String url;
  private JsMap headers;
  private JsMap queryParams;
  private String method;
  private RequestBody body;

  @JSGetter
  public String getUrl() {
    return url;
  }

  @JSSetter
  public void setUrl(String url) {
    this.url = url;
  }

  @JSGetter
  public JsMap getHeaders() {
    if (headers == null) headers = newObject(JsMap.class);
    return headers;
  }

  @JSSetter
  public void setHeaders(JsMap headers) {
    this.headers = headers;
  }

  @JSGetter
  public JsMap getQueryParams() {
    if (queryParams == null) queryParams = newObject(JsMap.class);
    return queryParams;
  }

  @JSSetter
  public void setQueryParams(JsMap queryParams) {
    this.queryParams = queryParams;
  }

  @JSGetter
  public String getMethod() {
    return method;
  }

  @JSSetter
  public void setMethod(String method) {
    this.method = method;
  }

  @JSGetter
  public RequestBody getBody() {
    if (body == null) body = newObject(RequestBody.class);
    return body;
  }

  @JSSetter
  public void setBody(RequestBody body) {
    this.body = body;
  }
}
