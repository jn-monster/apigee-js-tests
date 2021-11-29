package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class ResponseShortcut extends BaseScriptableObject {

  @JSGetter
  public JsMap getHeaders() {
    return getWrappedResponse().getHeaders();
  }

  @JSGetter
  public ResponseStatus getStatus() {
    return getWrappedResponse().getStatus();
  }

  @JSGetter
  public ResponseContent getContent() {
    return getWrappedResponse().getContent();
  }

  private Response getWrappedResponse() {
    var scope = getFactory().getScope();
    var context = (Context) scope.get("context", scope);
    switch (context.getFlow()) {
      case "PROXY_RESP_FLOW":
        return context.getProxyResponse();
      case "TARGET_RESP_FLOW":
        return  context.getTargetResponse();
      default:
        throw new IllegalStateException("Unknown Response Flow specified");
    }
  }
}
