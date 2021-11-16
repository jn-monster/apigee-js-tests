package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.example.apigee.model.ApigeeContext;
import org.example.apigee.model.ApigeeCrypto;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

public abstract class AbstractJsPolicyTest {

  ApigeeContext context = new ApigeeContext();
  ApigeeCrypto crypto = new ApigeeCrypto();

  protected void evaluateTest() throws IOException {
    Context cx = Context.enter();
    Global globalScope = new Global(cx);

    // Add apigee context and crypto to scope
    addObjectToScope("context", context, globalScope);
    addObjectToScope("crypto", crypto, globalScope);

    // Run the JS code
    cx.evaluateReader(globalScope, new FileReader(getTestFile()), getTestFile().getName(), 1, null);
  }

  abstract File getTestFile();

  private void addObjectToScope(String name, Object object, Scriptable scope) {
    Scriptable scriptable = Context.toObject(object, scope);
    scope.put(name, scope, scriptable);
  }
}
