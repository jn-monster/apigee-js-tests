package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class RequestBody extends BaseScriptableObject {

  private RequestBodyMember asXML;
  private RequestBodyMember asJSON;
  private RequestBodyMember asForm;

  @JSGetter
  public RequestBodyMember getAsXML() {
    if (asXML == null) asXML = newObject(RequestBodyMember.class);
    return asXML;
  }

  @JSGetter
  public RequestBodyMember getAsJSON() {
    if (asJSON == null) asJSON = newObject(RequestBodyMember.class);
    return asJSON;
  }

  @JSGetter
  public RequestBodyMember getAsForm() {
    if (asForm == null) asForm = newObject(RequestBodyMember.class);
    return asForm;
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
