package org.example;

import java.io.File;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.JavaScriptException;

class ContextTest extends AbstractJsPolicyTest {

  @Test
  void test() {
    // Arrange
    context.getProxyRequest().setMethod("GET");
    context.getProxyRequest().setUrl("http://someurl.com");

    var headers = context.getProxyRequest().getHeaders();
    headers.setValues("foo", Arrays.asList("bar"));
    headers.setValues("foo2", Arrays.asList("bar", "baz"));

    var queryParams = context.getProxyRequest().getQueryParams();
    queryParams.setValues("city", Arrays.asList("PaloAlto", "NewYork"));

    context.getSession().put("foo", null, "bar");
    context.getSession().put("foo2", null, 123);

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
