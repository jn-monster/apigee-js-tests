package org.example.apigee.model;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class ApigeeContext extends ScriptableObject {

  private String flow = "PROXY_REQ_FLOW";
  private Request targetRequest = new Request();

  public ApigeeContext() {
  }

  public String getFlow() {
    return flow;
  }

  public void setFlow(String flow) {
    this.flow = flow;
  }

  public Scriptable getTargetRequest() {
    return targetRequest;
  }

  public void setTargetRequest(Request targetRequest) {
    this.targetRequest = targetRequest;
  }

  @Override
  public Object get(String name, Scriptable start) {
    if (name.equals("flow")) return flow;
    if (name.equals("targetRequest")) return targetRequest;

    return super.get(name, start);
  }

  @Override
  public String getClassName() {
    return "ApigeeContext";
  }
}
