package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.validation;

import java.util.Arrays;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * A utility class for validating non-null content types and accept types in HTTP requests.
 *
 * <p>This class provides methods to ensure that arrays of content types or accept types do not
 * contain any {@code null} values. It is primarily used to validate input before making HTTP
 * requests, ensuring that the request headers do not have invalid {@code null} values.
 *
 * @since 1.3.0
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeValidator {

  /**
   * Validates that the given content types do not contain any {@code null} values.
   *
   * @param values the content types to be validated (must not contain {@code null})
   * @param <T> the type of the content types
   * @throws IllegalArgumentException if any of the provided content types is {@code null}
   * @since 1.3.0
   */
  @SafeVarargs
  public static <T> void nonNullContentTypes(T... values) {
    nonNull("ContentTypes", values);
  }

  /**
   * Validates that the given accept types do not contain any {@code null} values.
   *
   * @param values the accept types to be validated (must not contain {@code null})
   * @param <T> the type of the accept types
   * @throws IllegalArgumentException if any of the provided accept types is {@code null}
   * @since 1.3.0
   */
  @SafeVarargs
  public static <T> void nonNullAccepts(T... values) {
    nonNull("Accepts", values);
  }

  @SafeVarargs
  private static <T> void nonNull(String argumentName, T... values) {
    if (values != null && Arrays.stream(values).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException(argumentName + " must not contain null values");
    }
  }
}
