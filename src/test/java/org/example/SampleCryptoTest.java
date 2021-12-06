package org.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;
import org.example.apigee.model.Context;
import org.example.apigee.model.Crypto;
import org.example.apigee.model.CryptoHashObject;
import org.example.apigee.model.Flow;
import org.example.apigee.model.Request;
import org.example.apigee.model.Response;
import org.example.apigee.model.Session;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.Scriptable;

class SampleCryptoTest extends AbstractJsPolicyTest {

  @Test
  void test() {
    // Arrange
    String data = "Something";
    String digest = "Some Hash";
    String digest64 = "Some Hash 64";

    setContextFlow(Flow.PROXY_REQ_FLOW);
    Context context = useContext();
    Session session = useContextSession();
    setContextSessionValue("foo", "bar");
    setContextSessionValue("foo2", 123);

    Crypto crypto = useCrypto();
    CryptoHashObject sha1 = useCryptoSha1();
    CryptoHashObject sha256 = useCryptoSha256();

    setContextVariable("mww.data", data);

    when(sha1.digest()).thenReturn(digest);
    when(sha1.digest64()).thenReturn(digest64);

    when(sha256.digest()).thenReturn(digest);
    when(sha256.digest64()).thenReturn(digest64);

    Request proxyRequest = useContextProxyRequest();
    setHeaderValue(proxyRequest.getHeaders(), "foo", Arrays.asList("bar", "baz", "baf"));
    var headerValues = setHeaderValue(proxyRequest.getHeaders(), "foo2", Arrays.asList("bar", "baz", "baf"));

    Response proxyResponse = useContextProxyResponse();
    setHeaderValue(proxyResponse.getHeaders(), "response-foo", Arrays.asList("bar", "baz", "baf"));

//    request.getHeaders().setValues("foo", Arrays.asList("bar", "baz", "baf"));

    // Act
    evaluateTest();

    // Verify
    verify(crypto).getSHA1();
    verify(sha1).update(data);
    verify(sha1).digest();
    verify(sha1).digest64();
    verify(sha256).digest();
    verify(sha256).digest64();

    verify(session).put(eq("baz"), any(Scriptable.class), eq(headerValues));

    verify(context).setVariable("mww.sha1.hash", digest);
    verify(context).setVariable("mww.sha1.hash64", digest64);

    verify(context).setVariable("mww.sha256.hash", digest);
    verify(context).setVariable("mww.sha256.hash64", digest64);
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("sample-crypto-test.js");
  }
}
