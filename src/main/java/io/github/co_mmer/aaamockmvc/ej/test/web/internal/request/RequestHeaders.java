package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.http.MediaType;

@EqualsAndHashCode
public final class RequestHeaders {

  private Map<String, List<String>> values;

  public RequestHeaders() {
    this.values = new LinkedHashMap<>();
  }

  /*  public void set(@NonNull Map<String, List<String>> values) {
    var copy = new LinkedHashMap<String, List<String>>();

    values.forEach(
        (name, headerValues) -> {
          validateName(name);
          validateValues(name, headerValues);

          copy.put(name, List.copyOf(headerValues));
        });

    this.values = Map.copyOf(copy);
  }*/

  public void set(@NonNull Map<String, List<Object>> values) {
    var copy = new LinkedHashMap<String, List<String>>();

    values.forEach(
        (name, headerValues) -> {
          validateName(name);

          if (headerValues == null || headerValues.isEmpty()) {
            throw new IllegalArgumentException(
                "Header '%s' must contain at least one value".formatted(name));
          }

          if (headerValues.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException(
                "Header '%s' must not contain null values".formatted(name));
          }

          copy.put(name, headerValues.stream().map(String::valueOf).toList());
        });

    this.values = copy;
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

  public RequestHeaders accept(@NonNull MediaType... mediaTypes) {
    if (Arrays.stream(mediaTypes).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("mediaTypes must not contain null values");
    }
    values.put("Accept", Arrays.stream(mediaTypes).map(MediaType::toString).toList());
    return this;
  }

  public RequestHeaders contentType(@NonNull MediaType mediaType) {
    values.put("Content-Type", List.of(mediaType.toString()));
    return this;
  }

  public RequestHeaders contentType(@NonNull String mediaType) {
    values.put("Content-Type", List.of(mediaType));

    return this;
  }

  public void add(@NonNull String name, @NonNull String value) {
    validateName(name);

    if (value == null) {
      throw new IllegalArgumentException("Header value must not be blank");
    }

    values.computeIfAbsent(name, ignored -> new ArrayList<>()).add(value);
  }
}
