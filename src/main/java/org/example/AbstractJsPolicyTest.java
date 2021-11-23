package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.example.apigee.model.Context;
import org.example.apigee.model.Crypto;
import org.example.engine.RhinoEngine;
import org.example.engine.model.BaseScriptableObject;
import org.example.engine.model.Tuple;

public abstract class AbstractJsPolicyTest {
  private final RhinoEngine engine;

  Context context;
  Crypto crypto;

  protected AbstractJsPolicyTest() {
    this.engine = new RhinoEngine();
    this.context = BaseScriptableObject.newObject(Context.class, engine);
    this.crypto = BaseScriptableObject.newObject(Crypto.class, engine);
    engine.registerGlobalScopeObjects(Arrays.asList(new Tuple<>("context", context), new Tuple<>("crypto", crypto)));
  }

  protected void evaluateTest() throws IOException {
    // Init test data for now
    initTestData();

    // Run the JS code
    engine.eval(getTestFile());
  }

  abstract File getTestFile();

  private void initTestData() {
    context.getTargetRequest().setMethod("GET");
    context.getTargetRequest().setUrl("http://someurl.com");

    var headers = context.getTargetRequest().getHeaders();
    headers.setValues("foo", Arrays.asList("bar"));
    headers.setValues("foo2", Arrays.asList("bar", "baz"));

    var queryParams = context.getTargetRequest().getQueryParams();
    queryParams.setValues("city", Arrays.asList("PaloAlto", "NewYork"));

    context.getSession().put("foo", null, "bar");
    context.getSession().put("foo2", null, 123);
  }
}
