package org.example;

import java.io.File;
import org.junit.jupiter.api.Test;

class CryptoTest extends AbstractJsPolicyTest {

  @Test
  void test() throws Exception {
    evaluateTest();
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("crypto.js");
  }
}
