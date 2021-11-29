package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class CryptoHashObject extends BaseScriptableObject {

  private String digest;
  private String digest64;

  @JSFunction
  public void update(String value) {
  }

  @JSFunction
  public String digest() {
    return digest;
  }

  @JSFunction
  public String digest64() {
    return digest64;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public void setDigest64(String digest64) {
    this.digest64 = digest64;
  }
}
