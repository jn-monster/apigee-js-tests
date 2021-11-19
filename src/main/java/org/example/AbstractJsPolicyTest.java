package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.example.apigee.model.ApigeeContext;
import org.example.apigee.model.ApigeeCrypto;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;

public abstract class AbstractJsPolicyTest {

  ApigeeContext apigeeContext;
  ApigeeCrypto apigeeCrypto;

  protected void evaluateTest() throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException {
    Context context = Context.enter();
    ScriptableObject scope = new Global(context);

    ScriptableObject.defineClass(scope, ApigeeContext.class);
    this.apigeeContext = (ApigeeContext) context.newObject(scope, "ApigeeContext");
    this.apigeeCrypto = new ApigeeCrypto();

    // Add apigee context and crypto to scope
    addObjectToScope("context", apigeeContext, scope);
    addObjectToScope("crypto", apigeeCrypto, scope);

    // Run the JS code
    context.evaluateReader(scope, new FileReader(getTestFile()), getTestFile().getName(), 1, null);
  }

  abstract File getTestFile();

  private void addObjectToScope(String name, Object object, Scriptable scope) {
    Scriptable scriptable = Context.toObject(object, scope);
    scope.put(name, scope, scriptable);
  }
}
