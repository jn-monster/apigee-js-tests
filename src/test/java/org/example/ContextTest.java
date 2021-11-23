package org.example;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContextTest extends AbstractJsPolicyTest {

  @BeforeEach
  public void before() {

  }

  @Test
  void test() throws Exception {
    evaluateTest();
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("context.js");
  }
}
