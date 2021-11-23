package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class ApigeeCrypto extends BaseScriptableObject {

  @JSFunction
  public String getSHA1() {
    return "sha1";
  }

  @JSFunction
  public String getSHA256() {
    return "sha256";
  }

  @JSFunction
  public String getSHA512() {
    return "sha512";
  }

  @JSFunction
  public String getMD5() {
    return "md5";
  }
}
