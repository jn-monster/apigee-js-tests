package org.example.apigee.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class ApigeeContext {
  public String getFlow() {
    return "PROXY_REQ_FLOW";
  }

  public Request getTargetRequest() {
    return new Request();
  }

  public class Request {
    private final RequestHeaders headers = new RequestHeaders();

    public RequestHeaders getHeaders() {
      return headers;
    }
  }

  public class RequestHeaders extends ScriptableObject {
    private final Map<String, List<String>> headers = new HashMap<>();

    public RequestHeaders() {
      headers.put("foo", new ListWithLength<>(List.of("bar")));
    }

    @Override
    public String getClassName() {
      return "RequestHeaders";
    }

    public Object get(String name, Scriptable start) {
      return headers.get(name);
    }
  }

  public class ListWithLength<T> extends ArrayList<T> implements List<T> {
    public ListWithLength(Collection<? extends T> c) {
      super(c);
    }

    public long length() {
      return size();
    }
  }
}
