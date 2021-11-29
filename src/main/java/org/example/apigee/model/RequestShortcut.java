package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class RequestShortcut extends BaseScriptableObject {

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
    var scope = getFactory().getScope();
    var context = (Context) scope.get("context", scope);
    switch (context.getFlow()) {
      case "PROXY_REQ_FLOW":
        return context.getProxyRequest();
      case "TARGET_REQ_FLOW":
        return  context.getTargetRequest();
      default:
        throw new IllegalStateException("Unknown Request Flow specified");
    }
  }
}
