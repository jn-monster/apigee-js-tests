package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.example.apigee.model.ApigeeContext;
import org.example.apigee.model.ApigeeCrypto;
import org.example.apigee.model.BaseScriptableObject;
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
    this.apigeeContext = ApigeeUtils.createScriptableObject(ApigeeContext.class);
    this.apigeeCrypto = BaseScriptableObject.newObject(ApigeeCrypto.class, factory);

    // Add apigee context and crypto to scope
    addObjectToScope("context", apigeeContext);
    addObjectToScope("crypto", apigeeCrypto);

    // Run the JS code
    context.evaluateReader(scope, new FileReader(getTestFile()), getTestFile().getName(), 1, null);
  }

  abstract File getTestFile();

  private void addObjectToScope(String name, Object object) {
    Scriptable scriptable = Context.toObject(object, scope);
    scope.put(name, scope, scriptable);
  }
}
