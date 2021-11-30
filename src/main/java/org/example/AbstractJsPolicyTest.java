package org.example;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.Arrays;
import org.example.apigee.model.Context;
import org.example.apigee.model.Crypto;
import org.example.apigee.model.CryptoHashObject;
import org.example.apigee.model.Request;
import org.example.apigee.model.RequestShortcut;
import org.example.apigee.model.Response;
import org.example.apigee.model.ResponseShortcut;
import org.example.apigee.model.Session;
import org.example.engine.RhinoEngine;
import org.example.engine.model.BaseScriptableObject;
import org.example.engine.model.Tuple;

public abstract class AbstractJsPolicyTest {
  private final Mocks mocks = new Mocks();

  private final RhinoEngine engine;

  protected AbstractJsPolicyTest() {
    this.engine = new RhinoEngine();
  }

  abstract File getTestFile();

  protected void evaluateTest() {
    this.engine.registerGlobalScopeObjects(
        Arrays.asList(
            new Tuple<>("context", mocks.context),
            new Tuple<>("crypto", mocks.crypto),
            new Tuple<>("request", mocks.request),
            new Tuple<>("response", mocks.response)
        )
    );
    // Run the JS code
    engine.eval(getTestFile());
  }

//  private void initContext(Flow flow) {
//    if (this.context != null) return;
//    this.context = BaseScriptableObject.newObject(Context.class, engine);
//    this.context.setFlow(flow.name());
//    this.mocks.context = spy(this.context);
//  }
//
//  public Context useContext(Flow flow) {
//    initContext(flow);
//    return this.context;
//  }
//
//  public void setContextVariable(String key, Object value) {
//    this.context.setVariable(key, value);
//  }
//
//  public Request useContextProxyRequest() {
//    checkContextInitialized("useContextProxyRequest()");
//    if (this.proxyRequest == null) {
//      this.proxyRequest = BaseScriptableObject.newObject(Request.class, engine);
//      this.mocks.proxyRequest = spy(this.proxyRequest);
//      this.context.setProxyRequest(this.proxyRequest);
//    }
//    return this.proxyRequest;
//  }
//
//  public void setContextProxyRequestHeader(String key , List<Object> values) {
//    checkContextInitialized("setContextProxyRequestHeader()");
//    Request request = useContextProxyRequest();
//    request.getHeaders().setValues(key, values);
//  }
//
//  public Request useContextTargetRequest() {
//    checkContextInitialized("useContextTargetRequest()");
//    if (this.targetRequest == null) {
//      this.targetRequest = BaseScriptableObject.newObject(Request.class, engine);
//      this.mocks.targetRequest = spy(this.targetRequest);
//      this.context.setTargetRequest(this.targetRequest);
//    }
//    return this.proxyRequest;
//  }
//
//  public void setContextTargetRequestHeader(String key , List<Object> values) {
//    checkContextInitialized("setContextTargetRequestHeader()");
//    Request request = useContextTargetRequest();
//    request.getHeaders().setValues(key, values);
//  }
//
//  public Response useContextProxyResponse() {
//    checkContextInitialized("useContextProxyResponse()");
//    if (this.proxyResponse == null) {
//      this.proxyResponse = BaseScriptableObject.newObject(Response.class, engine);
//      this.mocks.proxyResponse = spy(this.proxyResponse);
//      this.context.setProxyResponse(this.proxyResponse);
//    }
//    return this.proxyResponse;
//  }
//
//  public void setContextProxyResponseHeader(String key , List<Object> values) {
//    checkContextInitialized("setContextProxyResponseHeader()");
//    Response response = useContextProxyResponse();
//    response.getHeaders().setValues(key, values);
//  }
//
//  public Response useContextTargetResponse() {
//    checkContextInitialized("useContextTargetResponse()");
//    if (this.targetResponse == null) {
//      this.targetResponse = BaseScriptableObject.newObject(Response.class, engine);
//      this.mocks.targetResponse = spy(this.targetResponse);
//      this.context.setTargetResponse(this.targetResponse);
//    }
//    return this.targetResponse;
//  }
//
//  public void setContextTargetResponseHeader(String key , List<Object> values) {
//    checkContextInitialized("setContextTargetResponseHeader()");
//    Response response = useContextTargetResponse();
//    response.getHeaders().setValues(key, values);
//  }
//
//  public void setContextSessionValue(String key, Object value) {
//    checkContextInitialized("setContextSessionValue()");
//    this.context.getSession().setValue(key, value);
//  }


  public Crypto useCrypto() {
    initCrypto();
    return this.mocks.crypto;
  }

  private void initCrypto() {
    if (this.mocks.crypto != null) return;
    this.mocks.crypto = spy(BaseScriptableObject.newObject(Crypto.class, engine));
  }

  public CryptoHashObject useCryptoSha1() {
    if (this.mocks.sha1 == null) {
      this.mocks.sha1 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      initCrypto();
      doReturn(this.mocks.sha1).when(this.mocks.crypto).getSHA1();
    }
    return this.mocks.sha1;
  }

  public CryptoHashObject useCryptoSha256() {
    if (this.mocks.sha256 == null) {
      this.mocks.sha256 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      initCrypto();
      doReturn(this.mocks.sha256).when(this.mocks.crypto).getSHA256();
    }
    return this.mocks.sha256;
  }

  public CryptoHashObject useCryptoSha512() {
    if (this.mocks.sha512 == null) {
      this.mocks.sha512 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      initCrypto();
      doReturn(this.mocks.sha512).when(this.mocks.crypto).getSHA512();
    }
    return this.mocks.sha512;
  }

  public CryptoHashObject useCryptoMd5() {
    if (this.mocks.md5 == null) {
      this.mocks.md5 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      initCrypto();
      doReturn(this.mocks.md5).when(this.mocks.crypto).getMD5();
    }
    return this.mocks.md5;
  }

  private void checkContextInitialized(String methodName) {
    if (this.mocks.context == null) throw new IllegalStateException("Context is not set. You have to call 'useContext()' before using method '" + methodName + "'.");
  }

  private class Mocks {
    // Context
    private RequestShortcut request;
    private ResponseShortcut response;
    private Context context;
    private Session session;
    private Request proxyRequest;
    private Request targetRequest;
    private Response proxyResponse;
    private Response targetResponse;

    // Crypto
    private Crypto crypto;
    private CryptoHashObject sha1;
    private CryptoHashObject sha256;
    private CryptoHashObject sha512;
    private CryptoHashObject md5;
  }
}
