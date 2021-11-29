const errors = [];

// Crypto
const cryptoValue = "something";
var sha1 = crypto.getSHA1();
sha1.update(cryptoValue);
const sha1DigestExpectedValue = '(Digested) ' + cryptoValue;
if (sha1.digest() !== sha1DigestExpectedValue) {
  errors.push("'sha1.digest()' is expected to be '" + sha1DigestExpectedValue + "' but was '" + sha1.digest() + "'");
}

const sha1Digest64ExpectedValue = '(Digested64) ' + cryptoValue;
if (sha1.digest64() !== sha1Digest64ExpectedValue) {
  errors.push("'sha1.digest64()' is expected to be '" + sha1Digest64ExpectedValue + "' but was '" + sha1.digest64() + "'");
}

// Evaluate errors
throw new Error(errors.length ? "Assertion failed: \n\t" + errors.join('\n\t') : 'Test succeeded');
// print(errors.length ? "Assertion failed: \n\t" + errors.join('\n\t') : 'Test succeeded');