package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class RequestBuilder {

  private HttpMethod method;
  private RequestPath path;
  private RequestQuery query;
  private RequestHeaders headers;
  private final List<RequestCookie> cookies = new ArrayList<>();
  private RequestBody body = new EmptyBody();

  public RequestBuilder method(HttpMethod method) {
    this.method = Objects.requireNonNull(method, "method must not be null");
    return this;
  }

  public RequestPath path() {
    if (path == null) {
      path = new RequestPath();
    }

    return path;
  }

  public RequestQuery query() {
    if (query == null) {
      query = new RequestQuery();
    }

    return query;
  }

  public RequestHeaders headers() {
    if (headers == null) {
      headers = new RequestHeaders();
    }

    return headers;
  }

  public RequestBody body(RequestBody body) {
    this.body = body;
    return this.body;
  }

  public Request build() {
    return new Request(
        method,
        path,
        query != null ? query : new RequestQuery(),
        headers != null ? headers : new RequestHeaders(),
        List.copyOf(cookies),
        body);
  }
}
