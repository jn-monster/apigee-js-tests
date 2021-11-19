package org.example.apigee.model;

import org.example.AbstractJsPolicyTest;
import org.example.ApigeeUtils;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class ApigeeContext extends ScriptableObject {

  private String flow = "PROXY_REQ_FLOW";
  private Request targetRequest;

  public ApigeeContext() {
    this.targetRequest = ApigeeUtils.createScriptableObject(Request.class);
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

  @Override
  public String getClassName() {
    return "ApigeeContext";
  }
}
