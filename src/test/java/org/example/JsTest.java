package org.example;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class JsTest extends AbstractJsPolicyTest {

  @Test
  void test() throws IOException {
    evaluateTest();
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("test.js");
  }
}
