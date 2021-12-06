package org.example;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;
import org.example.apigee.model.Flow;
import org.example.apigee.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.JavaScriptException;

class ContextTest extends AbstractJsPolicyTest {

  @Test
  void test() {
    // Arrange
    setContextFlow(Flow.PROXY_REQ_FLOW);
    // Context Session
    var contextSession = useContextSession();
    contextSession.put("foo", null, "bar");
    contextSession.put("foo2", null, 123);

    // Proxy Request
    Request proxyRequest = useContextProxyRequest();
    when(proxyRequest.getMethod()).thenReturn("GET");
    when(proxyRequest.getUrl()).thenReturn("http://someurl.com");

    var proxyRequestHeaders = proxyRequest.getHeaders();
    setHeaderValue(proxyRequestHeaders, "foo", Arrays.asList("bar"));
    setHeaderValue(proxyRequestHeaders, "foo2", Arrays.asList("bar", "baz"));

    var proxyRequestQueryParams = proxyRequest.getQueryParams();
    setQueryParamValue(proxyRequestQueryParams,"city", Arrays.asList("PaloAlto", "NewYork"));

    // Target Response
    var targetResponse = useContextTargetResponse();
    var responseHeaders = targetResponse.getHeaders();
    setHeaderValue(responseHeaders, "response-foo", Arrays.asList("response-bar"));
    setHeaderValue(responseHeaders, "response-foo2", Arrays.asList("response-bar", "response-baz"));

    var responseStatus = useTargetResponseStatus();
    when(responseStatus.getCode()).thenReturn("200");
    when(responseStatus.getMessage()).thenReturn("OK");

    try {
      // Act
      evaluateTest();
    } catch (JavaScriptException jex) {
      // Assert
      var errorMessage = jex.getMessage();
      if (errorMessage.contains("Assertion failed:")) {
        Assertions.fail(extractAssertionMessage(jex.getMessage()));
      } else if (!errorMessage.contains("Test succeeded")) {
        throw jex;
      }
    }
  }

  private String extractAssertionMessage(String exceptionMessage) {
    return exceptionMessage.substring(25, exceptionMessage.length());
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("context.js");
  }
}
