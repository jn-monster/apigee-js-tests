const errors = [];

// context.get/set/removeVariable()
var newContextObjectVariableName = 'new.context.object.variable';
var newContextObjectVariableValue = {'x': 1};
context.setVariable(newContextObjectVariableName, newContextObjectVariableValue)
if (context.getVariable(newContextObjectVariableName) === undefined) {
  errors.push("'context.getVariable('" + newContextObjectVariableName + "')' was expected to be found in the variables but was not.");
}
if (context.getVariable(newContextObjectVariableName) != newContextObjectVariableValue) { // TODO: verify with Mira if this is correct
  errors.push("'context.getVariable('" + newContextObjectVariableName + "')' was expected to be '" + newContextObjectVariableValue + "' but was '" + context.getVariable(newContextObjectVariableName) + "'");
}

var newContextStringVariableName = 'new.context.string.variable';
var newContextStringVariableValue = "string-value";
context.setVariable(newContextStringVariableName, newContextStringVariableValue)
if (context.getVariable(newContextStringVariableName) === undefined) {
  errors.push("'context.getVariable('" + newContextStringVariableName + "')' was expected to be found in the variables but was not.");
}
if (context.getVariable(newContextStringVariableName) !== newContextStringVariableValue) {
  errors.push("'context.getVariable('" + newContextStringVariableName + "')' was expected to be '" + newContextStringVariableValue + "' but was '" + context.getVariable(newContextStringVariableName) + "'");
}

var newContextNumberVariableName = 'new.context.number.variable';
var newContextNumberVariableValue = 123;
context.setVariable(newContextNumberVariableName, newContextNumberVariableValue)
if (context.getVariable(newContextNumberVariableName) === undefined) {
  errors.push("'context.getVariable('" + newContextNumberVariableName + "')' was expected to be found in the variables but was not.");
}
if (context.getVariable(newContextNumberVariableName) !== newContextNumberVariableValue) {
  errors.push("'context.getVariable('" + newContextNumberVariableName + "')' was expected to be '" + newContextNumberVariableValue + "' but was '" + context.getVariable(newContextNumberVariableName) + "'");
}

var newContextUndefinedVariableName = 'new.context.undefined.variable';
var newContextUndefinedVariableValue = undefined;
context.setVariable(newContextUndefinedVariableName, newContextUndefinedVariableValue)
if (context.getVariable(newContextUndefinedVariableName) !== newContextUndefinedVariableValue) {
  errors.push("'context.getVariable('" + newContextUndefinedVariableName + "')' was expected to be '" + newContextUndefinedVariableValue + "' but was '" + context.getVariable(newContextUndefinedVariableName) + "'");
}

var newContextVariableName = 'new.context.variable';
var newContextVariableValue = "some-value";
context.setVariable(newContextVariableName, newContextVariableValue)
if (context.getVariable(newContextVariableName) === undefined || context.getVariable(newContextVariableName) !== newContextVariableValue) {
  errors.push("'context.getVariable('" + newContextVariableName + "')' was expected to be found in the variables but was not or had an incorrect value.");
}
context.removeVariable(newContextVariableName);
if (context.getVariable(newContextVariableName) !== null) {
  errors.push("'context.getVariable('" + newContextVariableName + "')' was expected to be not found in the variables but was present.");
}

// context.flow tests
const contextFlowType = typeof context.flow;
if (contextFlowType !== 'string') {
  errors.push("'context.flow' is expected to be of type 'string' but is '" + contextFlowType + "'");
}

const contextFlowExpectedValue = 'PROXY_REQ_FLOW';
if (context.flow !== contextFlowExpectedValue) {
  errors.push("'context.flow' is expected to be '" + contextFlowExpectedValue + "' but was '" + context.flow + "'");
}

// context.session
const contextSessionType = typeof context.session;
if (contextSessionType !== 'object') {
  errors.push("'context.session' is expected to be of type 'object' but is '" + contextSessionType + "'");
}

const contextSessionFooExpectedValue = 'bar';
if (context.session['foo'] !== contextSessionFooExpectedValue) {
  errors.push("'context.session['foo']' is expected to be '" + contextSessionFooExpectedValue + "' but was '" + context.session['foo'] + "'");
}

const contextSessionFoo2ExpectedValue = 123;
if (context.session['foo2'] !== contextSessionFoo2ExpectedValue) {
  errors.push("'context.session['foo2']' is expected to be '" + contextSessionFoo2ExpectedValue + "' but was '" + context.session['foo2'] + "'");
}

// context.proxyRequest.url
const proxyRequestUrlType = typeof context.proxyRequest.url;
if (proxyRequestUrlType !== 'string') {
  errors.push("'context.proxyRequest.url' is expected to be of type 'string' but is '" + proxyRequestUrlType + "'");
}

const proxyRequestUrlExpectedValue = 'http://someurl.com';
if (context.proxyRequest.url !== proxyRequestUrlExpectedValue) {
  errors.push("'context.proxyRequest.url' is expected to be '" + proxyRequestUrlExpectedValue + "' but was '" + context.proxyRequest.url + "'");
}

// context.proxyRequest.method
const proxyRequestMethodType = typeof context.proxyRequest.method;
if (proxyRequestMethodType !== 'string') {
  errors.push("'context.proxyRequest.url' is expected to be of type 'string' but is '" + proxyRequestMethodType + "'");
}

const proxyRequestMethodExpectedValue = 'GET';
if (context.proxyRequest.method !== proxyRequestMethodExpectedValue) {
  errors.push("'context.proxyRequest.method' is expected to be '" + proxyRequestMethodExpectedValue + "' but was '" + context.proxyRequest.method + "'");
}

// context.proxyRequest.queryParams
const proxyRequestQueryParamsType = typeof context.proxyRequest.queryParams;
if (proxyRequestQueryParamsType !== 'object') {
  errors.push("'context.proxyRequest.queryParams' is expected to be of type 'object' but is '" + proxyRequestQueryParamsType + "'");
}

const proxyRequestQueryParamsExpectedDefaultValue = '{city=PaloAlto}';
if (context.proxyRequest.queryParams != proxyRequestQueryParamsExpectedDefaultValue) {
  errors.push("'context.proxyRequest.queryParams' is expected to be '" + proxyRequestQueryParamsExpectedDefaultValue + "' but was '" + context.proxyRequest.queryParams + "'");
}

const proxyRequestQueryParamsCityExpectedValue = 'PaloAlto';
if (context.proxyRequest.queryParams['city'] != proxyRequestQueryParamsCityExpectedValue) {
  errors.push("'context.proxyRequest.queryParams['city']' is expected to be '" + proxyRequestQueryParamsCityExpectedValue + "' but was '" + context.proxyRequest.queryParams['city'] + "'");
}

const proxyRequestQueryParamsCityExpectedLength = 2;
if (context.proxyRequest.queryParams['city'].length() !== proxyRequestQueryParamsCityExpectedLength) {
  errors.push("'context.proxyRequest.queryParams['city'].length()' is expected to be '" + proxyRequestQueryParamsCityExpectedLength + "' but was '" + context.proxyRequest.queryParams['city'].length() + "'");
}

const proxyRequestQueryParamsCity_0_ExpectedValue = 'PaloAlto';
if (context.proxyRequest.queryParams['city'][0] !== proxyRequestQueryParamsCity_0_ExpectedValue) {
  errors.push("'context.proxyRequest.queryParams['city'][0]' is expected to be '" + proxyRequestQueryParamsCity_0_ExpectedValue + "' but was '" + context.proxyRequest.queryParams['city'][0] + "'");
}

const proxyRequestQueryParamsCity_1_ExpectedValue = 'NewYork';
if (context.proxyRequest.queryParams['city'][1] !== proxyRequestQueryParamsCity_1_ExpectedValue) {
  errors.push("'context.proxyRequest.queryParams['city'][1]' is expected to be '" + proxyRequestQueryParamsCity_1_ExpectedValue + "' but was '" + context.proxyRequest.queryParams['city'][1] + "'");
}

// context.proxyRequest.headers
const proxyRequestHeadersType = typeof context.proxyRequest.headers;
if (proxyRequestHeadersType !== 'object') {
  errors.push("'context.proxyRequest.headers' is expected to be of type 'object' but is '" + proxyRequestHeadersType + "'");
}

if (context.proxyRequest.headers[0] !== undefined) {
  errors.push("'context.proxyRequest.headers[0]' is expected to be undefined but is '" + context.proxyRequest.headers[0] + "'");
}

const proxyRequestHeadersExpectedDefaultValue = '{foo=bar, foo2=bar}';
if (context.proxyRequest.headers != proxyRequestHeadersExpectedDefaultValue) {
  errors.push("'context.proxyRequest.headers' is expected to be '" + proxyRequestHeadersExpectedDefaultValue + "' but was '" + context.proxyRequest.headers + "'");
}

// {'foo': ['bar']}
const proxyRequestHeadersFooExpectedLength = 1;
if (context.proxyRequest.headers['foo'].length() !== proxyRequestHeadersFooExpectedLength) {
  errors.push("'context.proxyRequest.headers['foo'].length()' is expected to be '" + proxyRequestHeadersFooExpectedLength + "' but was '" + context.proxyRequest.headers['foo'].length() + "'");
}

const proxyRequestHeadersFooExpectedValue = 'bar';
if (context.proxyRequest.headers['foo'] != proxyRequestHeadersFooExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo']' is expected to be '" + proxyRequestHeadersFooExpectedValue + "' but was '" + context.proxyRequest.headers['foo'] + "'");
}

const proxyRequestHeadersFoo_0_ExpectedValue = 'bar';
if (context.proxyRequest.headers['foo'][0] !== proxyRequestHeadersFoo_0_ExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo'][0]' is expected to be '" + proxyRequestHeadersFoo_0_ExpectedValue + "' but was '" + context.proxyRequest.headers['foo'][0] + "'");
}

if ((context.proxyRequest.headers['foo'][0] + '') !== proxyRequestHeadersFoo_0_ExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo'][0]' is expected to be '" + proxyRequestHeadersFoo_0_ExpectedValue + "' but was '" + context.proxyRequest.headers['foo'][0] + "'");
}

if (context.proxyRequest.headers['foo'][0] != proxyRequestHeadersFoo_0_ExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo'][0]' is expected to be '" + proxyRequestHeadersFoo_0_ExpectedValue + "' but was '" + context.proxyRequest.headers['foo'][0] + "'");

}

// {'foo2': ['bar', 'baz']}
const proxyRequestHeadersFoo2ExpectedLength = 2;
if (context.proxyRequest.headers['foo2'].length() !== proxyRequestHeadersFoo2ExpectedLength) {
  errors.push("'context.proxyRequest.headers['foo2'].length()' is expected to be '" + proxyRequestHeadersFoo2ExpectedLength + "' but was '" + context.proxyRequest.headers['foo2'].length() + "'");
}

const proxyRequestHeadersFoo2ExpectedValue = 'bar';
if (context.proxyRequest.headers['foo2'] != proxyRequestHeadersFoo2ExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo2']' is expected to be '" + proxyRequestHeadersFoo2ExpectedValue + "' but was '" + context.proxyRequest.headers['foo2'] + "'");
}

const proxyRequestHeadersFoo2_0_ExpectedValue = 'bar';
if (context.proxyRequest.headers['foo2'][0] !== proxyRequestHeadersFoo2_0_ExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo2'][0]' is expected to be '" + proxyRequestHeadersFoo2_0_ExpectedValue + "' but was '" + context.proxyRequest.headers['foo2'][0] + "'");
}

const proxyRequestHeadersFoo2_1_ExpectedValue = 'baz';
if (context.proxyRequest.headers['foo2'][1] !== proxyRequestHeadersFoo2_1_ExpectedValue) {
  errors.push("'context.proxyRequest.headers['foo2'][1]' is expected to be '" + proxyRequestHeadersFoo2_1_ExpectedValue + "' but was '" + context.proxyRequest.headers['foo2'][1] + "'");
}

// TODO: PROXY REQUEST BODY -> Unsupported at the moment
// print(context.proxyRequest.body);
// print(context.proxyRequest.body.asXML);
// print(context.proxyRequest.body.asJSON);
// print(context.proxyRequest.body.asForm);

//TODO: context.proxyResponse.headers


// Evaluate errors
throw new Error(errors.length ? "Assertion failed: \n\t" + errors.join('\n\t') : 'Test succeeded');
// print(errors.length ? "Assertion failed: \n\t" + errors.join('\n\t') : 'Test succeeded');