package org.example;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;
import org.mozilla.javascript.json.JsonParser.ParseException;

class JsTest extends AbstractJsPolicyTest {

  @Test
  void test() throws Exception {
    evaluateTest();
  }

  @Override
  File getTestFile() {
    return TestFileUtils.getFileFromResources("test.js");
  }
}
