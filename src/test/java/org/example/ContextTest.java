package org.example;

import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import org.example.apigee.model.JsMapValues;
import org.example.apigee.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.JavaScriptException;

class ContextTest extends AbstractJsPolicyTest {

  @Test
  void test() {
    // Arrange
    // Context Session
    var contextSession = useContextSession();
    contextSession.put("foo", null, "bar");
    contextSession.put("foo2", null, 123);

    // Proxy Request
    Request proxyRequest = useContextProxyRequest();
    when(proxyRequest.getMethod()).thenReturn("GET");
    when(proxyRequest.getUrl()).thenReturn("http://someurl.com");

    var proxyRequestHeaders = proxyRequest.getHeaders();
    proxyRequestHeaders.setValues("foo", Arrays.asList("bar"));
    proxyRequestHeaders.setValues("foo2", Arrays.asList("bar", "baz"));

    var proxyRequestQueryParams = proxyRequest.getQueryParams();
    proxyRequestQueryParams.setValues("city", Arrays.asList("PaloAlto", "NewYork"));

    // Target Response
    var targetResponse = useContextTargetResponse();
    var responseHeaders = targetResponse.getHeaders();
    responseHeaders.setValues("response-foo", Arrays.asList("response-bar"));
    responseHeaders.setValues("response-foo2", Arrays.asList("response-bar", "response-baz"));

    var responseStatus = targetResponse.getStatus();
    responseStatus.setCode("200");
    responseStatus.setMessage("OK");


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
