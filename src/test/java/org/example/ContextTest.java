package org.example;

import java.io.File;
import org.junit.jupiter.api.Test;

class ContextTest extends AbstractJsPolicyTest {

  @Test
  void test() throws Exception {
    evaluateTest();
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("context.js");
  }
}
