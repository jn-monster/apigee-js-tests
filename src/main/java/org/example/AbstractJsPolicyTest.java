package org.example;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.example.apigee.model.Context;
import org.example.apigee.model.Crypto;
import org.example.apigee.model.CryptoHashObject;
import org.example.apigee.model.Flow;
import org.example.apigee.model.JsMap;
import org.example.apigee.model.Request;
import org.example.apigee.model.Response;
import org.example.apigee.model.ResponseStatus;
import org.example.apigee.model.Session;
import org.example.engine.RhinoEngine;
import org.example.engine.model.BaseScriptableObject;
import org.example.engine.model.Tuple;
import org.mozilla.javascript.Scriptable;

public abstract class AbstractJsPolicyTest {
  private final Mocks mocks = new Mocks();

  private final RhinoEngine engine;
  private Flow flow;

  protected AbstractJsPolicyTest() {
    this.engine = new RhinoEngine();
  }

  abstract File getTestFile();

  protected void evaluateTest() {
    List<Tuple<String, Scriptable>> globalScopeObjects = new ArrayList<>();
    globalScopeObjects.add(new Tuple<>("context", this.mocks.context));
    globalScopeObjects.add(new Tuple<>("crypto", this.mocks.crypto));
    addShorthand(useFlow(), globalScopeObjects);

    this.engine.registerGlobalScopeObjects(
        globalScopeObjects
    );
    // Run the JS code
    engine.eval(getTestFile());
  }

  // Context
  protected void setContextFlow(Flow flow) {
    if (this.flow != null) {
      throw new IllegalStateException("Context.flow is already set. Flow cannot be changed once set.");
    }
    this.flow = flow;
  }

  protected Context useContext() {
    if (this.mocks.context == null) {
      this.mocks.context = spy(BaseScriptableObject.newObject(Context.class, engine));
      doReturn(useFlow().name()).when(this.mocks.context).getFlow();
    }
    return this.mocks.context;
  }

  protected void setContextVariable(String key, Object value) {
    Context context = useContext();
    doReturn(value).when(context).getVariable(key);
  }

  protected Request useContextProxyRequest() {
    if (this.mocks.proxyRequest == null) {
      this.mocks.proxyRequest = spy(BaseScriptableObject.newObject(Request.class, engine));

      Context context = useContext();
      doReturn(this.mocks.proxyRequest).when(context).getProxyRequest();
    }
    return this.mocks.proxyRequest;
  }

  protected Response useContextProxyResponse() {
    if (this.mocks.proxyResponse == null) {
      this.mocks.proxyResponse = spy(BaseScriptableObject.newObject(Response.class, engine));

      Context context = useContext();
      doReturn(this.mocks.proxyResponse).when(context).getProxyResponse();
    }
    return this.mocks.proxyResponse;
  }

  protected Request useContextTargetRequest() {
    if (this.mocks.targetRequest == null) {
      this.mocks.targetRequest = spy(BaseScriptableObject.newObject(Request.class, engine));

      Context context = useContext();
      doReturn(this.mocks.targetRequest).when(context).getTargetRequest();
    }
    return this.mocks.targetRequest;
  }

  protected Response useContextTargetResponse() {
    if (this.mocks.targetResponse == null) {
      this.mocks.targetResponse = spy(BaseScriptableObject.newObject(Response.class, engine));

      Context context = useContext();
      doReturn(this.mocks.targetResponse).when(context).getTargetResponse();
    }
    return this.mocks.targetResponse;
  }

  protected ResponseStatus useProxyResponseStatus() {
    if (this.mocks.proxyResponseStatus == null) {
      this.mocks.proxyResponseStatus = spy(BaseScriptableObject.newObject(ResponseStatus.class, engine));

      Response proxyResponse = useContextProxyResponse();
      doReturn(this.mocks.proxyResponseStatus).when(proxyResponse).getStatus();
    }
    return this.mocks.proxyResponseStatus;
  }

  protected ResponseStatus useTargetResponseStatus() {
    if (this.mocks.targetResponseStatus == null) {
      this.mocks.targetResponseStatus = spy(BaseScriptableObject.newObject(ResponseStatus.class, engine));

      Response targetResponse = useContextTargetResponse();
      doReturn(this.mocks.targetResponseStatus).when(targetResponse).getStatus();
    }
    return this.mocks.targetResponseStatus;
  }

  protected Scriptable setHeaderValue(JsMap headers, String headerName, List<Object> values) {
    return headers.setValues(headerName, values);
  }

  protected Scriptable setQueryParamValue(JsMap queryParams, String paramName, List<Object> values) {
    return queryParams.setValues(paramName, values);
  }

  protected Session useContextSession() {
    if (this.mocks.session == null) {
      this.mocks.session = spy(BaseScriptableObject.newObject(Session.class, engine));

      Context context = useContext();
      doReturn(this.mocks.session).when(context).getSession();
    }
    return this.mocks.session;
  }

  protected void setContextSessionValue(String key, Object value) {
    Session session = useContextSession();
    session.setValue(key, value);
//    when(session.get(eq(key), any(Scriptable.class))).thenReturn(value);
  }

  // Crypto
  protected Crypto useCrypto() {
    if (this.mocks.crypto == null) {
      this.mocks.crypto = spy(BaseScriptableObject.newObject(Crypto.class, engine));
    }
    return this.mocks.crypto;
  }

  protected CryptoHashObject useCryptoSha1() {
    if (this.mocks.sha1 == null) {
      this.mocks.sha1 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.sha1).when(crypto).getSHA1();
      doReturn(this.mocks.sha1).when(crypto).getHash("SHA-1");
    }
    return this.mocks.sha1;
  }

  protected CryptoHashObject useCryptoSha256() {
    if (this.mocks.sha256 == null) {
      this.mocks.sha256 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.sha256).when(crypto).getSHA256();
      doReturn(this.mocks.sha256).when(crypto).getHash("SHA-256");
    }
    return this.mocks.sha256;
  }

  protected CryptoHashObject useCryptoSha512() {
    if (this.mocks.sha512 == null) {
      this.mocks.sha512 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.sha512).when(crypto).getSHA512();
      doReturn(this.mocks.sha512).when(crypto).getHash("SHA-512");
    }
    return this.mocks.sha512;
  }

  protected CryptoHashObject useCryptoMd5() {
    if (this.mocks.md5 == null) {
      this.mocks.md5 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.md5).when(crypto).getMD5();
      doReturn(this.mocks.md5).when(crypto).getHash("MD5");
    }
    return this.mocks.md5;
  }

  private Flow useFlow() {
    if (this.flow == null) {
      throw new IllegalStateException("No Flow has been set. Please use 'setContextFlow()' method to set it.");
    }
    return this.flow;
  }

  private void addShorthand(Flow flow, List<Tuple<String, Scriptable>> globalScopeObjects) {
    switch (flow) {
      case PROXY_REQ_FLOW:
        globalScopeObjects.add(new Tuple<>("request", this.mocks.proxyRequest));
        break;
      case TARGET_REQ_FLOW:
        globalScopeObjects.add(new Tuple<>("request", this.mocks.targetRequest));
        break;
      case PROXY_RESP_FLOW:
        globalScopeObjects.add(new Tuple<>("response", this.mocks.proxyResponse));
        break;
      case TARGET_RESP_FLOW:
        globalScopeObjects.add(new Tuple<>("response", this.mocks.targetResponse));
        break;
      default:
        throw new IllegalStateException("Unknown Flow specified.");
    }
  }

  private class Mocks {
    // Context
    private Context context;
    private Session session;
    private Request proxyRequest;
    private Request targetRequest;
    private Response proxyResponse;
    private Response targetResponse;
    private ResponseStatus proxyResponseStatus;
    private ResponseStatus targetResponseStatus;

    // Crypto
    private Crypto crypto;
    private CryptoHashObject sha1;
    private CryptoHashObject sha256;
    private CryptoHashObject sha512;
    private CryptoHashObject md5;
  }
}
