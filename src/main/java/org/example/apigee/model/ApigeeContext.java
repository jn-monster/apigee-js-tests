package org.example.apigee.model;

import java.util.HashMap;
import java.util.Map;
import org.example.ApigeeUtils;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class ApigeeContext extends BaseScriptableObject {

  private Map<String, String> variables = new HashMap<>();

  private String flow = "PROXY_REQ_FLOW";
  private Session session;
  private Request targetRequest;

  public ApigeeContext() {
    this.targetRequest = ApigeeUtils.createScriptableObject(Request.class);
    this.session = ApigeeUtils.createScriptableObject(Session.class);
  }

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

  @JSSetter // TODO: is there one?
  public void setFlow(String flow) {
    this.flow = flow;
  }

  @JSGetter
  public Scriptable getTargetRequest() {
    return targetRequest;
  }

  @JSSetter
  public void setTargetRequest(Request targetRequest) {
    this.targetRequest = targetRequest;
  }

  @JSGetter
  public Session getSession() {
    return session;
  }

  @JSSetter
  public void setSession(Session session) {
    this.session = session;
  }
}
