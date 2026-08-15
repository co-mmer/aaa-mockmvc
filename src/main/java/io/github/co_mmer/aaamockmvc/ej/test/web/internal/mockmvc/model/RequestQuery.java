package io.github.co_mmer.aaamockmvc.ej.test.web.internal.mockmvc.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class RequestQuery {

  private final Map<String, List<String>> values;

  public RequestQuery(Map<String, List<String>> values) {
    Objects.requireNonNull(values, "values must not be null");

    Map<String, List<String>> copy = new LinkedHashMap<>();

    values.forEach(
        (name, parameterValues) -> {
          validateName(name);
          validateValues(name, parameterValues);

          copy.put(name, List.copyOf(parameterValues));
        });

    this.values = copy;
  }

  public Map<String, List<String>> values() {
    return Map.copyOf(values);
  }

  private static void validateName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Query parameter name must not be null or blank");
    }
  }

  private static void validateValues(String name, List<String> values) {
    if (values == null || values.isEmpty()) {
      throw new IllegalArgumentException(
          "Query parameter '%s' must contain at least one value".formatted(name));
    }

    if (values.stream().anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException(
          "Query parameter '%s' must not contain null values".formatted(name));
    }
  }
}
