//package org.example;
//
//import static org.example.apigee.model.Flow.PROXY_REQ_FLOW;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.io.File;
//import java.util.Arrays;
//import org.example.apigee.model.Context;
//import org.example.apigee.model.Crypto;
//import org.example.apigee.model.CryptoHashObject;
//import org.example.apigee.model.Request;
//import org.example.apigee.model.Response;
//import org.junit.jupiter.api.Test;
//
//class SampleCryptoTest extends AbstractJsPolicyTest {
//
//  @Test
//  void test() {
//    // Arrange
//    String data = "Something";
//    String digest = "Some Hash";
//    String digest64 = "Some Hash 64";
//
//    Context context = useContext(PROXY_REQ_FLOW);
//    setContextSessionValue("foo", "bar");
//
//    Crypto crypto = useCrypto();
//    CryptoHashObject sha1 = useCryptoSha1();
//
////    when(context.getVariable("mww.data")).thenReturn(data);
//    setContextVariable("mww.data", data);
//
//    when(sha1.digest()).thenReturn(digest);
//    when(sha1.digest64()).thenReturn(digest64);
//    Request proxyRequest = useContextProxyRequest();
//    Response proxyResponse = useContextProxyResponse();
//    setContextProxyRequestHeader("foo", Arrays.asList("bar", "baz", "baf"));
//    setContextTargetRequestHeader("foo2", Arrays.asList("bar", "baz", "baf"));
//
//    setContextTargetResponseHeader("response-foo", Arrays.asList("bar", "baz", "baf"));
//
////    request.getHeaders().setValues("foo", Arrays.asList("bar", "baz", "baf"));
//
//    // Act
//    evaluateTest();
//
//    // Verify
//    verify(crypto).getSHA1();
//    verify(sha1).update(data);
//    verify(sha1).digest();
//    verify(sha1).digest64();
//    verify(proxyRequest).getMethod();
//    verify(proxyResponse).getStatus();
//
//    verify(context).setVariable("mww.hash", digest);
//    verify(context).setVariable("mww.hash64", digest64);
//  }
//
//  @Override
//  File getTestFile() {
//    return TestFileUtils.getFileFromResources("sample-crypto-test.js");
//  }
//}
