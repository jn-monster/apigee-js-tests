package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.example.apigee.model.ApigeeContext;
import org.example.apigee.model.ApigeeCrypto;
import org.example.apigee.model.BaseScriptableObject;
import org.example.apigee.model.JsMapValues;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;

public abstract class AbstractJsPolicyTest {

  public static final Context context = Context.enter();
  public static final ScriptableObject scope = new Global(context);
  ScriptableFactory factory = new ScriptableFactory(context, scope);

  ApigeeContext apigeeContext;
  ApigeeCrypto apigeeCrypto;

  protected void evaluateTest() throws IOException {
    this.apigeeContext = BaseScriptableObject.newObject(ApigeeContext.class, factory);
    this.apigeeCrypto = BaseScriptableObject.newObject(ApigeeCrypto.class, factory);

    // Add apigee context and crypto to scope
    addObjectToScope("context", apigeeContext);
    addObjectToScope("crypto", apigeeCrypto);

    // Init test data for now
    initTestData();

    // Run the JS code
    context.evaluateReader(scope, new FileReader(getTestFile()), getTestFile().getName(), 1, null);
  }

  abstract File getTestFile();

  private void addObjectToScope(String name, Object object) {
    Scriptable scriptable = Context.toObject(object, scope);
    scope.put(name, scope, scriptable);
  }

  private void initTestData() {
    apigeeContext.getTargetRequest().setMethod("GET");
    apigeeContext.getTargetRequest().setUrl("http://someurl.com");
    Map<String, JsMapValues> headerValues = new HashMap<>();
    var headerValues1 = BaseScriptableObject.newObject(JsMapValues.class, factory);
    var headerValues2 = BaseScriptableObject.newObject(JsMapValues.class, factory);
    headerValues1.setValues(Arrays.asList("bar"));
    headerValues2.setValues(Arrays.asList("bar", "baz"));
    headerValues.put("foo", headerValues1);
    headerValues.put("foo2", headerValues2);
    apigeeContext.getTargetRequest().getHeaders().setValues(headerValues);

    Map<String, JsMapValues> queryParamsValues = new HashMap<>();
    var queryParamValues1 = BaseScriptableObject.newObject(JsMapValues.class, factory);
    queryParamValues1.setValues(Arrays.asList("PaloAlto", "NewYork"));
    queryParamsValues.put("city", queryParamValues1);
    apigeeContext.getTargetRequest().getQueryParams().setValues(queryParamsValues);

    apigeeContext.getSession().put("foo", null, "bar");
    apigeeContext.getSession().put("foo2", null, 123);
  }
}
