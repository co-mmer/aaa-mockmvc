package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.base.validation;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.util.Arrays;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Since("1.3.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeValidator {

  @Since("1.3.0")
  @SafeVarargs
  public static <T> void nonNullContentTypes(T... values) {
    nonNull("ContentTypes", values);
  }

  @Since("1.3.0")
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
