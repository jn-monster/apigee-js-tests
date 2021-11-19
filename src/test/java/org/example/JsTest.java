package org.example;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.json.JsonParser.ParseException;

class JsTest extends AbstractJsPolicyTest {

  public JsTest() throws ParseException {
  }

  @Test
  void test() throws Exception {
    evaluateTest();
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("test.js");
  }
}
