package org.example.apigee.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.example.ApigeeUtils;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;

public class Request extends BaseScriptableObject {

  private String url;
  private JsMap headers;
  private JsMap queryParams;
  private String method;
  private RequestBody body;

  public Request() {
    this.headers = ApigeeUtils.createScriptableObject(JsMap.class);
    this.queryParams = ApigeeUtils.createScriptableObject(JsMap.class);
    this.body = ApigeeUtils.createScriptableObject(RequestBody.class);
    this.url = "urlValue";
    this.method = "GET";

    // init for testing
    Map<String, JsMapValues> headerValues = new HashMap<>();
    var headerValues1 = ApigeeUtils.createScriptableObject(JsMapValues.class);
    var headerValues2 = ApigeeUtils.createScriptableObject(JsMapValues.class);
    headerValues1.setValues(Arrays.asList("bar"));
    headerValues2.setValues(Arrays.asList("bar", "baz"));
    headerValues.put("foo", headerValues1);
    headerValues.put("foo2", headerValues2);
    headers.setValues(headerValues);

    Map<String, JsMapValues> queryParamsValues = new HashMap<>();
    var queryParamValues1 = ApigeeUtils.createScriptableObject(JsMapValues.class);
    queryParamValues1.setValues(Arrays.asList("PaloAlto", "NewYork"));
    queryParamsValues.put("city", queryParamValues1);
    queryParams.setValues(queryParamsValues);
  }

  @JSGetter
  public String getUrl() {
    return url;
  }

  @JSSetter
  public void setUrl(String url) {
    this.url = url;
  }

  @JSGetter
  public JsMap getHeaders() {
    return headers;
  }

  @JSSetter
  public void setHeaders(JsMap headers) {
    this.headers = headers;
  }

  @JSGetter
  public JsMap getQueryParams() {
    return queryParams;
  }

  @JSSetter
  public void setQueryParams(JsMap queryParams) {
    this.queryParams = queryParams;
  }

  @JSGetter
  public String getMethod() {
    return method;
  }

  @JSSetter
  public void setMethod(String method) {
    this.method = method;
  }

  @JSGetter
  public RequestBody getBody() {
    return body;
  }

  @JSSetter
  public void setBody(RequestBody body) {
    this.body = body;
  }
}
