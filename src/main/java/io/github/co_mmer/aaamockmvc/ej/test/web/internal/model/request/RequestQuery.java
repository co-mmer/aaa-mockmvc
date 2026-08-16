package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class RequestQuery {

  private final Map<String, List<String>> values = new LinkedHashMap<>();

  public void add(String name, String value) {
    validateName(name);
    Objects.requireNonNull(value, "value must not be null");

    values.computeIfAbsent(name, ignored -> new java.util.ArrayList<>()).add(value);
  }

  public void addAllValues(Map<String, List<String>> values) {
    Objects.requireNonNull(values, "values must not be null");

    values.forEach(
        (name, queryValues) -> {
          validateName(name);

          Objects.requireNonNull(queryValues, "values for '%s' must not be null".formatted(name));

          queryValues.forEach(value -> add(name, value));
        });
  }

  public void addAll(Map<String, String> query) {
    Objects.requireNonNull(query, "query must not be null");

    query.forEach(this::add);
  }

  public Map<String, List<String>> values() {
    Map<String, List<String>> copy = new LinkedHashMap<>();

    values.forEach((name, queryValues) -> copy.put(name, List.copyOf(queryValues)));

    return Collections.unmodifiableMap(copy);
  }

  private static void validateName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Query parameter name must not be null or blank");
    }
  }
}
