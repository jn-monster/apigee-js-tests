const data = context.getVariable('mww.data');
const data2 = context.proxyRequest.headers['foo'];
print(data);
print(data2);

const sha1 = crypto.getSHA1();
const sha256 = crypto.getHash('SHA-256');
sha1.update(data);
sha1.update(data2[2]);

const hash = sha1.digest();
const hash64 = sha1.digest64();

context.setVariable('mww.hash', hash);
context.setVariable('mww.hash64', hash64);