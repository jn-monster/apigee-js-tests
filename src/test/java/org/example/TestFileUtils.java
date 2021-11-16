package org.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.net.URL;

public class TestFileUtils {

  public static File getFileFromResources(String filename) {
    URL resource = TestFileUtils.class.getClassLoader().getResource(filename);
    assertNotNull(resource, "File " + filename + " not found!");
    return new File(resource.getFile());
  }
}
