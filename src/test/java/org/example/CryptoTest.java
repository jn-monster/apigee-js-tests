package org.example;

import static org.mockito.Mockito.*;

import java.io.File;
import org.example.apigee.model.Crypto;
import org.example.apigee.model.CryptoHashObject;
import org.example.apigee.model.Flow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mozilla.javascript.JavaScriptException;

class CryptoTest extends AbstractJsPolicyTest {

  @Test
  void test() throws Exception {
    try {
      // Arrange
      setContextFlow(Flow.PROXY_REQ_FLOW);
      CryptoHashObject sha1 = useCryptoSha1();
      when(sha1.digest()).thenReturn("(Digested) something");
      when(sha1.digest64()).thenReturn("(Digested64) something");

      // Act
      evaluateTest();

      verify(sha1).digest();
      verify(sha1).digest64();
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
    return TestFileUtils.getFileFromResources("crypto.js");
  }
}
