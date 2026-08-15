package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import java.net.URI;
import java.util.Objects;
import org.springframework.web.util.UriComponentsBuilder;

public class RequestPath {

  private URI value;

  public void setValue(String value) {
    this.value = toUri(value);
  }

  public void setValue(URI value) {
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

  public void setValue(String path, Object... variables) {
    Objects.requireNonNull(path, "path must not be null");

    if (path.isBlank()) {
      throw new IllegalArgumentException("path must not be blank");
    }

    this.value =
        UriComponentsBuilder.fromUriString(path).buildAndExpand(variables).encode().toUri();
  }
}
