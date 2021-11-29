package org.example.apigee.model;

import java.util.HashMap;
import java.util.Map;
import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;

public class Context extends BaseScriptableObject {

  private Map<String, Object> variables = new HashMap<>();

  private String flow = "PROXY_REQ_FLOW";
  private Session session;
  private Request proxyRequest;
  private Request targetRequest;
  private Response proxyResponse;
  private Response targetResponse;

  @JSFunction
  public Object getVariable(String key) {
    return variables.get(key);
  }

  @JSFunction
  public void setVariable(String key, Object value) {
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
  public Request getProxyRequest() {
    if (proxyRequest == null) proxyRequest = newObject(Request.class);
    return proxyRequest;
  }

  @JSGetter
  public Request getTargetRequest() {
    if (targetRequest == null) targetRequest = newObject(Request.class);
    return targetRequest;
  }

  @JSGetter
  public Response getProxyResponse() {
    if (proxyResponse == null) proxyResponse = newObject(Response.class);
    return proxyResponse;
  }

  @JSGetter
  public Response getTargetResponse() {
    if (targetResponse == null) targetResponse = newObject(Response.class);
    return targetResponse;
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
