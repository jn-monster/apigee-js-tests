package org.example;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.JavaScriptException;

class CryptoTest extends AbstractJsPolicyTest {

  @Test
  void test() throws Exception {
    try {
      // Arrange
      crypto.getSHA1().setDigest("(Digested) something");
      crypto.getSHA1().setDigest64("(Digested64) something");

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
    return TestFileUtils.getFileFromResources("crypto.js");
  }
}
