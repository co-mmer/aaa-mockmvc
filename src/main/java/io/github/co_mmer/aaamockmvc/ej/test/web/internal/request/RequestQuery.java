package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonEmpty;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonNull;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.EqualsAndHashCode;

@Since("2.0.2")
@EqualsAndHashCode
public final class RequestQuery {

  private static final String NULL_PARAMETERS_MESSAGE = "Query parameters must not be null";

  private static final String NULL_PARAMETER_NAME_MESSAGE = "Query parameter name must not be null";

  private static final String BLANK_PARAMETER_NAME_MESSAGE =
      "Query parameter name must not be blank";

  private static final String NULL_PARAMETER_VALUE_MESSAGE =
      "Query parameter '%s' must not have a null value";

  private final Map<String, List<String>> values = new LinkedHashMap<>();

  @Since("2.0.2")
  public void add(String name, String value) {
    validateParameter(name, value);
    addValidated(name, value);
  }

  @Since("2.0.2")
  public void addAll(Map<String, String> parameters) {
    requireNonNull(parameters, NULL_PARAMETERS_MESSAGE);
    parameters.forEach(RequestQuery::validateParameter);
    parameters.forEach(this::addValidated);
  }

  @Since("2.0.2")
  public Map<String, List<String>> values() {
    var copy = new LinkedHashMap<String, List<String>>();
    this.values.forEach((n, v) -> copy.put(n, List.copyOf(v)));
    return Collections.unmodifiableMap(copy);
  }

  private static void validateParameter(String name, String value) {
    requireNonNull(name, NULL_PARAMETER_NAME_MESSAGE);
    requireNonEmpty(name, BLANK_PARAMETER_NAME_MESSAGE);
    requireNonNull(value, NULL_PARAMETER_VALUE_MESSAGE.formatted(name));
  }

  private void addValidated(String name, String value) {
    this.values.computeIfAbsent(name, ignored -> new ArrayList<>()).add(value);
  }
}
