package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.aaa.request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public record FormBody(Map<String, List<String>> values) implements RequestBody {

  public FormBody {
    Objects.requireNonNull(values, "values must not be null");

    Map<String, List<String>> copy = new LinkedHashMap<>();

    values.forEach(
        (name, formValues) -> {
          if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Form parameter name must not be null or blank");
          }

          if (formValues == null) {
            throw new IllegalArgumentException(
                "Form parameter '%s' values must not be null".formatted(name));
          }

          if (formValues.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException(
                "Form parameter '%s' must not contain null values".formatted(name));
          }

          copy.put(name, List.copyOf(formValues));
        });

    values = Map.copyOf(copy);
  }
}
