package org.example;

import java.io.File;
import java.util.Arrays;
import org.example.apigee.model.Context;
import org.example.apigee.model.Crypto;
import org.example.apigee.model.RequestShortcut;
import org.example.apigee.model.ResponseShortcut;
import org.example.engine.RhinoEngine;
import org.example.engine.model.BaseScriptableObject;
import org.example.engine.model.Tuple;

public abstract class AbstractJsPolicyTest {
  private final RhinoEngine engine;

  Context context;
  Crypto crypto;
  RequestShortcut request;
  ResponseShortcut response;

  protected AbstractJsPolicyTest() {
    this.engine = new RhinoEngine();
    this.context = BaseScriptableObject.newObject(Context.class, engine);
    this.crypto = BaseScriptableObject.newObject(Crypto.class, engine);
    this.request = BaseScriptableObject.newObject(RequestShortcut.class, engine);
    this.response = BaseScriptableObject.newObject(ResponseShortcut.class, engine);
    request.setContext(context);
    response.setContext(context);

    engine.registerGlobalScopeObjects(
        Arrays.asList(
            new Tuple<>("context", context),
            new Tuple<>("crypto", crypto),
            new Tuple<>("request", request),
            new Tuple<>("response", response)
        )
    );
  }

  protected void evaluateTest() {
    // Run the JS code
    engine.eval(getTestFile());
  }

  abstract File getTestFile();
}
