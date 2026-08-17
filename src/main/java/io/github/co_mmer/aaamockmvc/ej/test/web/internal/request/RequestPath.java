package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonEmpty;
import static io.github.co_mmer.aaamockmvc.ej.test.web.internal.request.RequestValidation.requireNonNull;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import org.springframework.web.util.UriComponentsBuilder;

@Since("2.0.2")
@EqualsAndHashCode
public class RequestPath {

  private static final String NULL_PATH_MESSAGE = "Request path must not be null";

  private static final String BLANK_PATH_MESSAGE = "Request path must not be blank";

  private static final String INVALID_PATH_MESSAGE = "Request path '%s' is not a valid URI";

  private static final String NULL_PATH_VARIABLES_MESSAGE = "Path variables must not be null";

  private static final String NULL_PATH_VARIABLE_MESSAGE =
      "Path variable at position %d must not be null";

  private static final String UNSUPPORTED_PATH_VARIABLE_MESSAGE =
      "Path variable at position %d has unsupported type '%s'";

  private static final String UNRESOLVABLE_PATH_TEMPLATE_MESSAGE =
      "Request path template '%s' could not be expanded with the provided variables";

  private URI value;

  @Since("2.0.2")
  public void setValue(String value) {
    this.value = toUri(value);
  }

  @Since("2.0.2")
  public void setValue(URI value) {
    this.value = validate(value);
  }

  @Since("2.0.2")
  public void setValue(String path, Object... variables) {
    var checkedPath = validate(path);
    var checkedVariables = validateVariables(variables);

    try {
      this.value =
          UriComponentsBuilder.fromUriString(checkedPath)
              .buildAndExpand(checkedVariables.toArray())
              .encode()
              .toUri();
    } catch (IllegalArgumentException exception) {
      throw new IllegalArgumentException(
          UNRESOLVABLE_PATH_TEMPLATE_MESSAGE.formatted(path), exception);
    }
  }

  @Since("2.0.2")
  public URI value() {
    return value;
  }

  private static URI toUri(String value) {
    var checkedValue = validate(value);

    try {
      return URI.create(checkedValue);
    } catch (IllegalArgumentException exception) {
      throw new IllegalArgumentException(INVALID_PATH_MESSAGE.formatted(value), exception);
    }
  }

  private static String validate(String value) {
    requireNonNull(value, NULL_PATH_MESSAGE);
    requireNonEmpty(value, BLANK_PATH_MESSAGE);
    return value;
  }

  private static URI validate(URI value) {
    requireNonNull(value, NULL_PATH_MESSAGE);
    requireNonEmpty(value.toString(), BLANK_PATH_MESSAGE);
    return value;
  }

  private static List<Object> validateVariables(Object... variables) {
    requireNonNull(variables, NULL_PATH_VARIABLES_MESSAGE);

    for (var index = 0; index < variables.length; index++) {
      var variable = variables[index];
      var position = index + 1;

      requireNonNull(variable, NULL_PATH_VARIABLE_MESSAGE.formatted(position));

      if (!isSupported(variable)) {
        throw new IllegalArgumentException(
            UNSUPPORTED_PATH_VARIABLE_MESSAGE.formatted(position, variable.getClass().getName()));
      }
    }

    return List.of(variables);
  }

  private static boolean isSupported(Object value) {
    return value instanceof String
        || value instanceof Character
        || value instanceof Number
        || value instanceof Boolean
        || value instanceof UUID
        || value instanceof Enum<?>;
  }
}
