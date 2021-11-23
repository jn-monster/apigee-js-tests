package org.example.apigee.model;

import java.util.HashMap;
import java.util.Map;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;

public class ApigeeContext extends BaseScriptableObject {

  private Map<String, String> variables = new HashMap<>();

  private String flow = "PROXY_REQ_FLOW";
  private Session session;
  private Request targetRequest;

  @JSFunction
  public String getVariable(String key) {
    return variables.get(key);
  }

  @JSFunction
  public void setVariable(String key, String value) {
    variables.put(key, value);
  }

  @JSFunction
  public void removeVariable(String key) {
    variables.remove(key);
  }

  @JSGetter
  public String getFlow() {
    return flow;
  }

  public void setFlow(String flow) {
    this.flow = flow;
  }

  @JSGetter
  public Request getTargetRequest() {
    if (targetRequest == null) targetRequest = newObject(Request.class);
    return targetRequest;
  }

  public void setTargetRequest(Request targetRequest) {
    this.targetRequest = targetRequest;
  }

  @JSGetter
  public Session getSession() {
    if (session == null) session = newObject(Session.class);
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
}
