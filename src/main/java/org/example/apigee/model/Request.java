package org.example.apigee.model;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Request extends ScriptableObject {

  private String url;
  private Headers headers;
  private String method;
//  private RequestBody body;

  public Request() {
    this.headers = new Headers();
    this.url = "urlValue";
    this.method = "GET";
  }

  @Override
  public Object get(String name, Scriptable start) {
    if (name.equals("url")) return url;
    if (name.equals("method")) return method;
    if (name.equals("headers")) return headers;

    return super.get(name, start);
  }

  @Override
  public String getClassName() {
    return "Request";
  }
}
