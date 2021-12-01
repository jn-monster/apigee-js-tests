package org.example;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;
import org.example.apigee.model.Context;
import org.example.apigee.model.Crypto;
import org.example.apigee.model.CryptoHashObject;
import org.example.apigee.model.Request;
import org.example.apigee.model.Response;
import org.junit.jupiter.api.Test;

class SampleCryptoTest extends AbstractJsPolicyTest {

  @Test
  void test() {
    // Arrange
    String data = "Something";
    String digest = "Some Hash";
    String digest64 = "Some Hash 64";

    Context context = useContext();
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
    Response proxyResponse = useContextProxyResponse();
    setContextProxyRequestHeader("foo", Arrays.asList("bar", "baz", "baf"));
    setContextTargetRequestHeader("foo2", Arrays.asList("bar", "baz", "baf"));
    setContextTargetResponseHeader("response-foo", Arrays.asList("bar", "baz", "baf"));

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
