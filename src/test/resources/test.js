print("Javascript testing start...\n");

print(crypto.getSHA1());
print(context.flow);
print(context.targetRequest.url);
print(context.targetRequest.method);

print("Headers: " + context.targetRequest.headers);
print("Headers[0] (undefined): " + context.targetRequest.headers[0]); // undefined
print("Headers[1] (undefined): " + context.targetRequest.headers[1]); // undefined
print("Headers[foo]: " + context.targetRequest.headers["foo"]);
print("Headers[foo2]: " + context.targetRequest.headers["foo2"]);
// print("Headers[foo].length(): " + context.targetRequest.headers["foo"].size());
// print("Headers[foo].length(): " + context.targetRequest.headers["foo"].length());

// {'foo': ['bar']}
print("Foo Value[0]: " + context.targetRequest.headers['foo'][0]);  // bar
print("Foo default Value: " + context.targetRequest.headers['foo']);  // bar
print("Foo Length: " + context.targetRequest.headers['foo'].length()); // 1
print("Foo Value=== (false): " + context.targetRequest.headers['foo'][0] === 'bar')
print("Foo Value== (false): " + context.targetRequest.headers['foo'][0] == 'bar')
print("Foo Value== with '' (false): " + context.targetRequest.headers['foo'] + '' == 'bar');

// {'foo2': ['bar', 'baz']}
// print("Foo2 Length: " + context.targetRequest.headers['foo2'].length()); // 2
// print("Foo2 Value[0]: " + context.targetRequest.headers['foo2'][0]);  // bar
// print("Foo2 default Value: " + context.targetRequest.headers['foo2']);  // bar,baz

print("Headers[foo2][0]: " + context.targetRequest.headers["foo2"][0]);
print("Headers[foo2][1]: " + context.targetRequest.headers["foo2"][1]);

// if (context.targetRequest.headers['foo'].length() === 1) {
//   throw new Error('Test succeeded');
// }

// --------------------------------------
var length = context.targetRequest.headers['foo'].length();
print("Headers.length(): " + length);
print("Headers.length typeof: " + typeof length);

if (context.targetRequest.headers['foo'].length() !== 1) {
  throw new Error('Assert failed: length is not 1');
}


if (context.targetRequest.headers['foo'] + '' != 'bar') {
  throw new Error('Assert failed: header["foo"] is not "bar"');
}

throw new Error('Test succeeded');