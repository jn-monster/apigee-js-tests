print("Javascript testing start...\n");

var sha1 = crypto.getSHA1();

for (var i = 0; i < 5; i++) {
  print(sha1);
}

print(context.flow);

// {'foo': ['bar']}
print("Length: " + context.targetRequest.headers['foo'].length()); // 1
// print("Length[0]: " + context.targetRequest.headers['foo'][0]);  // bar
print("Length: " + context.targetRequest.headers['foo']);  // bar
// print("Length(false): " + context.targetRequest.headers['foo'][0] === 'bar')
// print("Length(true): " + context.targetRequest.headers['foo'][0] == 'bar')
// print("Length(true): " + context.targetRequest.headers['foo'][0] + '' == 'bar')

// {'foo2': ['bar', 'baz']}
// print("Length: " + context.targetRequest.headers['foo2'].length()); // 2
// print("Length[0]: " + context.targetRequest.headers['foo2'][0]);  // bar
// print("Length: " + context.targetRequest.headers['foo2']);  // bar,baz

if (context.targetRequest.headers['foo'].length() === 1) {
  throw new Error('Test succeeded');
}