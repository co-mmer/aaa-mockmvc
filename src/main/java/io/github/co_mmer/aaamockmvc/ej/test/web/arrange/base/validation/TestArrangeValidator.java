package io.github.co_mmer.aaamockmvc.ej.test.web.arrange.base.validation;

import java.util.Arrays;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeValidator {

  @SafeVarargs
  public static <T> void nonNullContentTypes(T... values) {
    nonNull("ContentTypes", values);
  }

  @SafeVarargs
  public static <T> void nonNullAccepts(T... values) {
    nonNull("Accepts", values);
  }

  @SafeVarargs
  private static <T> void nonNull(String argumentName, T... values) {
    if (values != null && Arrays.stream(values).anyMatch(Objects::isNull)) {
      throw new NullPointerException(argumentName + " must not contain null values");
    }
  }
}
