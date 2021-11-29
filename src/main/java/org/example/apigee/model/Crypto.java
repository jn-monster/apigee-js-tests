package org.example.apigee.model;

import org.example.engine.model.BaseScriptableObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.annotations.JSFunction;

public class Crypto extends BaseScriptableObject {

  private CryptoHashObject sha1;
  private CryptoHashObject sha256;
  private CryptoHashObject sha512;
  private CryptoHashObject md5;

  private String dateFormat;

  @JSFunction
  public CryptoHashObject getSHA1() {
    if (sha1 == null) sha1 = newObject(CryptoHashObject.class);
    return sha1;
  }

  @JSFunction
  public CryptoHashObject getSHA256() {
    if (sha256 == null) sha256 = newObject(CryptoHashObject.class);
    return sha256;
  }

  @JSFunction
  public CryptoHashObject getSHA512() {
    if (sha512 == null) sha512 = newObject(CryptoHashObject.class);
    return sha512;
  }

  @JSFunction
  public CryptoHashObject getMD5() {
    if (md5 == null) md5 = newObject(CryptoHashObject.class);
    return md5;
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
    return this.dateFormat;
  }

  public void setDigest(String hashName, String digest) {
    var hash = getHash(hashName);
    hash.setDigest(digest);
  }

  public void setDigest64(String hashName, String digest64) {
    var hash = getHash(hashName);
    hash.setDigest64(digest64);
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
  }
}
