package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class RequestQuery {

  private static final String NULL_PARAMETERS_MESSAGE = "Query parameters must not be null";

  private static final String NULL_PARAMETER_NAME_MESSAGE = "Query parameter name must not be null";

  private static final String BLANK_PARAMETER_NAME_MESSAGE =
      "Query parameter name must not be blank";

  private static final String NULL_PARAMETER_VALUE_MESSAGE =
      "Query parameter '%s' must not have a null value";

  private final Map<String, List<String>> values = new LinkedHashMap<>();

  public void add(String name, String value) {
    validateParameter(name, value);
    addValidated(name, value);
  }

  public void addAll(Map<String, String> parameters) {
    Objects.requireNonNull(parameters, NULL_PARAMETERS_MESSAGE);

    parameters.forEach(RequestQuery::validateParameter);
    parameters.forEach(this::addValidated);
  }

  public Map<String, List<String>> values() {
    var copy = new LinkedHashMap<String, List<String>>();
    this.values.forEach((n, v) -> copy.put(n, List.copyOf(v)));
    return Collections.unmodifiableMap(copy);
  }

  private static void validateParameter(String name, String value) {
    validateName(name);

    Objects.requireNonNull(value, NULL_PARAMETER_VALUE_MESSAGE.formatted(name));
  }

  private static void validateName(String name) {
    if (name == null) {
      throw new IllegalArgumentException(NULL_PARAMETER_NAME_MESSAGE);
    }

    if (name.isBlank()) {
      throw new IllegalArgumentException(BLANK_PARAMETER_NAME_MESSAGE);
    }
  }

  private void addValidated(String name, String value) {
    this.values.computeIfAbsent(name, ignored -> new ArrayList<>()).add(value);
  }
}
