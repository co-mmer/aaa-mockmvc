package io.github.co_mmer.aaamockmvc.ej.test.web.internal.arrange.methods.core;

import io.github.co_mmer.aaamockmvc.ej.test.web.internal.annotation.Since;
import java.util.Arrays;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Deprecated
@Since("1.3.0")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestArrangeValidator {

  @Since("1.3.0")
  @SafeVarargs
  public static <T> void nonNullAccepts(T... values) {
    nonNull(values);
  }

  @SafeVarargs
  private static <T> void nonNull(T... values) {
    if (Arrays.stream(values).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Accepts" + " must not contain null values");
    }
  }
}
