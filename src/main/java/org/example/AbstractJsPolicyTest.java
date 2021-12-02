package org.example;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.util.Arrays;
import java.util.List;
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

  // Context
  public Context useContext() {
    if (this.mocks.context == null) {
      this.mocks.context = spy(BaseScriptableObject.newObject(Context.class, engine));
    }
    return this.mocks.context;
  }

  public void setContextVariable(String key, Object value) {
    Context context = useContext();
    context.setVariable(key, value);
  }

  public Request useContextProxyRequest() {
    if (this.mocks.proxyRequest == null) {
      this.mocks.proxyRequest = spy(BaseScriptableObject.newObject(Request.class, engine));

      Context context = useContext();
      doReturn(this.mocks.proxyRequest).when(context).getProxyRequest();
    }
    return this.mocks.proxyRequest;
  }

  public void setContextProxyRequestHeader(String headerName, List<Object> values) {
    Request proxyRequest = useContextProxyRequest();
    proxyRequest.getHeaders().setValues(headerName, values);
  }

  public Response useContextProxyResponse() {
    if (this.mocks.proxyResponse == null) {
      this.mocks.proxyResponse = spy(BaseScriptableObject.newObject(Response.class, engine));

      Context context = useContext();
      doReturn(this.mocks.proxyResponse).when(context).getProxyResponse();
    }
    return this.mocks.proxyResponse;
  }

  public void setContextProxyResponseHeader(String headerName, List<Object> values) {
    Response proxyResponse = useContextProxyResponse();
    proxyResponse.getHeaders().setValues(headerName, values);
  }

  public Request useContextTargetRequest() {
    if (this.mocks.targetRequest == null) {
      this.mocks.targetRequest = spy(BaseScriptableObject.newObject(Request.class, engine));

      Context context = useContext();
      doReturn(this.mocks.targetRequest).when(context).getTargetRequest();
    }
    return this.mocks.targetRequest;
  }

  public void setContextTargetRequestHeader(String headerName, List<Object> values) {
    Request targetRequest = useContextTargetRequest();
    targetRequest.getHeaders().setValues(headerName, values);
  }

  public Response useContextTargetResponse() {
    if (this.mocks.targetResponse == null) {
      this.mocks.targetResponse = spy(BaseScriptableObject.newObject(Response.class, engine));

      Context context = useContext();
      doReturn(this.mocks.targetResponse).when(context).getTargetResponse();
    }
    return this.mocks.targetResponse;
  }

  public void setContextTargetResponseHeader(String headerName, List<Object> values) {
    Response targetResponse = useContextTargetResponse();
    targetResponse.getHeaders().setValues(headerName, values);
  }

  public Session useContextSession() {
    if (this.mocks.session == null) {
      this.mocks.session = spy(BaseScriptableObject.newObject(Session.class, engine));

      Context context = useContext();
      doReturn(this.mocks.session).when(context).getSession();
    }
    return this.mocks.session;
  }

  public void setContextSessionValue(String key, Object value) {
    Session session = useContextSession();
    session.setValue(key, value);
  }

  // Crypto
  public Crypto useCrypto() {
    if (this.mocks.crypto == null) {
      this.mocks.crypto = spy(BaseScriptableObject.newObject(Crypto.class, engine));
    }
    return this.mocks.crypto;
  }

  public CryptoHashObject useCryptoSha1() {
    if (this.mocks.sha1 == null) {
      this.mocks.sha1 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.sha1).when(crypto).getSHA1();
    }
    return this.mocks.sha1;
  }

  public CryptoHashObject useCryptoSha256() {
    if (this.mocks.sha256 == null) {
      this.mocks.sha256 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.sha256).when(crypto).getSHA256();
    }
    return this.mocks.sha256;
  }

  public CryptoHashObject useCryptoSha512() {
    if (this.mocks.sha512 == null) {
      this.mocks.sha512 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.sha512).when(crypto).getSHA512();
    }
    return this.mocks.sha512;
  }

  public CryptoHashObject useCryptoMd5() {
    if (this.mocks.md5 == null) {
      this.mocks.md5 = spy(BaseScriptableObject.newObject(CryptoHashObject.class, engine));

      Crypto crypto = useCrypto();
      doReturn(this.mocks.md5).when(crypto).getMD5();
    }
    return this.mocks.md5;
  }

  private class Mocks {
    // Shorthands
    private RequestShortcut request;
    private ResponseShortcut response;

    // Context
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
