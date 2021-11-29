package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class RequestShortcut extends BaseScriptableObject {

  private Context context;

  @JSGetter
  public String getUrl() {
    return getWrappedRequest().getUrl();
  }

  @JSGetter
  public JsMap getHeaders() {
    return getWrappedRequest().getHeaders();
  }

  @JSGetter
  public JsMap getQueryParams() {
    return getWrappedRequest().getQueryParams();
  }

  @JSGetter
  public String getMethod() {
    return getWrappedRequest().getMethod();
  }

  @JSGetter
  public RequestBody getBody() {
    return getWrappedRequest().getBody();
  }

  private Request getWrappedRequest() {
    switch (this.context.getFlow()) {
      case "PROXY_REQ_FLOW":
        return this.context.getProxyRequest();
      case "TARGET_REQ_FLOW":
        return  this.context.getTargetRequest();
      default:
        throw new IllegalStateException("Unknown Request Flow specified");
    }
  }

  public void setContext(Context context) {
    this.context = context;
  }
}
