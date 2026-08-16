package io.github.co_mmer.aaamockmvc.ej.test.web.internal.model.request;

import java.math.BigInteger;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import org.springframework.web.util.UriComponentsBuilder;

@EqualsAndHashCode
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
      throw new IllegalArgumentException("TestArrangeResult path must not be blank");
    }

    return URI.create(value);
  }

  private static URI validate(URI value) {
    Objects.requireNonNull(value, "value must not be null");

    if (value.toString().isBlank()) {
      throw new IllegalArgumentException("TestArrangeResult path must not be blank");
    }

    return value;
  }

  public void setValue(String path, Object... variables) {
    if (path.isBlank()) {
      throw new IllegalArgumentException("path must not be blank");
    }

    var checkedVariables = validateSupported(variables);

    this.value =
        UriComponentsBuilder.fromUriString(path)
            .buildAndExpand(checkedVariables.toArray())
            .encode()
            .toUri();
  }

  private static List<Object> validateSupported(Object... variables) {
    var unsupported =
        Arrays.stream(variables)
            .filter(Objects::nonNull)
            .filter(value -> !isSupported(value))
            .toList();

    if (!unsupported.isEmpty()) {
      throw new IllegalArgumentException(
          "Unsupported path variable types: "
              + unsupported.stream().map(value -> value.getClass().getName()).distinct().toList());
    }

    if (Arrays.stream(variables).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Path variables must not contain null values");
    }

    return Arrays.asList(variables);
  }

  private static boolean isSupported(Object value) {
    return value instanceof String
        || value instanceof Character
        || value instanceof Byte
        || value instanceof Short
        || value instanceof Integer
        || value instanceof Long
        || value instanceof Float
        || value instanceof Double
        || value instanceof BigInteger
        || value instanceof Boolean
        || value instanceof UUID
        || value instanceof Enum<?>;
  }
}
