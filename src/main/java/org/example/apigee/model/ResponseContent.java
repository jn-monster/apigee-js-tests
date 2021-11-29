package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.annotations.JSGetter;

public class ResponseContent extends BaseScriptableObject {

  private ResponseContentMember asXML;
  private ResponseContentMember asJSON;

  @JSGetter
  public ResponseContentMember getAsXML() {
    if (asXML == null) asXML = newObject(ResponseContentMember.class);
    return asXML;
  }

  @JSGetter
  public ResponseContentMember getAsJSON() {
    if (asJSON == null) asJSON = newObject(ResponseContentMember.class);
    return asJSON;
  }

  @Override
  public Object getDefaultValue(Class<?> typeHint) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
