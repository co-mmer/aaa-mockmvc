package io.github.co_mmer.aaamockmvc.ej.test.web.internal.aaa.arrange;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.HttpMethod;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.Request;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestHeaders;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestPath;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestQuery;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.EmptyBody;
import io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.body.RequestBody;
import java.util.Objects;

@Since("2.0.2")
public final class TestArrangeBuilder {

  private HttpMethod method;
  private final RequestPath path = new RequestPath();
  private final RequestQuery query = new RequestQuery();
  private final RequestHeaders headers = new RequestHeaders();
  private RequestBody body = new EmptyBody();

  @Since("2.0.2")
  public TestArrangeBuilder method(HttpMethod method) {
    this.method = Objects.requireNonNull(method, "method must not be null");
    return this;
  }

  @Since("2.0.2")
  public RequestPath path() {
    return this.path;
  }

  @Since("2.0.2")
  public RequestQuery query() {
    return this.query;
  }

  @Since("2.0.2")
  public RequestHeaders headers() {
    return this.headers;
  }

  @Since("2.0.2")
  public RequestBody body(RequestBody body) {
    this.body = body;
    return this.body;
  }

  @Since("2.0.2")
  public RequestBody body() {
    return this.body;
  }

  @Since("2.0.2")
  public Request build() {
    return new Request(this.method, this.path, this.query, this.headers, this.body);
  }
}
