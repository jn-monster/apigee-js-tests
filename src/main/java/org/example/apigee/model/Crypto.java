package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.annotations.JSFunction;

public class Crypto extends BaseScriptableObject {

  @JSFunction
  public CryptoHashObject getSHA1() {
    throw new UnsupportedOperationException("Call 'useCryptoSha1()' in tests to get SHA1 reference.");
  }

  @JSFunction
  public CryptoHashObject getSHA256() {
    throw new UnsupportedOperationException("Call 'useCryptoSha256()' in tests to get SHA256 reference.");
  }

  @JSFunction
  public CryptoHashObject getSHA512() {
    throw new UnsupportedOperationException("Call 'useCryptoSha512()' in tests to get SHA512 reference.");
  }

  @JSFunction
  public CryptoHashObject getMD5() {
    throw new UnsupportedOperationException("Call 'useCryptoMd5()' in tests to get MD5 reference.");
  }

  @JSFunction
  public CryptoHashObject getHash(String hashName) {
    switch (hashName) {
      case "MD5":
        return getMD5();
      case "SHA-1":
        return getSHA1();
      case "SHA-256":
        return getSHA256();
      case "SHA-512":
        return getSHA512();
      default:
        throw new IllegalStateException("Unsupported Hash '" + hashName + "' specified");
    }
  }

  // DateFormat
  @JSFunction
  public String dateFormat(Scriptable format, Scriptable timezone, Scriptable unixTimeStamp) {
    throw new UnsupportedOperationException("Mock the 'dateFormat()' call through Mockito.");
  }
}
