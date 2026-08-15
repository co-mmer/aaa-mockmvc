package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.NonNull;

public final class RequestHeaders {

  private final Map<String, List<String>> values;

  public RequestHeaders(@NonNull Map<String, List<String>> values) {
    var copy = new LinkedHashMap<String, List<String>>();

    values.forEach(
        (name, headerValues) -> {
          validateName(name);
          validateValues(name, headerValues);

          copy.put(name, List.copyOf(headerValues));
        });

    this.values = Map.copyOf(copy);
  }

  public Map<String, List<String>> values() {
    return values;
  }

  private static void validateName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Header name must not be null or blank");
    }
  }

  private static void validateValues(String name, List<String> values) {
    if (values == null || values.isEmpty()) {
      throw new IllegalArgumentException(
          "Header '%s' must contain at least one value".formatted(name));
    }

    if (values.stream().anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException(
          "Header '%s' must not contain null values".formatted(name));
    }
  }

  public RequestHeaders auth(@NonNull String token) {
    if (token.isBlank()) {
      throw new IllegalArgumentException("token must not be blank");
    }

    values.put(AUTHORIZATION, List.of(token));

    return this;
  }
}
