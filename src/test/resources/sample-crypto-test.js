const data = context.getVariable('mww.data');
const data2 = context.proxyRequest.headers['foo'];
const data3 = context.proxyRequest.headers['foo2'];

const sha1 = crypto.getSHA1();
const sha256 = crypto.getHash('SHA-256');
sha1.update(data);
sha1.update(data2[2]);

context.session['baz'] = data3;

const sha1Hash = sha1.digest();
const sha1Hash64 = sha1.digest64();

const sha256Hash = sha256.digest();
const sha256Hash64 = sha256.digest64();

context.setVariable('mww.sha1.hash', sha1Hash);
context.setVariable('mww.sha1.hash64', sha1Hash64);
context.setVariable('mww.sha256.hash', sha256Hash);
context.setVariable('mww.sha256.hash64', sha256Hash64);