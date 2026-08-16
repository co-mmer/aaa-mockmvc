package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestCookie;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request.RequestQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TestArrangeBuilder {

  private HttpMethod method;
  private RequestPath path;
  private RequestQuery query;
  private RequestHeaders headers;
  private final List<RequestCookie> cookies = new ArrayList<>();
  private RequestBody body = new EmptyBody();

  public TestArrangeBuilder method(HttpMethod method) {
    this.method = Objects.requireNonNull(method, "method must not be null");
    return this;
  }

  public RequestPath path() {
    if (this.path == null) {
      this.path = new RequestPath();
    }

    return this.path;
  }

  public RequestQuery query() {
    if (this.query == null) {
      this.query = new RequestQuery();
    }

    return this.query;
  }

  public RequestHeaders headers() {
    if (this.headers == null) {
      this.headers = new RequestHeaders();
    }

    return this.headers;
  }

  public RequestBody body(RequestBody body) {
    this.body = body;
    return this.body;
  }

  public RequestBody body() {
    return this.body;
  }

  public TestArrangeResult build() {
    return new TestArrangeResult(
        this.method, this.path, this.query, this.headers, List.copyOf(this.cookies), this.body);
  }
}
