package io.github.co_mmer.aaamockmvc.ej.test.web.internal.request;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.metadata.Since;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("2.0.2")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestValidation {

  @Since("2.0.2")
  public static void requireNonNull(Object value, String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }
  }

  @Since("2.0.2")
  public static void requireNonEmpty(String value, String message) {
    if (value.isBlank()) {
      throw new IllegalArgumentException(message);
    }
  }

  @Since("2.0.2")
  public static void requireNonBlank(List<?> values, String message) {
    if (values == null || values.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  @SafeVarargs
  @Since("2.0.2")
  public static <T> void requireNonEmpty(String message, T... values) {
    if (values == null || values.length == 0) {
      throw new IllegalArgumentException(message);
    }
  }
}
