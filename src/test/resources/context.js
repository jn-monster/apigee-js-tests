print("Javascript testing start...\n");

print(context.flow);

// SESSION
print("--- context.session... ---\n");
print(context.session['foo']);
context.session['test'] = 890;
print(context.session['test']);

// VARIABLES
print("--- context.variables... ---\n");
context.setVariable("variable-name", "variable-value123")
print(context.getVariable("variable-name")); // variable-value123
context.removeVariable("variable-name");
print(context.getVariable("variable-name")); // null

// TARGET REQUEST
print("--- context.targetRequest... ---\n");
print(context.targetRequest.url);
print(context.targetRequest.method);

// TARGET REQUEST BODY
print("--- context.targetRequest.body... ---\n");
print(context.targetRequest.body.asXML);
print(context.targetRequest.body.asJSON);
print(context.targetRequest.body.asForm);

// TARGET REQUEST QUERY PARAMS
print("--- context.targetRequest.queryParams... ---\n");
print(context.targetRequest.queryParams['city']);  // == 'PaloAlto'
print(context.targetRequest.queryParams['city'][0]);     // == 'PaloAlto'
print(context.targetRequest.queryParams['city'][1]);    // == 'NewYork'
print(context.targetRequest.queryParams['city'].length()); // == 2

// TARGET REQUEST HEADERS
print("--- context.targetRequest.headers... ---\n");
print("Headers: " + context.targetRequest.headers);

// {'foo': ['bar']}
print("Foo Length: " + context.targetRequest.headers['foo'].length()); // 1
print("Foo default Value: " + context.targetRequest.headers['foo']);  // bar
print("Headers[0] (undefined): " + context.targetRequest.headers[0]); // undefined
print("Headers[1] (undefined): " + context.targetRequest.headers[1]); // undefined
print("Foo Value[0]: " + context.targetRequest.headers['foo'][0]);  // bar
print("Foo Value=== (false): " + context.targetRequest.headers['foo'][0] === 'bar')
print("Foo Value== (false): " + context.targetRequest.headers['foo'][0] == 'bar')
print("Foo Value== with '' (false): " + context.targetRequest.headers['foo'] + '' == 'bar');

// {'foo2': ['bar', 'baz']}
print("Foo2 Length: " + context.targetRequest.headers['foo2'].length()); // 2
print("Foo2 default Value: " + context.targetRequest.headers['foo2']);  // bar,baz
print("Foo2 Value[0]: " + context.targetRequest.headers['foo2'][0]);  // bar

print("Headers[foo2][0]: " + context.targetRequest.headers["foo2"][0]); // bar
print("Headers[foo2][1]: " + context.targetRequest.headers["foo2"][1]); // baz

// if (context.targetRequest.headers['foo'].length() === 1) {
//   throw new Error('Test succeeded');
// }

// --------------------------------------
if (context.targetRequest.headers['foo'].length() !== 1) {
  throw new Error('Assert failed: length is not 1');
}


if (context.targetRequest.headers['foo'] + '' != 'bar') {
  throw new Error('Assert failed: header["foo"] is not "bar"');
}

throw new Error('Test succeeded');