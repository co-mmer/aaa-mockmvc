package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import java.net.URI;
import java.util.Objects;

public class RequestPath {

  private final URI value;

  public RequestPath(String value) {
    this(toUri(value));
  }

  public RequestPath(URI value) {
    this.value = validate(value);
  }

  public URI value() {
    return value;
  }

  private static URI toUri(String value) {
    Objects.requireNonNull(value, "value must not be null");

    if (value.isBlank()) {
      throw new IllegalArgumentException("Request path must not be blank");
    }

    return URI.create(value);
  }

  private static URI validate(URI value) {
    Objects.requireNonNull(value, "value must not be null");

    if (value.toString().isBlank()) {
      throw new IllegalArgumentException("Request path must not be blank");
    }

    return value;
  }
}
